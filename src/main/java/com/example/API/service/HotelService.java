package com.example.API.service;

import com.example.API.model.entities.Hotel;
import com.example.API.model.repositories.HotelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HotelService {
    @Autowired
    private HotelRepository hotelRepository;

    public List<Hotel> getAllHotels() {
        return hotelRepository.findAll();
    }
    public Hotel getHotelById(long id) {
        return hotelRepository.findById(id).orElse(null);
    }
    public Hotel updateHotel(Hotel hotel){
        return hotelRepository.save(hotel);
    }
    public void deleteHotel(long id){
        hotelRepository.deleteById(id);
    }
}
