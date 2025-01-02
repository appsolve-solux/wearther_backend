package com.appsolve.wearther_backend.init_data;

import com.appsolve.wearther_backend.init_data.entity.*;
import com.appsolve.wearther_backend.init_data.repository.*;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class InitService {

    @Autowired private WeatherRepostory weatherRepository;
    @Autowired private TasteRepository tasteRepository;
    @Autowired private TasteLowerWearRepository tasteLowerWearRepository;
    @Autowired private LowerWearRepository lowerWearRepository;
    @Autowired private UpperWearRepository upperWearRepository;
    @Autowired private TasteUpperWearRepository tasteUpperWearRepository;
    @Autowired private WeatherUpperWearRepository weatherUpperWearRepository;
    @Autowired private WeatherLowerWearRepository weatherLowerWearRepository;
    @Autowired private OtherWearRepository otherWearRepository;
    @Autowired private TasteOtherWearRepository tasteOtherWearRepository;
    @Autowired private WeatherOtherWearRepository weatherOtherWearRepository;

    @PostConstruct
    public void init() {
        if (weatherRepository.count() == 0) {
            setWeather();
        }
        if (tasteRepository.count() == 0) {
            setTaste();
        }
        if (upperWearRepository.count() == 0) {
            setUpperWear();
        }
        if (lowerWearRepository.count() == 0) {
            setLowerWear();
        }
        if (otherWearRepository.count() == 0) {
            setOtherWear();
        }
    }

    public void setWeather() {
        String[] weatherNames = {"뜨거운", "포근한", "선선한", "쌀쌀한", "서늘한", "차가운", "추운", "매서운", "얼어붙은", "비",
                "많은 비", "햇빛 셈", "햇빛 센 여름"};
        String[] weatherDescriptions = {"체감 온도 27 이상", "체감 온도 23 ~ 26", "체감 온도 20 ~ 22", "체감 온도 17 ~ 19",
                "체감 온도 12 ~ 16", "체감 온도 9 ~ 11", "체감 온도 5 ~ 8", "체감 온도 -4 ~ 4", "체감 온도 -5 이하",
                "강수량 0 초과", "강수량 5 이상", "UV 5 이상", "UV 5 이상이며, 기온 25도 이상"};

        List<Weather> weatherList = new ArrayList<>();
        for (int i = 0; i < weatherNames.length; i++) {
            Weather weather = Weather.builder()
                    .name(weatherNames[i])
                    .description(weatherDescriptions[i])
                    .build();
            weatherList.add(weather);
        }
        weatherRepository.saveAll(weatherList);
    }

    private void setTaste() {
        List<String> tasteNames = Arrays.asList("페미닌", "모던", "드뮤어", "올드머니", "힙", "캐주얼", "고프코어", "Y2K");
        List<Taste> tasteList = new ArrayList<>();

        for (String name : tasteNames) {
            Taste taste = Taste.builder()
                    .name(name)
                    .build();
            tasteList.add(taste);
        }
        tasteRepository.saveAll(tasteList);
    }

    private void setUpperWear() {
        insertUpperInnerWearData();
        insertUpperOuterWearData();

        matchTasteForUpperWear();
        matchWeatherForUpperWear();
    }

    private void setLowerWear() {
        insertLowerPantsData();
        insertLowerSkirtData();

        matchTasteForLowerWear();
        matchWeatherForLowerWear();
    }

    private void setOtherWear() {
        insertOtherWear();

        matchTasteForOtherWear();
        matchWeatherForOtherWear();
    }

    public void insertUpperInnerWearData() {
        List<String> upperInnerWearNames = Arrays.asList("민소매", "반소매", "맨투맨/후드티", "셔츠/블라우스", "니트",
                "오프숄더", "히트택", "기모제품");

        for (String name : upperInnerWearNames) {
            UpperWear upperWear = UpperWear.builder()
                    .name(name)
                    .type(0)
                    .build();
            upperWearRepository.save(upperWear);
        }
    }

    public void insertUpperOuterWearData() {
        List<String> upperOuterWearNames = Arrays.asList("가디건", "사파리자켓", "트위드자켓", "레더자켓", "트렌치코트",
                "숏코트", "무스탕", "경량 패딩", "롱패딩", "숏패딩", "점퍼", "후드집업", "바람막이");

        for (String name : upperOuterWearNames) {
            UpperWear upperWear = UpperWear.builder()
                    .name(name)
                    .type(1)
                    .build();
            upperWearRepository.save(upperWear);
        }
    }

    private void matchTasteForUpperWear() {
        Map<Long, List<Long>> tasteToUpperWearMap = new HashMap<>();
        tasteToUpperWearMap.put(1L, Arrays.asList(1L, 4L, 5L, 6L, 8L, 9L, 11L, 14L, 15L));
        tasteToUpperWearMap.put(2L, Arrays.asList(1L, 4L, 5L, 7L, 8L, 10L, 13L, 14L));
        tasteToUpperWearMap.put(3L, Arrays.asList(5L, 6L, 8L, 9L, 11L, 13L, 17L));
        tasteToUpperWearMap.put(4L, Arrays.asList(2L, 4L, 5L, 8L, 9L, 10L, 11L, 13L, 14L, 15L));
        tasteToUpperWearMap.put(5L, Arrays.asList(1L, 2L, 3L, 8L, 10L, 12L, 18L, 19L, 20L, 21L));
        tasteToUpperWearMap.put(6L, Arrays.asList(2L, 3L, 4L, 7L, 8L, 12L, 16L, 17L, 18L, 19L, 20L, 21L));
        tasteToUpperWearMap.put(7L, Arrays.asList(1L, 2L, 3L, 7L, 8L, 10L, 16L, 17L, 19L, 20L, 21L));
        tasteToUpperWearMap.put(8L, Arrays.asList(3L, 6L, 8L, 12L));

        for (Map.Entry<Long, List<Long>> entry : tasteToUpperWearMap.entrySet()) {
            Long tasteId = entry.getKey();
            List<Long> upperWearIds = entry.getValue();

            Taste taste = tasteRepository.findById(tasteId)
                    .orElseThrow(() -> new IllegalArgumentException("Invalid Taste ID: " + tasteId));

            for (Long upperWearId : upperWearIds) {
                UpperWear upperWear = upperWearRepository.findById(upperWearId)
                        .orElseThrow(() -> new IllegalArgumentException("Invalid UpperWear ID: " + upperWearId));

                TasteUpperWear tasteUpperWear = TasteUpperWear.builder()
                        .taste(taste)
                        .upperWear(upperWear)
                        .build();
                tasteUpperWearRepository.save(tasteUpperWear);
            }
        }
    }

    private void matchWeatherForUpperWear() {
        Map<Long, List<Long>> weatherToUpperWearMap = new HashMap<>();
        weatherToUpperWearMap.put(1L, Arrays.asList(1L, 2L, 6L));
        weatherToUpperWearMap.put(2L, Arrays.asList(2L, 4L, 6L, 21L));
        weatherToUpperWearMap.put(3L, Arrays.asList(3L, 4L, 6L, 9L, 20L, 21L));
        weatherToUpperWearMap.put(4L, Arrays.asList(3L, 5L, 9L, 10L, 14L, 20L, 21L));
        weatherToUpperWearMap.put(5L, Arrays.asList(3L, 5L, 9L, 10L, 11L,12L, 13L, 14L, 19L, 20L));
        weatherToUpperWearMap.put(6L, Arrays.asList(5L, 10L, 11L, 12L, 13L, 15L, 16L, 19L));
        weatherToUpperWearMap.put(7L, Arrays.asList(5L, 7L, 15L, 16L, 19L));
        weatherToUpperWearMap.put(8L, Arrays.asList(7L, 8L, 17L, 18L));
        weatherToUpperWearMap.put(9L, Arrays.asList(7L, 8L, 17L, 18L));

        for (Map.Entry<Long, List<Long>> entry : weatherToUpperWearMap.entrySet()) {
            Long weatherId = entry.getKey();
            List<Long> upperWearIds = entry.getValue();

            Weather weather = weatherRepository.findById(weatherId)
                    .orElseThrow(() -> new IllegalArgumentException("Invalid Weather ID: " + weatherId));

            for (Long upperWearId : upperWearIds) {
                UpperWear upperWear = upperWearRepository.findById(upperWearId)
                        .orElseThrow(() -> new IllegalArgumentException("Invalid UpperWear ID: " + upperWearId));

                WeatherUpperWear weatherUpperWear = WeatherUpperWear.builder()
                        .weather(weather)
                        .upperWear(upperWear)
                        .build();
                weatherUpperWearRepository.save(weatherUpperWear);
            }
        }
    }

    private void insertLowerPantsData() {
        List<String> lowerPants = Arrays.asList("숏팬츠", "슬랙스", "데님", "와이드팬츠", "스키니",
                "부츠컷", "조거팬츠", "점프수트", "반비지", "기모바지");

        for (String name : lowerPants) {
            LowerWear lowerWear = LowerWear.builder()
                    .name(name)
                    .type(0)
                    .build();
            lowerWearRepository.save(lowerWear);
        }
    }

    private void insertLowerSkirtData() {
        List<String> lowerSkirt = Arrays.asList("미니스커트", "미디스커트", "롱스커트");

        for (String name : lowerSkirt) {
            LowerWear lowerWear = LowerWear.builder()
                    .name(name)
                    .type(1)
                    .build();
            lowerWearRepository.save(lowerWear);
        }
    }

    private void matchTasteForLowerWear() {
        Map<Long, List<Long>> tasteToLowerPants = new HashMap<>();
        tasteToLowerPants.put(1L, Arrays.asList(1L, 3L, 5L, 6L, 10L, 11L, 12L, 13L));
        tasteToLowerPants.put(2L, Arrays.asList(2L, 3L, 4L, 6L, 9L, 10L) );
        tasteToLowerPants.put(3L, Arrays.asList(2L, 3L, 4L, 9L, 10L, 12L, 13L));
        tasteToLowerPants.put(4L, Arrays.asList(2L, 3L, 9L, 10L, 12L, 13L));
        tasteToLowerPants.put(5L, Arrays.asList(3L, 4L, 7L,  8L, 9L, 10L, 11L));
        tasteToLowerPants.put(6L, Arrays.asList(2L, 3L, 4L, 5L, 7L, 9L, 10L));
        tasteToLowerPants.put(7L, Arrays.asList(3L, 7L,  8L, 9L, 10L, 13L));
        tasteToLowerPants.put(8L, Arrays.asList(1L, 3L, 5L, 6L, 10L, 11L));

        for (Map.Entry<Long, List<Long>> entry : tasteToLowerPants.entrySet()) {
            Long tasteId = entry.getKey();
            List<Long> lowerWearId = entry.getValue();

            Taste taste = tasteRepository.findById(tasteId)
                    .orElseThrow(() -> new IllegalArgumentException("Invalid Taste ID: " + tasteId));

            for (Long wearId : lowerWearId) {
                LowerWear lowerWear = lowerWearRepository.findById(wearId)
                        .orElseThrow(() -> new IllegalArgumentException("Invalid UpperWear ID: " + wearId));

                TasteLowerWear tasteUpperWear = TasteLowerWear.builder()
                        .taste(taste)
                        .lowerWear(lowerWear )
                        .build();
                tasteLowerWearRepository.save(tasteUpperWear);
            }
        }
    }

    private void matchWeatherForLowerWear() {
        Map<Long, List<Long>> weatherToLowerWearMap = new HashMap<>();
        weatherToLowerWearMap.put(1L, Arrays.asList(1L, 5L, 9L, 11L));
        weatherToLowerWearMap.put(2L, Arrays.asList(1L, 5L, 9L, 11L));
        weatherToLowerWearMap.put(3L, Arrays.asList(2L, 3L, 4L, 6L, 7L, 8L, 12L, 13L));
        weatherToLowerWearMap.put(4L, Arrays.asList(2L, 3L, 4L, 6L, 7L, 8L, 12L, 13L));
        weatherToLowerWearMap.put(5L, Arrays.asList(2L, 3L, 4L, 6L, 7L, 8L, 12L, 13L));
        weatherToLowerWearMap.put(6L, Arrays.asList(2L, 3L, 4L, 7L));
        weatherToLowerWearMap.put(7L, Arrays.asList(2L, 3L, 4L, 7L));
        weatherToLowerWearMap.put(8L, Arrays.asList(4L, 7L, 10L));
        weatherToLowerWearMap.put(9L, List.of(10L));

        for (Map.Entry<Long, List<Long>> entry : weatherToLowerWearMap.entrySet()) {
            Long weatherId = entry.getKey();
            List<Long> lowerWearIds = entry.getValue();

            Weather weather = weatherRepository.findById(weatherId)
                    .orElseThrow(() -> new IllegalArgumentException("Invalid Weather ID: " + weatherId));

            for (Long lowerWearId : lowerWearIds) {
                LowerWear lowerWear = lowerWearRepository.findById(lowerWearId)
                        .orElseThrow(() -> new IllegalArgumentException("Invalid UpperWear ID: " + lowerWearId));

                WeatherLowerWear weatherLowerWear = WeatherLowerWear.builder()
                        .weather(weather)
                        .lowerWear(lowerWear)
                        .build();
                weatherLowerWearRepository.save(weatherLowerWear);
            }
        }
    }

    private void insertOtherWear() {
        List<String> otherWearNames = Arrays.asList("쪼리", "목도리, 장갑", "어그, 귀마개", "우산", "장화",
                "양산", "비니, 벙거지", "볼캡", "선글라스");

        for (String name : otherWearNames) {
            OtherWear otherWear = OtherWear.builder()
                    .name(name)
                    .build();
            otherWearRepository.save(otherWear);
        }
    }

    private void matchTasteForOtherWear() {
        Map<Long, List<Long>> tasteToOtherWear = new HashMap<>();
        tasteToOtherWear.put(1L, List.of(6L));
        tasteToOtherWear.put(2L, List.of(6L));
        tasteToOtherWear.put(3L, List.of(6L));
        tasteToOtherWear.put(4L, List.of(6L));
        tasteToOtherWear.put(5L, List.of(7L));
        tasteToOtherWear.put(6L, List.of(8L));
        tasteToOtherWear.put(7L, List.of(8L));
        tasteToOtherWear.put(8L, List.of(8L));

        for (Map.Entry<Long, List<Long>> entry : tasteToOtherWear.entrySet()) {
            Long tasteId = entry.getKey();
            List<Long> otherWearId = entry.getValue();

            Taste taste = tasteRepository.findById(tasteId)
                    .orElseThrow(() -> new IllegalArgumentException("Invalid Taste ID: " + tasteId));

            for (Long wearId : otherWearId) {
                OtherWear otherWear = otherWearRepository.findById(wearId)
                        .orElseThrow(() -> new IllegalArgumentException("Invalid UpperWear ID: " + wearId));

                TasteOtherWear tasteOtherWear = TasteOtherWear.builder()
                        .taste(taste)
                        .otherWear(otherWear)
                        .build();
                tasteOtherWearRepository.save(tasteOtherWear);
            }
        }
    }

    private void matchWeatherForOtherWear() {
        Map<Long, List<Long>> weatherToOtherWearMap = new HashMap<>();
        weatherToOtherWearMap.put(1L, List.of(1L));
        weatherToOtherWearMap.put(7L, List.of(2L));
        weatherToOtherWearMap.put(8L, List.of(2L));
        weatherToOtherWearMap.put(9L, List.of(3L));

        weatherToOtherWearMap.put(10L, List.of(4L));
        weatherToOtherWearMap.put(11L, List.of(5L));
        weatherToOtherWearMap.put(12L, Arrays.asList(6L, 7L, 8L));
        weatherToOtherWearMap.put(13L, List.of(9L));


        for (Map.Entry<Long, List<Long>> entry : weatherToOtherWearMap.entrySet()) {
            Long weatherId = entry.getKey();
            List<Long> otherWearId = entry.getValue();

            Weather weather = weatherRepository.findById(weatherId)
                    .orElseThrow(() -> new IllegalArgumentException("Invalid Weather ID: " + weatherId));

            for (Long wearId : otherWearId) {
                OtherWear otherWear = otherWearRepository.findById(wearId)
                        .orElseThrow(() -> new IllegalArgumentException("Invalid UpperWear ID: " + wearId));

                WeatherOtherWear weatherOtherWear = WeatherOtherWear.builder()
                        .weather(weather)
                        .otherWear(otherWear)
                        .build();
                weatherOtherWearRepository.save(weatherOtherWear);
            }
        }
    }

}