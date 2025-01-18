package com.appsolve.wearther_backend.home.service;

import com.appsolve.wearther_backend.Service.LocationConverter;
import com.appsolve.wearther_backend.apiResponse.exception.CustomException;
import com.appsolve.wearther_backend.apiResponse.exception.ErrorCode;
import com.appsolve.wearther_backend.init_data.entity.LocationInfo;
import com.appsolve.wearther_backend.init_data.repository.LocationInfoRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.http.converter.xml.Jaxb2RootElementHttpMessageConverter;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.StringReader;
import java.net.URI;
import java.net.URISyntaxException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Service
public class UvService {
    private final String serviceKey;
    private final LocationInfoRepository locationInfoRepository;

    public UvService(@Value("${weather.service-key}") String serviceKey, LocationInfoRepository locationInfoRepository) {
        this.serviceKey = serviceKey;
        this.locationInfoRepository = locationInfoRepository;
    }
    private static final String KMA_API_URL = "http://apis.data.go.kr/1360000/LivingWthrIdxServiceV4/getUVIdxV4";

    // api 호출
    public String getWeatherData(String areaNo) throws URISyntaxException {
        List<HttpMessageConverter<?>> messageConverters = new ArrayList<>();
        messageConverters.add(new StringHttpMessageConverter());
        messageConverters.add(new Jaxb2RootElementHttpMessageConverter());
        messageConverters.add(new MappingJackson2HttpMessageConverter());

        RestTemplate restTemplate = new RestTemplate(messageConverters);

        LocalDateTime now = LocalDateTime.now();
        String time = now.format(DateTimeFormatter.ofPattern("yyyyMMddHH"));

        String url = String.format(
                "%s?ServiceKey=%s&pageNo=1&numOfRows=10&dataType=XML&areaNo=%s&time=%s",
                KMA_API_URL, serviceKey, areaNo, time);

        System.out.println("Request URL: " + url);

        URI uri = new URI(url);
        String responseBody = restTemplate.getForObject(uri, String.class);

        System.out.println("Response Body: " + responseBody);

        return responseBody;
    }

    // 자외선 지수 데이터 파싱
    private String parseUV(String responseBody) {
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            InputSource inputSource = new InputSource(new StringReader(responseBody));
            Document doc = builder.parse(inputSource);

            NodeList nodeList = doc.getElementsByTagName("item");

            String UV;

            for (int i = 0; i < nodeList.getLength(); i++) {
                Node node = nodeList.item(i);
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element element = (Element) node;

                    UV = element.getElementsByTagName("h0").item(0).getTextContent();
                    return UV;
                }
            }


        } catch (Exception e) {
            e.printStackTrace();
        }
        throw new CustomException(ErrorCode.Weather_NOT_FOUND);
    }

    public String getLocationCode(String gridX, String gridY) {
        return locationInfoRepository.findByGridXAndGridY(gridX, gridY)
                .stream()
                .map(LocationInfo::getId)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("행정 구역 코드를 불러올 수 없습니다."));
    }


    // 최종 자외선 지수 데이터 반환
    public int getUV(double latitude, double longitude) {
        int[] convert = LocationConverter.latLonToGrid(latitude, longitude);
        String x = String.valueOf(convert[0]);
        String y = String.valueOf(convert[1]);

        String locationCode = getLocationCode(x, y);

        try {
            String uvValue = parseUV(getWeatherData(locationCode));

            return Integer.parseInt(uvValue);

        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }
}
