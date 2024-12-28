package com.appsolve.wearther_backend.sevice;

import com.appsolve.wearther_backend.entity.*;
import com.appsolve.wearther_backend.repository.*;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class InitService {

    @Autowired private TasteRepository tasteRepository;
    @Autowired private TasteLowerWearRepository tasteLowerWearRepository;
    @Autowired private LowerWearRepository lowerWearRepository;
    @Autowired private UpperWearRepository upperWearRepository;
    @Autowired private TasteUpperWearRepository tasteUpperWearRepository;

    @PostConstruct
    public void init() {
        if (tasteRepository.count() == 0) {
            setTaste();
        }
        if (upperWearRepository.count() == 0) {
            setUpperWear();
        }
        if (lowerWearRepository.count() == 0) {
            setLowerWear();
        }
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

    private void setLowerWear() {
        insertLowerPantsData();
        matchTasteForLowerPants();
        insertLowerSkirtData();
        matchTasteForLowerSkirt();
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

    private void matchTasteForLowerPants() {
        Map<Long, List<Long>> tasteToLowerPants = new HashMap<>();
        tasteToLowerPants.put(1L, Arrays.asList(1L, 3L, 5L, 6L, 10L));
        tasteToLowerPants.put(2L, Arrays.asList(2L, 3L, 4L, 6L, 9L, 10L) );
        tasteToLowerPants.put(3L, Arrays.asList(2L, 3L, 4L, 9L, 10L));
        tasteToLowerPants.put(4L, Arrays.asList(2L, 3L, 9L, 10L));
        tasteToLowerPants.put(5L, Arrays.asList(3L, 4L, 7L,  8L, 9L, 10L));
        tasteToLowerPants.put(6L, Arrays.asList(2L, 3L, 4L, 5L, 7L, 9L, 10L));
        tasteToLowerPants.put(7L, Arrays.asList(3L, 7L,  8L, 9L, 10L));
        tasteToLowerPants.put(8L, Arrays.asList(1L, 3L, 5L, 6L, 10L));

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

    private void matchTasteForLowerSkirt() {
        Map<Long, List<Long>> tasteToLowerSkirt = new HashMap<>();
        tasteToLowerSkirt.put(1L, Arrays.asList(11L, 12L, 13L));
        tasteToLowerSkirt.put(3L, Arrays.asList(12L, 13L));
        tasteToLowerSkirt.put(4L, Arrays.asList(12L, 13L));
        tasteToLowerSkirt.put(5L, List.of(11L));
        tasteToLowerSkirt.put(7L, List.of(13L));
        tasteToLowerSkirt.put(8L, List.of(11L));

        for (Map.Entry<Long, List<Long>> entry : tasteToLowerSkirt.entrySet()) {
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

    private void setUpperWear() {
        insertUpperInnerWearData();
        matchTasteForUpperInnerWear();
        insertUpperOuterWearData();
        matchTasteForUpperOuterWear();
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

    private void matchTasteForUpperInnerWear() {
        Map<Long, List<Long>> tasteToUpperInnerWearMap = new HashMap<>();
        tasteToUpperInnerWearMap.put(1L, Arrays.asList(1L, 4L, 5L, 6L, 8L));
        tasteToUpperInnerWearMap.put(2L, Arrays.asList(1L, 4L, 5L, 7L, 8L));
        tasteToUpperInnerWearMap.put(3L, Arrays.asList(5L, 6L, 8L));
        tasteToUpperInnerWearMap.put(4L, Arrays.asList(1L, 2L, 4L, 5L, 8L));
        tasteToUpperInnerWearMap.put(5L, Arrays.asList(2L, 3L, 8L));
        tasteToUpperInnerWearMap.put(6L, Arrays.asList(2L, 3L, 4L, 7L, 8L));
        tasteToUpperInnerWearMap.put(7L, Arrays.asList(1L, 2L, 3L, 7L, 8L));
        tasteToUpperInnerWearMap.put(8L, Arrays.asList(3L, 6L, 8L));

        for (Map.Entry<Long, List<Long>> entry : tasteToUpperInnerWearMap.entrySet()) {
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

    private void matchTasteForUpperOuterWear() {
        Map<Long, List<Long>> tasteToUpperOuterWearMap = new HashMap<>();
        tasteToUpperOuterWearMap.put(1L, Arrays.asList(9L, 11L, 14L, 15L));
        tasteToUpperOuterWearMap.put(2L, Arrays.asList(10L, 13L, 14L));
        tasteToUpperOuterWearMap.put(3L, Arrays.asList(9L, 11L, 13L, 17L));
        tasteToUpperOuterWearMap.put(4L, Arrays.asList(9L, 10L, 11L, 13L, 14L, 15L));
        tasteToUpperOuterWearMap.put(5L, Arrays.asList(10L, 12L, 18L, 19L, 20L, 21L));
        tasteToUpperOuterWearMap.put(6L, Arrays.asList(12L, 16L, 17L, 18L, 19L, 20L, 21L));
        tasteToUpperOuterWearMap.put(7L, Arrays.asList(10L, 16L, 17L, 19L, 20L, 21L));
        tasteToUpperOuterWearMap.put(8L, List.of(12L));

        for (Map.Entry<Long, List<Long>> entry : tasteToUpperOuterWearMap.entrySet()) {
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
}