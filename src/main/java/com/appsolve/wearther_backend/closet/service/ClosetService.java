package com.appsolve.wearther_backend.closet.service;

import com.appsolve.wearther_backend.closet.dto.ClosetResponseDto;
import com.appsolve.wearther_backend.closet.repository.ClosetLowerRepository;
import com.appsolve.wearther_backend.closet.repository.ClosetOtherRepository;
import com.appsolve.wearther_backend.closet.repository.ClosetUpperRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ClosetService {
    @Autowired private ClosetUpperRepository closetUpperRepository;
    @Autowired private ClosetLowerRepository closetLowerRepository;
    @Autowired private ClosetOtherRepository closetOtherRepository;

    public ClosetResponseDto getClothes(Long memberId) {
        List<Long> uppers = closetUpperRepository.findByClosetId(memberId)
                .stream()
                .map(closetUpper -> closetUpper.getUpperWear().getId())
                .collect(Collectors.toList());

        List<Long> lowers = closetLowerRepository.findByClosetId(memberId)
                .stream()
                .map(closetLower -> closetLower.getLowerWear().getId())
                .collect(Collectors.toList());

        List<Long> others = closetOtherRepository.findByClosetId(memberId)
                .stream()
                .map(closetOther -> closetOther.getOtherWear().getId())
                .collect(Collectors.toList());

        return new ClosetResponseDto(uppers, lowers, others);
    }
}
