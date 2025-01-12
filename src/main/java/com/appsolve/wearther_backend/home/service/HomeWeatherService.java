package com.appsolve.wearther_backend.home.service;

import com.appsolve.wearther_backend.Service.LocationConverter;
import com.appsolve.wearther_backend.apiResponse.exception.CustomException;
import com.appsolve.wearther_backend.apiResponse.exception.ErrorCode;
import com.appsolve.wearther_backend.home.dto.WeatherResponseDto;
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
public class HomeWeatherService {

    private final String serviceKey;

    public HomeWeatherService(@Value("${weather.service-key}") String serviceKey) {
        this.serviceKey = serviceKey;
    }

    private static final String KMA_API_URL = "http://apis.data.go.kr/1360000/VilageFcstInfoService_2.0/getVilageFcst";

    public String getCurrentWeather(int x, int y) throws URISyntaxException {
        List<HttpMessageConverter<?>> messageConverters = new ArrayList<>();
        messageConverters.add(new StringHttpMessageConverter());
        messageConverters.add(new Jaxb2RootElementHttpMessageConverter());
        messageConverters.add(new MappingJackson2HttpMessageConverter());  // JSON 응답 처리
        messageConverters.add(new Jaxb2RootElementHttpMessageConverter());  // XML 응답 처리

        RestTemplate restTemplate = new RestTemplate(messageConverters);

        // 기상청 API 요청 시간 계산
        LocalDateTime now = LocalDateTime.now();
        int hour = now.getHour();
        int minute = now.getMinute();
        if (hour < 2 || (hour == 2 && minute < 10)) {
            now = now.minusDays(1);
        }

        String baseDate = now.format(DateTimeFormatter.ofPattern("yyyyMMdd"));
        String baseTime;

        if (hour < 2 || (hour == 2 && minute < 10)) {
            now = now.minusDays(1);
        }

        // 현재 시간을 기준으로 적절한 baseTime 설정 (추후 조정 필요할듯 ㅜㅜ)
        if (hour < 2 || (hour == 2 && minute <= 10)) {
            baseTime = "2300";
        } else if (hour < 5 || (hour == 5 && minute <= 10)) {
            baseTime = "0200";
        } else if (hour < 8 || (hour == 8 && minute <= 10)) {
            baseTime = "0500";
        } else if (hour < 11 || (hour == 11 && minute <= 10)) {
            baseTime = "0800";
        } else if (hour < 14 || (hour == 14 && minute <= 10)) {
            baseTime = "1100";
        } else if (hour < 17 || (hour == 17 && minute <= 10)) {
            baseTime = "1400";
        } else if (hour < 20 || (hour == 20 && minute <= 10)) {
            baseTime = "1700";
        } else if (hour < 23 || (hour == 23 && minute <= 10)) {
            baseTime = "2000";
        } else baseTime = "2300";

        String url = String.format(
                "%s?serviceKey=%s&pageNo=1&numOfRows=170&dataType=XML&base_date=%s&base_time=%s&nx=%d&ny=%d",
                KMA_API_URL, serviceKey, baseDate, baseTime, x, y);

        System.out.println("Request URL: " + url);

        URI uri = new URI(url);
        String responseBody = restTemplate.getForObject(uri, String.class);

        System.out.println("Response Body: " + responseBody);

        // XML 파싱하여 온도 정보 추출
        String temperature = null;
        String temperatureMin = null;
        String temperatureMax = null;
        String humidity = null;

        try {
            // XML 파싱: "TMP","TMN", "TMX", "REH" 값을 추출
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            InputSource inputSource = new InputSource(new StringReader(responseBody));
            Document doc = builder.parse(inputSource);

            // "category" 요소가 "TMP","TMN", "TMX", "REH"인 값을 찾음
            NodeList nodeList = doc.getElementsByTagName("item");
            for (int i = 0; i < nodeList.getLength(); i++) {
                Node node = nodeList.item(i);
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element element = (Element) node;
                    String category = element.getElementsByTagName("category").item(0).getTextContent();
                    if ("TMP".equals(category)) {
                        // 온도 값 추출
                        temperature = element.getElementsByTagName("fcstValue").item(0).getTextContent();
                        break;
                    }
                }
            }

            for (int i = 0; i < nodeList.getLength(); i++) {
                Node node = nodeList.item(i);
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element element = (Element) node;
                    String category = element.getElementsByTagName("category").item(0).getTextContent();
                    if ("TMN".equals(category)) {
                        // 일 최저 기온 값 추출
                        temperatureMin = element.getElementsByTagName("fcstValue").item(0).getTextContent();
                        break;
                    }
                }
            }

            for (int i = 0; i < nodeList.getLength(); i++) {
                Node node = nodeList.item(i);
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element element = (Element) node;
                    String category = element.getElementsByTagName("category").item(0).getTextContent();
                    if ("TMX".equals(category)) {
                        // 일 최고 기온 값 추출
                        temperatureMax = element.getElementsByTagName("fcstValue").item(0).getTextContent();
                        break;
                    }
                }
            }

            for (int i = 0; i < nodeList.getLength(); i++) {
                Node node = nodeList.item(i);
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element element = (Element) node;
                    String category = element.getElementsByTagName("category").item(0).getTextContent();
                    if ("REH".equals(category)) {
                        // 일 습도 값 추출
                        humidity = element.getElementsByTagName("fcstValue").item(0).getTextContent();
                        break;
                    }
                }
            }

            if (temperature != null && temperatureMin != null && temperatureMax != null && humidity != null) {
                return String.format("%s°C, %s°C, %s°C, %s%%", temperature, temperatureMin, temperatureMax, humidity);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        throw new CustomException(ErrorCode.Weather_NOT_FOUND);
    }

    public WeatherResponseDto getWeatherValue (double latitude, double longitude) {
        int[] convert = LocationConverter.latLonToGrid(latitude, longitude);

        String weatherData = null;
        String temperature = null;
        String temperatureMin = null;
        String temperatureMax = null;
        String humidity = null;

        try {
            weatherData = getCurrentWeather(convert[0], convert[1]);
            System.out.println(weatherData);
            String[] data = weatherData.split(",");
            temperature = data[0].trim();
            temperatureMin = data[1].trim();
            temperatureMax = data[2].trim();
            humidity = data[3].trim();
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
        return new WeatherResponseDto(temperature, temperatureMin, temperatureMax, humidity);
    }
}