package com.citysos.api.police.application.implement;

import com.citysos.api.auth.application.implement.UserDetailsServiceImpl;
import com.citysos.api.auth.domain.models.entities.UserEntity;
import com.citysos.api.auth.domain.models.enums.ERole;
import com.citysos.api.police.domain.services.PoliceService;
import com.citysos.api.police.infrastructure.repositories.PoliceRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PoliceServiceImpl implements PoliceService {

    private final PoliceRepository policeRepository;
    private final ModelMapper modelMapper;
    private final PasswordEncoder passwordEncoder;
    private final UserDetailsServiceImpl userDetailsService;

    @Override
    public Long getPoliceId() {
        return userDetailsService.getCurrentUser().getId();
    }

    @Override
    public List<UserEntity> getAllUsersPolices() {
        return policeRepository.findAll()
                .stream()
                .filter(x -> x.getRoles().stream().anyMatch(y -> y.getRole().equals(ERole.POLICE)))
                .toList();
    }
}
