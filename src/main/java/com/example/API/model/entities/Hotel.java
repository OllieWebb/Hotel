package com.example.API.model.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Hotel {
    @Id
    private long id;
    private String name;
    private String hotelType;
    private String address;
    private String countryCode;


}
