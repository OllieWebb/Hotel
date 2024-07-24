package com.example.API.controller;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.Arrays;

import com.example.API.model.entities.Hotel;
import com.example.API.service.HotelService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(HotelController.class)
public class HotelControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private HotelService hotelService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetAllHotel() throws Exception {
        // Arrange
        Hotel hotel1 = new Hotel();
        hotel1.setId(1L);
        hotel1.setName("Hilton");
        hotel1.setHotelType("Luxury");
        hotel1.setAddress("hotel address");
        hotel1.setCountryCode("FR");

        Hotel hotel2 = new Hotel();
        hotel2.setId(2L);
        hotel2.setName("Marriott");
        hotel2.setHotelType("Budget");
        hotel2.setAddress("hotel address2");
        hotel2.setCountryCode("UK");

        when(hotelService.getAllHotels()).thenReturn(Arrays.asList(hotel1, hotel2));

        // Act & Assert
        mockMvc.perform(get("/hotels"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].name").value("Hilton"))
                .andExpect(jsonPath("$[1].name").value("Marriott"));
    }

    @Test
    public void testGetHotelById() throws Exception {
        // Arrange
        Hotel hotel = new Hotel();
        hotel.setId(1L);
        hotel.setName("Hotel One");
        hotel.setHotelType("Luxury");
        hotel.setAddress("123 Main St");
        hotel.setCountryCode("US");

        when(hotelService.getHotelById(1L)).thenReturn(hotel);

        // Act & Assert
        mockMvc.perform(get("/hotel/1"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.name").value("Hotel One"));
    }

    @Test
    public void testCreateHotel() throws Exception {
        // Arrange
        Hotel hotel = new Hotel();
        hotel.setId(1L);
        hotel.setName("Hotel One");
        hotel.setHotelType("Luxury");
        hotel.setAddress("123 Main St");
        hotel.setCountryCode("US");

        when(hotelService.updateHotel(any(Hotel.class))).thenReturn(hotel);

        // Act & Assert
        mockMvc.perform(post("/hotel/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"id\":1,\"name\":\"Hotel One\",\"hotelType\":\"Luxury\",\"address\":\"123 Main St\",\"countryCode\":\"US\"}"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.name").value("Hotel One"));
    }

    @Test
    public void testUpdateHotel() throws Exception {
        // Arrange
        Hotel existingHotel = new Hotel();
        existingHotel.setId(1L);
        existingHotel.setName("Hotel One");
        existingHotel.setHotelType("Luxury");
        existingHotel.setAddress("123 Main St");
        existingHotel.setCountryCode("US");

        Hotel updatedHotel = new Hotel();
        updatedHotel.setId(1L);
        updatedHotel.setName("Updated Hotel");
        updatedHotel.setHotelType("Luxury");
        updatedHotel.setAddress("123 Main St");
        updatedHotel.setCountryCode("US");

        when(hotelService.getHotelById(1L)).thenReturn(existingHotel);
        when(hotelService.updateHotel(any(Hotel.class))).thenReturn(updatedHotel);

        // Act & Assert
        mockMvc.perform(put("/hotel/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"id\":1,\"name\":\"Updated Hotel\",\"hotelType\":\"Luxury\",\"address\":\"123 Main St\",\"countryCode\":\"US\"}"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.name").value("Updated Hotel"));
    }

    @Test
    public void testDeleteHotel() throws Exception {
        // Arrange
        doNothing().when(hotelService).deleteHotel(1L);

        // Act & Assert
        mockMvc.perform(delete("/hotel/1"))
                .andExpect(status().isOk());

        verify(hotelService, times(1)).deleteHotel(1L);
    }
}
