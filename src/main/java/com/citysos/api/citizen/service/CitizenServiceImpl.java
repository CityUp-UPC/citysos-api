package com.citysos.api.citizen.service;

import com.citysos.api.citizen.domain.model.entity.Citizen;
import com.citysos.api.citizen.domain.model.persistence.CitizenRepository;
import com.citysos.api.citizen.domain.model.service.CitizenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CitizenServiceImpl implements CitizenService {
    @Autowired
    private CitizenRepository citizenRepository;

    public CitizenServiceImpl(CitizenRepository citizenRepository){
        this.citizenRepository=citizenRepository;
    }
    @Override
    public List<Citizen> fetchAll() {
        return citizenRepository.findAll();
    }

    @Override
    public Optional<Citizen> fetchById(Integer id) {
        return citizenRepository.findById(id);
    }

    @Override
    public Citizen save(Citizen citizen) {
        return citizenRepository.save(citizen);
    }
}
