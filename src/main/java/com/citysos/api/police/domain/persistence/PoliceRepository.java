package com.citysos.api.police.domain.persistence;

import com.citysos.api.police.domain.model.entity.Police;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface PoliceRepository extends JpaRepository<Police, Integer> {

    @Query(nativeQuery = true, value = "SELECT * FROM polices WHERE in_service = 1")
    List<Police> findPoliceInService();

    @Transactional
    @Modifying
    @Query(nativeQuery = true, value = "UPDATE polices SET in_service = 0 WHERE id = :id")
    void OffInService(Integer id);

    @Transactional
    @Modifying
    @Query(nativeQuery = true, value = "UPDATE polices SET in_service = 1 WHERE id = :id")
    void OnInService(Integer id);

    @Query(nativeQuery = true, value = "SELECT * FROM polices WHERE user_id = :userId")
    Police findByUserId(Integer userId);
}
