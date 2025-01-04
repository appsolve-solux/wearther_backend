package com.appsolve.wearther_backend.closet.repository;

import com.appsolve.wearther_backend.closet.entity.Closet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ClosetRepository extends JpaRepository<Closet, Long> {
    @Query("SELECT c FROM Closet c WHERE c.id = :id")
    Optional<Closet> findClosetById(Long id);
}
