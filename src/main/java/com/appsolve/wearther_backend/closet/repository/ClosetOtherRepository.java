package com.appsolve.wearther_backend.closet.repository;

import com.appsolve.wearther_backend.closet.entity.ClosetOther;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClosetOtherRepository  extends JpaRepository<ClosetOther, Long> {
    List<ClosetOther> findByClosetId(Long memberId);
}
