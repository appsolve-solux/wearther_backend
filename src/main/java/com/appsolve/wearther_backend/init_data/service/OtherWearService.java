package com.appsolve.wearther_backend.init_data.service;

import com.appsolve.wearther_backend.init_data.repository.OtherWearRepository;
import org.springframework.stereotype.Service;

@Service
public class OtherWearService {
    private final OtherWearRepository otherWearRepository;

    public OtherWearService(OtherWearRepository otherWearRepository) {
        this.otherWearRepository = otherWearRepository;
    }

    public String getOtherNameById(Long id) {
        return otherWearRepository.findNameById(id);
    }
}
