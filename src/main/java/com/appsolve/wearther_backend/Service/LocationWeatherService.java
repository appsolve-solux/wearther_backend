package com.appsolve.wearther_backend.Service;

import com.appsolve.wearther_backend.Dto.LocationWeatherResponseDto;
import com.appsolve.wearther_backend.Entity.Location;
import com.appsolve.wearther_backend.Repository.LocationRepository;
import com.appsolve.wearther_backend.apiResponse.exception.CustomException;
import com.appsolve.wearther_backend.apiResponse.exception.ErrorCode;
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
import java.util.stream.Collectors;

@Service
public class LocationWeatherService {

    private final LocationRepository locationRepository;
    private final String serviceKey;


    public LocationWeatherService(LocationRepository locationRepository,
                                  @Value("${weather.service-key}") String serviceKey) {
        this.locationRepository = locationRepository;
        this.serviceKey = serviceKey;
    }


    private static final String KMA_API_URL = "http://apis.data.go.kr/1360000/VilageFcstInfoService_2.0/getVilageFcst";

    public String getCurrentTemperature(int x, int y) throws URISyntaxException {
        List<HttpMessageConverter<?>> messageConverters = new ArrayList<>();
        messageConverters.add(new StringHttpMessageConverter());
        messageConverters.add(new Jaxb2RootElementHttpMessageConverter());
        messageConverters.add(new MappingJackson2HttpMessageConverter());  // JSON 응답 처리
        messageConverters.add(new Jaxb2RootElementHttpMessageConverter());  // XML 응답 처리 (필요시 추가)

        RestTemplate restTemplate = new RestTemplate(messageConverters);

        // 기상청 API 요청 시간 계산
        LocalDateTime now = LocalDateTime.now();
        if (now.getHour() < 2 || (now.getHour() == 2 && now.getMinute() < 30)) {
            now = now.minusDays(1);
        }

        String baseDate = now.format(DateTimeFormatter.ofPattern("yyyyMMdd"));

        // 현재 시간을 기준으로 적절한 baseTime 설정
        String baseTime;
        int hour = now.getHour();
        if (hour >= 0 && hour < 3) {
            baseTime = "0200";
        } else if (hour >= 3 && hour < 6) {
            baseTime = "0500";
        } else if (hour >= 6 && hour < 9) {
            baseTime = "0800";
        } else if (hour >= 9 && hour < 12) {
            baseTime = "1100";
        } else if (hour >= 12 && hour < 15) {
            baseTime = "1400";
        } else if (hour >= 15 && hour < 18) {
            baseTime = "1700";
        } else if (hour >= 18 && hour < 21) {
            baseTime = "2000";
        } else {
            baseTime = "2300";
        }


        String url = String.format(
                "%s?serviceKey=%s&pageNo=1&numOfRows=10&dataType=XML&base_date=%s&base_time=%s&nx=%d&ny=%d",
                KMA_API_URL, serviceKey, baseDate, baseTime, x, y);

        System.out.println("Request URL: " + url);

        URI uri = new URI(url);
        String responseBody = restTemplate.getForObject(uri, String.class);


        System.out.println("Response Body: " + responseBody);

        // XML 파싱하여 온도 정보 추출
        try {
            // XML 파싱: "TMP" 값을 추출
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            InputSource inputSource = new InputSource(new StringReader(responseBody));
            Document doc = builder.parse(inputSource);

            // "category" 요소가 "TMP"인 값을 찾음
            NodeList nodeList = doc.getElementsByTagName("item");
            for (int i = 0; i < nodeList.getLength(); i++) {
                Node node = nodeList.item(i);
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element element = (Element) node;
                    String category = element.getElementsByTagName("category").item(0).getTextContent();
                    if ("TMP".equals(category)) {
                        // 온도 값 추출
                        String temperature = element.getElementsByTagName("fcstValue").item(0).getTextContent();
                        return temperature + "°C";
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        throw new CustomException(ErrorCode.Weather_NOT_FOUND);
    }

    public LocationWeatherResponseDto getLocationsWeather(Long memberId) {
        // 사용자의 모든 위치 정보 조회
        List<Location> locations = locationRepository.findAllByMember_MemberId(memberId);

        // 각 위치별 날씨 정보 수집
        List<LocationWeatherResponseDto.LocationWeatherInfo> locationWeatherInfos = locations.stream()
                .map(location -> {

                    double latitude = location.getLatitude();
                    double longitude = location.getLongitude();

                    int[] convert = LocationConverter.latLonToGrid(latitude, longitude);


                    String temperature = null;  // int 값을 전달
                    try {
                        temperature = getCurrentTemperature(convert[0], convert[1]);
                    } catch (URISyntaxException e) {
                        throw new RuntimeException(e);
                    }

                    return new LocationWeatherResponseDto.LocationWeatherInfo(
                            location.getLocationInfo(),
                            location.getLocationIndex(),
                            temperature
                    );
                })
                .collect(Collectors.toList());

        return new LocationWeatherResponseDto(locationWeatherInfos);
    }

    public String getCurrentTmp(double latitude, double longitude){
        int[] convert = LocationConverter.latLonToGrid(latitude, longitude);

        String temperature = null;  // int 값을 전달
        try {
            temperature = getCurrentTemperature(convert[0], convert[1]);
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }

        return temperature;
    }

}

