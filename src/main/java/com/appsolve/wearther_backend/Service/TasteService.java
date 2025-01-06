package com.appsolve.wearther_backend.Service;

import com.appsolve.wearther_backend.init_data.repository.TasteLowerWearRepository;
import com.appsolve.wearther_backend.init_data.repository.TasteOtherWearRepository;
import com.appsolve.wearther_backend.init_data.repository.TasteUpperWearRepository;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TasteService {

    private final TasteUpperWearRepository tasteUpperWearRepository;
    private final TasteLowerWearRepository tasteLowerWearRepository;
    private final TasteOtherWearRepository tasteOtherWearRepository;

    public TasteService(TasteUpperWearRepository tasteUpperWearRepository, TasteLowerWearRepository tasteLowerWearRepository,
                        TasteOtherWearRepository tasteOtherWearRepository) {
        this.tasteUpperWearRepository = tasteUpperWearRepository;
        this.tasteLowerWearRepository = tasteLowerWearRepository;
        this.tasteOtherWearRepository = tasteOtherWearRepository;
    }

    public List<Long> getClothesByTasteId(Long tasteId, String clothingType) {
        return switch (clothingType) {
            case "upper" -> tasteUpperWearRepository.findByTasteId(tasteId)
                    .stream()
                    .map(tasteUpper -> tasteUpper.getUpperWear().getId())
                    .collect(Collectors.toList());
            case "lower" -> tasteLowerWearRepository.findByTasteId(tasteId)
                    .stream()
                    .map(tasteLower -> tasteLower.getLowerWear().getId())
                    .collect(Collectors.toList());
            case "other" -> tasteOtherWearRepository.findByTasteId(tasteId)
                    .stream()
                    .map(tasteOther -> tasteOther.getOtherWear().getId())
                    .collect(Collectors.toList());
            default -> Collections.emptyList();
        };
    }
}
