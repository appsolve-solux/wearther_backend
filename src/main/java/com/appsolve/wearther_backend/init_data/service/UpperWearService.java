package com.appsolve.wearther_backend.init_data.service;

import com.appsolve.wearther_backend.init_data.repository.UpperWearRepository;
import org.springframework.stereotype.Service;

@Service
public class UpperWearService {
    private final UpperWearRepository upperWearRepository;

    public UpperWearService(UpperWearRepository upperWearRepository) {
        this.upperWearRepository = upperWearRepository;
    }

    public String getUpperNameById(Long id) {
        return upperWearRepository.findNameById(id);
    }
}