package com.example.API.service;

import com.example.API.model.entities.Hotel;
import com.example.API.model.entities.Landmark;
import com.example.API.model.repositories.LandmarkRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LandmarkService {

    private LandmarkRepository landmarkRepository;

    public List<Landmark> getAllLandmarks() {
        return landmarkRepository.findAll();
    }
    public Landmark getLandmarkById(long id) {
        return landmarkRepository.findById(id).orElse(null);
    }
    public Landmark createOrUpdateLandmark(Landmark landmark){
        return landmarkRepository.save(landmark);
    }
    public void deleteLandmark(long id){
        landmarkRepository.deleteById(id);
    }
}
