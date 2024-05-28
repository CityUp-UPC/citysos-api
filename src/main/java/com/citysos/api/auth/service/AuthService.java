package com.citysos.api.auth.service;

import com.citysos.api.auth.resource.*;
import com.citysos.api.citizen.domain.model.entity.Citizen;
import com.citysos.api.citizen.domain.model.persistence.CitizenRepository;
import com.citysos.api.police.domain.model.entity.Police;
import com.citysos.api.police.domain.persistence.PoliceRepository;
import lombok.RequiredArgsConstructor;
import com.citysos.api.auth.domain.model.entity.Role;
import com.citysos.api.auth.domain.model.entity.User;
import com.citysos.api.auth.domain.persistence.UserRepository;
import com.citysos.api.auth.jwt.JwtService;
import com.citysos.api.shared.exception.CustomException;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final CitizenRepository citizenRepository;
    private final PoliceRepository policeRepository;

    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    public AuthResponse login(LoginRequest request) {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));

            User user = userRepository.findByUsername(request.getUsername())
                    .orElseThrow(() -> new CustomException("User not found", HttpStatus.NOT_FOUND));

            userRepository.findById(user.getId())
                    .ifPresent(userToUpdate -> {
                        userToUpdate.setDeviceToken(request.getDeviceToken());
                        userRepository.save(userToUpdate);
                    });

            String token = jwtService.getToken(user);
            return AuthResponse.builder().token(token).build();
        } catch (Exception ex) {
            throw new CustomException("Authentication failed: " + ex.getMessage(), HttpStatus.UNAUTHORIZED);
        }
    }

    public AuthResponse changePassword(ChangePasswordRequest request) {

        if (!request.getNewPassword().equals(request.getConfirmPassword())) {
            throw new CustomException("New passwords do not match", HttpStatus.BAD_REQUEST);
        }

        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));

        UserDetails user = userRepository.findByUsername(request.getUsername())
                .orElseThrow(() -> new CustomException("User not found", HttpStatus.NOT_FOUND));

        userRepository.findById(((User) user).getId())
                .ifPresent(userToUpdate -> {
                    userToUpdate.setPassword(passwordEncoder.encode(request.getNewPassword()));
                    userRepository.save(userToUpdate);
                });

        String token = jwtService.getToken(user);
        return AuthResponse.builder()
                .token(token)
                .build();
    }


    public AuthResponse registerPolice(RegisterRequestPolice request) {
        User user = User.builder()
                .username(request.getUsername())
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .phoneNumber(request.getPhoneNumber())
                .dni(request.getDni())
                .deviceToken(request.getDeviceToken())
                .role(Role.POLICE)
                .build();

        userRepository.save(user);

        Police police = new Police();
        police.setAssignDistrict(request.getAssignedDistrict());
        police.setInService(false);
        police.setPoliceRank(request.getPoliceRank());
        police.setPoliceIdentifier(request.getPoliceIdentifier());
        police.setEntityPolice(request.getEntityPolice());
        police.setUser(user);

        policeRepository.save(police);

        return AuthResponse.builder()
                .token(jwtService.getToken(user))
                .build();

    }

    public AuthResponse registerCitizen(RegisterRequestUser request) {
        User user = User.builder()
                .username(request.getUsername())
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .phoneNumber(request.getPhoneNumber())
                .dni(request.getDni())
                .role(Role.USER)
                .deviceToken(request.getDeviceToken())
                .build();

        userRepository.save(user);

        Citizen citizen = new Citizen();
        citizen.setDistrict(request.getDistrict());
        citizen.setUser(user);

        citizenRepository.save(citizen);

        return AuthResponse.builder()
                .token(jwtService.getToken(user))
                .build();

    }

    public boolean findUserById(Integer id) {
        return userRepository.existsById(id);
    }

    public void updateDeviceToken(Integer userId, String deviceToken) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new CustomException("User not found", HttpStatus.NOT_FOUND));
        user.setDeviceToken(deviceToken);
        userRepository.save(user);
    }
}
