package com.example.API.service;

import com.example.API.model.entities.Landmark;
import com.example.API.model.repositories.LandmarkRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.*;

public class LandmarkServiceTest {
    @InjectMocks
    private LandmarkService landmarkService;

    @Mock
    private LandmarkRepository landmarkRepository;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetAllLandmarks() {
        Landmark landmark1 = new Landmark();
        landmark1.setId(1L);
        landmark1.setName("Eiffel Tower");
        landmark1.setDescription("Big tower");
        landmark1.setCategory("Tower");
        landmark1.setCountryCode("FR");

        Landmark landmark2 = new Landmark();
        landmark2.setId(2L);
        landmark2.setName("Statue of Liberty");
        landmark2.setDescription("Big lady");
        landmark2.setCategory("Statue");
        landmark2.setCountryCode("US");

        List<Landmark> landmarks = Arrays.asList(landmark1, landmark2);
        when(landmarkRepository.findAll()).thenReturn(landmarks);

        List<Landmark> result = landmarkService.getAllLandmarks();
        assertEquals(2, result.size());
        assertEquals("Eiffel Tower", result.get(0).getName());
        assertEquals("Statue of Liberty", result.get(1).getName());
    }

    @Test
    public void testGetLandmarkById() {
        Landmark landmark = new Landmark();
        landmark.setId(1L);
        landmark.setName("Eiffel Tower");
        landmark.setDescription("Big tower");
        landmark.setCategory("Tower");
        landmark.setCountryCode("FR");

        when(landmarkRepository.findById(1L)).thenReturn(Optional.of(landmark));

        Landmark result = landmarkService.getLandmarkById(1L);
        assertEquals("Eiffel Tower", result.getName());
        assertEquals("FR", result.getCountryCode());
    }

    @Test
    public void testGetLandmarkByIdNotFound() {
        when(landmarkRepository.findById(anyLong())).thenReturn(Optional.empty());

        Landmark result = landmarkService.getLandmarkById(1L);
        assertNull(result);
    }

    @Test
    public void testUpdateLandmark() {
        Landmark landmark = new Landmark();
        landmark.setId(1L);
        landmark.setName("Eiffel Tower");
        landmark.setDescription("Big tower");
        landmark.setCategory("Tower");
        landmark.setCountryCode("FR");

        when(landmarkRepository.save(any(Landmark.class))).thenReturn(landmark);

        Landmark result = landmarkService.updateLandmark(landmark);
        assertEquals("Eiffel Tower", result.getName());
        assertEquals("FR", result.getCountryCode());
    }

    @Test
    public void testDeleteLandmark() {
        doNothing().when(landmarkRepository).deleteById(anyLong());

        landmarkService.deleteLandmark(1L);
        verify(landmarkRepository, times(1)).deleteById(1L);
    }
}
