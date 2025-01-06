package com.appsolve.wearther_backend.init_data.service;

import com.appsolve.wearther_backend.init_data.repository.LowerWearRepository;
import org.springframework.stereotype.Service;

@Service
public class LowerWearService {
    private final LowerWearRepository lowerWearRepository;

    public LowerWearService(LowerWearRepository lowerWearRepository) {
        this.lowerWearRepository = lowerWearRepository;
    }

    public String getLowerNameById(Long id) {
        return lowerWearRepository.findNameById(id);
    }
}
