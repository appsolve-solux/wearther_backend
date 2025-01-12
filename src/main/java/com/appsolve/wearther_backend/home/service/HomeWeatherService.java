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

    public WeatherResponseDto getCurrentWeather(int x, int y) throws URISyntaxException {
        List<HttpMessageConverter<?>> messageConverters = new ArrayList<>();
        messageConverters.add(new StringHttpMessageConverter());
        messageConverters.add(new Jaxb2RootElementHttpMessageConverter());
        messageConverters.add(new MappingJackson2HttpMessageConverter());

        RestTemplate restTemplate = new RestTemplate(messageConverters);

        LocalDateTime now = adjustBaseDateTime();
        String baseDate = now.format(DateTimeFormatter.ofPattern("yyyyMMdd"));
        String baseTime = determineBaseTime(now);

        String url = String.format(
                "%s?serviceKey=%s&pageNo=1&numOfRows=170&dataType=XML&base_date=%s&base_time=%s&nx=%d&ny=%d",
                KMA_API_URL, serviceKey, baseDate, baseTime, x, y);
        System.out.println("Request URL: " + url);

        URI uri = new URI(url);
        String responseBody = restTemplate.getForObject(uri, String.class);
        System.out.println("Response Body: " + responseBody);

        return parseWeatherResponse(responseBody);
    }

    private LocalDateTime adjustBaseDateTime() {
        LocalDateTime now = LocalDateTime.now();
        int hour = now.getHour();
        int minute = now.getMinute();
        if (hour < 2 || (hour == 2 && minute <= 30)) {
            now = now.minusDays(1);
        }
        return now;
    }

    private String determineBaseTime(LocalDateTime now) {
        int hour = now.getHour();
        int minute = now.getMinute();
        if (hour < 2 || (hour == 2 && minute <= 30)) {
            return "2300";
        } else if (hour < 5 || (hour == 5 && minute <= 30)) {
            return "0200";
        } else if (hour < 8 || (hour == 8 && minute <= 30)) {
            return "0500";
        } else if (hour < 11 || (hour == 11 && minute <= 30)) {
            return "0800";
        } else if (hour < 14 || (hour == 14 && minute <= 30)) {
            return "1100";
        } else if (hour < 17 || (hour == 17 && minute <= 30)) {
            return "1400";
        } else if (hour < 20 || (hour == 20 && minute <= 30)) {
            return "1700";
        } else if (hour < 23 || (hour == 23 && minute <= 30)) {
            return "2000";
        } else {
            return "2300";
        }
    }

    private WeatherResponseDto parseWeatherResponse(String responseBody) {
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            InputSource inputSource = new InputSource(new StringReader(responseBody));
            Document doc = builder.parse(inputSource);

            NodeList nodeList = doc.getElementsByTagName("item");

            String temperatureMin = null; // 최저 기온
            String temperatureMax = null; // 최고 기온
            String humidity = null; // 습도
            List<String> hourlyTemp = new ArrayList<>(); // 시간 별 기온
            List<String> hourlySky = new ArrayList<>(); // 시간 별 하늘 상태

            LocalDateTime now = LocalDateTime.now();
            int currentHour = now.getHour();
            String formatDate = now.format(DateTimeFormatter.ofPattern("yyyyMMdd"));
            String formatHour;
            int adjustedHour = now.getMinute() >= 30 ? currentHour + 1 : currentHour;
            formatHour = String.format("%02d00", adjustedHour);

            boolean foundStart = false; // 시작점을 찾았는지 여부

            for (int i = 0; i < nodeList.getLength(); i++) {
                Node node = nodeList.item(i);
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element element = (Element) node;

                    String category = element.getElementsByTagName("category").item(0).getTextContent();
                    String fcstDate = element.getElementsByTagName("fcstDate").item(0).getTextContent();
                    String fcstTime = element.getElementsByTagName("fcstTime").item(0).getTextContent();
                    String fcstValue = element.getElementsByTagName("fcstValue").item(0).getTextContent();

                    if ("TMN".equals(category)) {
                        temperatureMin = fcstValue + "°C" + fcstTime;
                    } else if ("TMX".equals(category)) {
                        temperatureMax = fcstValue + "°C" + fcstTime;
                    } else if ("REH".equals(category) && fcstDate.equals(formatDate) && fcstTime.equals(formatHour)) {
                        humidity = fcstValue + "%" + fcstTime;
                    }

                    // 시간 비교하여 다음 7개의 데이터 수집
                    if (hourlyTemp.size() < 7 || hourlySky.size() < 7) {
                        if (!foundStart && Integer.parseInt(fcstTime) >= adjustedHour*100) {
                            foundStart = true; // 시작점 찾음
                        }
                        if (foundStart) {
                            if ("TMP".equals(category)) {
                                hourlyTemp.add(fcstValue + "°C" + fcstTime);
                            } else if ("SKY".equals(category)) {
                                // 하늘 상태 코드 변환
                                String skyDescription = switch (fcstValue) {
                                    case "1" -> "맑음";
                                    case "2" -> "구름 조금";
                                    case "3" -> "구름 많음";
                                    case "4" -> "흐림";
                                    default -> "알 수 없음";
                                };
                                hourlySky.add(skyDescription + fcstTime);
                            }
                        }
                    }
                }
            }

            // 모든 데이터를 WeatherResponseDto에 매핑
            return new WeatherResponseDto(
                    hourlyTemp.isEmpty() ? "N/A" : hourlyTemp.get(0), // 현재온도
                    temperatureMin != null ? temperatureMin : "N/A",
                    temperatureMax != null ? temperatureMax : "N/A",
                    humidity != null ? humidity : "N/A",
                    hourlyTemp,
                    hourlySky
            );

        } catch (Exception e) {
            e.printStackTrace();
        }
        throw new CustomException(ErrorCode.Weather_NOT_FOUND);
    }

    public WeatherResponseDto getWeatherValue(double latitude, double longitude) {
        int[] convert = LocationConverter.latLonToGrid(latitude, longitude);

        try {
            // getCurrentWeather 메서드가 WeatherResponseDto를 반환하므로 그대로 사용
            return getCurrentWeather(convert[0], convert[1]);
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }

}
