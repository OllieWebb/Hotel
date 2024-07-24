package com.example.API.controller;

import com.example.API.model.entities.Hotel;
import com.example.API.service.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class HotelController {

    @Autowired
    HotelService hotelService;

    @GetMapping("/")
    public List<Hotel> getAllLandmarks(){
        return hotelService.getAllHotels();
    }

    @GetMapping("/{id}")
    public Hotel getLandmarkById(@PathVariable long id) {
        return hotelService.getHotelById(id);
    }

    @PostMapping("/")
    public Hotel createLandmark(@RequestBody Hotel hotel){
        return hotelService.updateHotel(hotel);
    }

    @PutMapping("/{id}")
    public Hotel updateLandmark(@RequestBody Hotel landmark, @PathVariable long id){
        return hotelService.updateHotel(hotelService.getHotelById(id));
    }

    @DeleteMapping
    public void deleteLandmark(@PathVariable long id){
        hotelService.deleteHotel(id);
    }

}
