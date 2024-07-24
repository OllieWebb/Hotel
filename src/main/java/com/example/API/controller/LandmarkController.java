package com.example.API.controller;

import com.example.API.model.entities.Landmark;
import com.example.API.service.LandmarkService;
import io.swagger.v3.oas.annotations.Parameter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class LandmarkController {

    @Autowired
    LandmarkService landmarkService;

    @GetMapping("/landmarks")
    public List<Landmark> getAllLandmarks(){
        return landmarkService.getAllLandmarks();
    }

    @GetMapping("/landmark/{id}")
    public Landmark getLandmarkById(@PathVariable long id) {
        return landmarkService.getLandmarkById(id);
    }

    @PostMapping("/landmark/")
    public Landmark createLandmark(@RequestBody Landmark landmark){
        return landmarkService.updateLandmark(landmark);
    }

    @PutMapping("/landmark/{id}")
    public Landmark updateLandmark(@Parameter(description = "Update landmark", required = true) @RequestBody Landmark landmark, @PathVariable long id){
        landmark.setId(id);
        return landmarkService.updateLandmark(landmark);
    }

    @DeleteMapping("/landmark/{id}")
    public void deleteLandmark(@PathVariable long id){
        landmarkService.deleteLandmark(id);
    }

}
