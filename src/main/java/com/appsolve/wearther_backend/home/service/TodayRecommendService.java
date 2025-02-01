package com.appsolve.wearther_backend.home.service;

import com.appsolve.wearther_backend.Repository.LocationRepository;
import com.appsolve.wearther_backend.Service.MemberService;
import com.appsolve.wearther_backend.Service.MemberTasteService;
import com.appsolve.wearther_backend.Service.TasteService;
import com.appsolve.wearther_backend.apiResponse.exception.CustomException;
import com.appsolve.wearther_backend.apiResponse.exception.ErrorCode;
import com.appsolve.wearther_backend.closet.dto.ClosetResponseDto;
import com.appsolve.wearther_backend.closet.service.ClosetService;
import com.appsolve.wearther_backend.home.dto.RecommendResponseDto;
import com.appsolve.wearther_backend.home.dto.WeatherResponseDto;
import com.appsolve.wearther_backend.Entity.Location;
import com.appsolve.wearther_backend.init_data.repository.WeatherLowerWearRepository;
import com.appsolve.wearther_backend.init_data.repository.WeatherOtherWearRepository;
import com.appsolve.wearther_backend.init_data.repository.WeatherRepostory;
import com.appsolve.wearther_backend.init_data.repository.WeatherUpperWearRepository;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TodayRecommendService {
    private final HomeWeatherService homeWeatherService;
    private final MemberService memberService;
    private final ClosetService closetService;
    private final WeatherUpperWearRepository weatherUpperWearRepository;
    private final WeatherLowerWearRepository weatherLowerWearRepository;
    private final WeatherOtherWearRepository weatherOtherWearRepository;
    private final MemberTasteService memberTasteService;
    private final TasteService tasteService;
    private  final UvService uvService;
    private final LocationRepository locationRepository;
    private final WeatherRepostory weatherRepostory;

    private WeatherResponseDto weatherResponseDto;

    public TodayRecommendService(HomeWeatherService homeWeatherService, MemberService memberService, ClosetService closetService, WeatherUpperWearRepository weatherUpperWearRepository, WeatherLowerWearRepository weatherLowerWearRepository, WeatherOtherWearRepository weatherOtherWearRepository, MemberTasteService memberTasteService, TasteService tasteService, UvService uvService, LocationRepository locationRepository, WeatherRepostory weatherRepostory) {
        this.homeWeatherService = homeWeatherService;
        this.memberService = memberService;
        this.closetService = closetService;
        this.weatherUpperWearRepository = weatherUpperWearRepository;
        this.weatherLowerWearRepository = weatherLowerWearRepository;
        this.weatherOtherWearRepository = weatherOtherWearRepository;
        this.memberTasteService = memberTasteService;
        this.tasteService = tasteService;
        this.uvService = uvService;
        this.locationRepository = locationRepository;
        this.weatherRepostory = weatherRepostory;
    }

    // 날씨 데이터 초기화
    private void initWeatherData(Long memberId, Integer locationIndex) {
        if (this.weatherResponseDto == null) {
            this.weatherResponseDto = homeWeatherService.getWeatherValue(memberId, locationIndex);
        }
    }

    // 기본 체감온도
    private double getFeelLikeTemp(Long memberId, Integer locationIndex) {
        initWeatherData(memberId, locationIndex);

        try {
            String temperature = weatherResponseDto.getTemperature();
            String humidity = weatherResponseDto.getHumidity();

            double temp = Double.parseDouble(temperature.replace("°C", ""));
            double hum = Double.parseDouble(humidity.replace("%", ""));

            // 습도 50%를 기준으로 10% 증가/감소함에 따라 1°C 증가/감소
            double baseTemp = ((hum - 50) / 10) + temp;

            System.out.println("현재 기온 : " + temp + " 현재 습도 : " + hum + " 기본 체감온도: " + baseTemp);
            return baseTemp;

        } catch (Exception e) {
            e.printStackTrace();
        }
        throw new CustomException(ErrorCode.TEMP_NOT_FOUND);
    }

    // 사용자 별 체감온도
    private Long getWeatherId(Long memberId, Integer locationIndex) {
        double feelLikeTemp = getFeelLikeTemp(memberId, locationIndex);

        // 체질 정보 가져오기
        int constitution = memberService.getConstitutionByMemberId(memberId);

        // 체질에 따른 체감 온도 조절
        feelLikeTemp += (constitution == 0) ? -2 : (constitution == 1) ? 2 : 0;

        System.out.println(memberId + "의 체감온도 : " + feelLikeTemp);

        if (feelLikeTemp >= 27) return 1L;
        else if (feelLikeTemp >= 23) return 2L;
        else if (feelLikeTemp >= 20) return 3L;
        else if (feelLikeTemp >= 17) return 4L;
        else if (feelLikeTemp >= 12) return 5L;
        else if (feelLikeTemp >= 9) return 6L;
        else if (feelLikeTemp >= 5) return 7L;
        else if (feelLikeTemp >= -4) return 8L;
        else return 9L;
    }

    // 추천 리스트 생성
    private RecommendResponseDto getRecommend(Long memberId, Integer locationIndex) {
        List<Location> locations = locationRepository.findLocationsByMember_MemberId(memberId);
        Double latitude = null;
        Double longitude = null;

        for (Location location : locations) {
            if (location.getLocationIndex().equals(locationIndex)) {
                latitude = location.getLatitude();
                longitude = location.getLongitude();
                break;
            }
        }

        if (latitude != null && longitude != null) {
            System.out.println("Latitude: " + latitude + ", Longitude: " + longitude);
        } else {
            System.out.println("Location with index " + locationIndex + " not found.");
        }

        initWeatherData(memberId, locationIndex);

        Long weatherId = getWeatherId(memberId, locationIndex);

        // 사용자 옷장 불러오기
        ClosetResponseDto closet = closetService.getOwnedClothes(memberId);
        List<Long> uppers = closet.getUppers();
        List<Long> lowers = closet.getLowers();
        List<Long> others = closet.getOthers();

        // 사용자 취향 불러오기
        List<Long> taste = memberTasteService.getMemberTasteIds(memberId);

        List<Long> tasteUpper = new ArrayList<>();
        for (Long tasteId : taste) {
            List<Long> tempTasteUpper = tasteService.getClothesByTasteId(tasteId, "upper");
            tasteUpper.addAll(tempTasteUpper);
        }

        List<Long> tasteLower = new ArrayList<>();
        for (Long tasteId : taste) {
            List<Long> tempTasteLower = tasteService.getClothesByTasteId(tasteId, "lower");
            tasteLower.addAll(tempTasteLower);
        }

        List<Long> tasteOther = new ArrayList<>();
        for (Long tasteId : taste) {
            List<Long> tempTasteOther = tasteService.getClothesByTasteId(tasteId, "other");
            tasteOther.addAll(tempTasteOther);
        }

        System.out.println(memberId + "의 정보 - 체감 온도 ID: " + weatherId + " 보유 상의: " + uppers + " 보유 하의 " + lowers + " 보유 기타: " + others + " 취향: ");

        // 상의 추천 리스트
        List<Long> upperRecommend = weatherUpperWearRepository.findByWeatherId(weatherId).stream()
                .map(weatherUpperWear -> weatherUpperWear.getUpperWear().getId())
                .filter(uppers::contains)
                .filter(tasteUpper::contains)
                .collect(Collectors.toList());

        if (upperRecommend.isEmpty()) {
            switch (weatherId.intValue()) {
                case 1 -> upperRecommend.add(1L);
                case 2, 3 -> upperRecommend.add(4L);
                case 4, 5, 6, 7 -> upperRecommend.add(5L);
                case 8, 9 -> upperRecommend.add(8L);
            }
        }

        // 하의 추천 리스트
        List<Long> lowerRecommend = weatherLowerWearRepository.findByWeatherId(weatherId).stream()
                .map(weatherLowerWear -> weatherLowerWear.getLowerWear().getId())
                .filter(lowers::contains)
                .filter(tasteLower::contains)
                .collect(Collectors.toList());

        if (lowerRecommend.isEmpty()) {
            switch (weatherId.intValue()) {
                case 1, 2 -> lowerRecommend.add(9L);
                case 3, 4, 5, 6, 7 -> lowerRecommend.add(2L);
                case 8, 9 -> lowerRecommend.add(10L);
            }
        }

        String rain = weatherResponseDto.getRain();
        String temperature = weatherResponseDto.getTemperature();
        int temp = Integer.parseInt(temperature.replace("°C", ""));
        int uv = uvService.getUV(latitude, longitude);
        if (!rain.equals("0") && !rain.equals("강수 없음")) {
            if (rain.equals("1mm 미만")) {
                weatherId = 10L;
            } else {
                try {
                    String numericPart = rain.replaceAll("[^0-9.]", "");
                    if (!numericPart.isEmpty()) {
                        double rainAmount = Double.parseDouble(numericPart);
                        if (rainAmount >= 5) weatherId = 11L;
                    }
                } catch (NumberFormatException e) {
                    e.printStackTrace();
                }
            }
            if (uv >= 5){
                weatherId = 12L;
                if (temp >= 25) weatherId = 13L;
            }
        }

        System.out.println("최종 weatherID : " + weatherId);

        String weatherInfo = weatherRepostory.findByWeatherId(weatherId);

        // 기타 추천 리스트
        List<Long> otherRecommend = weatherOtherWearRepository.findByWeatherId(weatherId).stream()
                .map(weatherOtherWear -> weatherOtherWear.getOtherWear().getId())
                .filter(others::contains)
                .collect(Collectors.toList());

        System.out.println("추천 리스트 - 상의: " + upperRecommend + " 하의: " + lowerRecommend + " 기타: " + otherRecommend);
        return new RecommendResponseDto(upperRecommend, lowerRecommend, otherRecommend, weatherInfo);
    }

    public RecommendResponseDto getTodayRecommend(Long memberId, Integer locationIndex) {
        this.weatherResponseDto = null;
        return getRecommend(memberId, locationIndex);
    }
}
