package com.example.API.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import com.example.API.model.entities.Hotel;
import com.example.API.model.repositories.HotelRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class HotelServiceTest {

    @InjectMocks
    private HotelService hotelService;

    @Mock
    private HotelRepository hotelRepository;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetAllHotels() {
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

        when(hotelRepository.findAll()).thenReturn(Arrays.asList(hotel1, hotel2));

        // Act
        List<Hotel> hotels = hotelService.getAllHotels();

        // Assert
        assertThat(hotels).hasSize(2);
        assertThat(hotels.get(0).getName()).isEqualTo("Hilton");
        assertThat(hotels.get(1).getName()).isEqualTo("Marriott");
    }

    @Test
    public void testGetHotelById() {
        // Arrange
        Hotel hotel = new Hotel();
        hotel.setId(1L);
        hotel.setName("Hilton");
        hotel.setHotelType("Luxury");
        hotel.setAddress("hotel address");
        hotel.setCountryCode("FR");

        when(hotelRepository.findById(1L)).thenReturn(Optional.of(hotel));

        // Act
        Hotel foundHotel = hotelService.getHotelById(1L);

        // Assert
        assertThat(foundHotel).isNotNull();
        assertThat(foundHotel.getName()).isEqualTo("Hilton");
    }

    @Test
    public void testGetHotelById_NotFound() {
        // Arrange
        when(hotelRepository.findById(1L)).thenReturn(Optional.empty());

        // Act
        Hotel foundHotel = hotelService.getHotelById(1L);

        // Assert
        assertThat(foundHotel).isNull();
    }

    @Test
    public void testUpdateHotel() {
        // Arrange
        Hotel hotel = new Hotel();
        hotel.setId(1L);
        hotel.setName("Hilton");
        hotel.setHotelType("Luxury");
        hotel.setAddress("hotel address");
        hotel.setCountryCode("FR");

        when(hotelRepository.save(hotel)).thenReturn(hotel);

        // Act
        Hotel updatedHotel = hotelService.updateHotel(hotel);

        // Assert
        assertThat(updatedHotel).isNotNull();
        assertThat(updatedHotel.getName()).isEqualTo("Hilton");
    }

    @Test
    public void testDeleteHotel() {
        // Act
        hotelService.deleteHotel(1L);

        // Assert
        verify(hotelRepository, times(1)).deleteById(1L);
    }
}
