package com.example.API.controller;

import com.example.API.model.entities.Hotel;
import com.example.API.service.HotelService;
import com.example.API.service.LandmarkService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class HotelController {

    @Autowired
    HotelService hotelService;
    @Autowired
    private LandmarkService landmarkService;

    @GetMapping("/hotels")
    @Operation(summary = "View a list of available hotels")
    public List<Hotel> getAllHotel(){
        return hotelService.getAllHotels();
    }

    @GetMapping("/hotel/{id}")
    @Operation(summary = "Get a hotel by Id")
    public Hotel getHotelById(@PathVariable long id) {
        return hotelService.getHotelById(id);
    }

    @PostMapping("/hotel/")
    @Operation(summary = "Add a hotel")
    public Hotel createHotel(@RequestBody Hotel hotel){
        return hotelService.updateHotel(hotel);
    }

    @PutMapping("/hotel/{id}")
    @Operation(summary = "Update a hotel")
    public Hotel updateHotel(@Parameter(description = "Update hotel", required = true)@RequestBody Hotel hotel, @PathVariable long id){
        hotel.setId(id);
        return hotelService.updateHotel(hotel);
    }

    @DeleteMapping("/hotel/{id}")
    @Operation(summary = "Delete a hotel")
    public void deleteHotel(@PathVariable long id){
        hotelService.deleteHotel(id);
    }
}
