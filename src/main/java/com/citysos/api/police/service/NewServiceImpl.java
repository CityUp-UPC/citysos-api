package com.citysos.api.police.service;

import com.citysos.api.police.domain.model.entity.New;
import com.citysos.api.police.domain.persistence.NewRepository;
import com.citysos.api.police.domain.service.NewService;
import com.citysos.api.shared.exception.CustomException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class NewServiceImpl implements NewService {
    @Autowired
    private NewRepository newRepository;

    public NewServiceImpl(NewRepository newRepository){
        this.newRepository = newRepository;
    }

    @Override
    public List<New> fetchAll(){ return newRepository.findAll(); }

    @Override
    public List<New> fetchByDistrict(String district) {
        return newRepository.findByDistrict(district);
    }

    @Override
    public List<New> fetchByPoliceId(Integer policeId) {
        return newRepository.findByPoliceId(policeId);
    }

    @Override
    public Optional<New> fetchById(Integer id) {
        return newRepository.findById(id);
    }

    @Override
    public New save(New newEntity) {
        return newRepository.save(newEntity);
    }

    @Override
    public Optional<New> update(New newEntity) {
        var newToUpdate = newRepository.findById(newEntity.getId()).orElseThrow(() -> new CustomException("New not found", HttpStatus.NOT_FOUND));
        newToUpdate.setDescription(newEntity.getDescription());
        newToUpdate.setDistrict(newEntity.getDistrict());
        newToUpdate.setPolice(newEntity.getPolice());
        newToUpdate.setImages(newEntity.getImages());

        newRepository.save(newToUpdate);
        return Optional.of(newToUpdate);
    }

    @Override
    public boolean delete(Integer id){
        var newToDelete = newRepository.findById(id).orElseThrow(() -> new CustomException("New not found", HttpStatus.NOT_FOUND));
        newRepository.delete(newToDelete);
        return true;
    }


}
