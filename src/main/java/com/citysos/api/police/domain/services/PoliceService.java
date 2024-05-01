package com.citysos.api.police.domain.services;

import com.citysos.api.auth.domain.models.entities.UserEntity;

import java.util.List;


public interface PoliceService {
    Long getPoliceId();
    List<UserEntity> getAllUsersPolices();
}
