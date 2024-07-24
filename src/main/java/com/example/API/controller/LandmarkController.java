package com.example.API.controller;

import com.example.API.model.entities.Landmark;
import com.example.API.service.LandmarkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class LandmarkController {

    @Autowired
    LandmarkService landmarkService;

    @GetMapping("/")
    public List<Landmark> getAllLandmarks(){
        return landmarkService.getAllLandmarks();
    }

    @GetMapping("/{id}")
    public Landmark getLandmarkById(@PathVariable long id) {
        return landmarkService.getLandmarkById(id);
    }

    @PostMapping("/")
    public Landmark createLandmark(@RequestBody Landmark landmark){
        return landmarkService.updateLandmark(landmark);
    }

    @PutMapping("/{id}")
    public Landmark updateLandmark(@RequestBody Landmark landmark, @PathVariable long id){
        return landmarkService.updateLandmark(landmarkService.getLandmarkById(id));
    }

    @DeleteMapping
    public void deleteLandmark(@PathVariable long id){
        landmarkService.deleteLandmark(id);
    }

}
