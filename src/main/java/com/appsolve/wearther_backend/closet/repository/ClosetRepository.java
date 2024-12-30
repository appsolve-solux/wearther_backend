package com.appsolve.wearther_backend.closet.repository;

import com.appsolve.wearther_backend.closet.entity.Closet;
import com.appsolve.wearther_backend.closet.entity.ClosetLower;
import com.appsolve.wearther_backend.closet.entity.ClosetOther;
import com.appsolve.wearther_backend.closet.entity.ClosetUpper;
import com.appsolve.wearther_backend.member.Member;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ClosetRepository extends JpaRepository<Closet, Long> {
    @Query("SELECT c FROM Closet c WHERE c.id = :id")
    Optional<Closet> findClosetById(Long id);

    @Query("SELECT cu FROM ClosetUpper cu JOIN FETCH cu.closet c WHERE c.id = :closetId")
    List<ClosetUpper> findClosetUppersByClosetId(Long closetId);

    @Query("SELECT cl FROM ClosetLower cl JOIN FETCH cl.closet c WHERE c.id = :closetId")
    List<ClosetLower> findClosetLowersByClosetId(Long closetId);

    @Query("SELECT co FROM ClosetOther co JOIN FETCH co.closet c WHERE c.id = :closetId")
    List<ClosetOther> findClosetOthersByClosetId(Long closetId);

}
