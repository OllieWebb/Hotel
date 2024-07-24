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

    @GetMapping("/hotels")
    public List<Hotel> getAllHotel(){
        return hotelService.getAllHotels();
    }

    @GetMapping("/hotel/{id}")
    public Hotel getHotelById(@PathVariable long id) {
        return hotelService.getHotelById(id);
    }

    @PostMapping("/hotel/")
    public Hotel createHotel(@RequestBody Hotel hotel){
        return hotelService.updateHotel(hotel);
    }

    @PutMapping("/hotel/{id}")
    public Hotel updateHotel(@RequestBody Hotel hotel, @PathVariable long id){
        return hotelService.updateHotel(hotelService.getHotelById(id));
    }

    @DeleteMapping("/hotel/{id}")
    public void deleteHotel(@PathVariable long id){
        hotelService.deleteHotel(id);
    }

}
