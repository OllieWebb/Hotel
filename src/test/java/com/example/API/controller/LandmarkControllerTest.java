package com.example.API.controller;

import com.example.API.model.entities.Landmark;
import com.example.API.service.LandmarkService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class LandmarkControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private LandmarkService landmarkService;

    @Test
    public void testGetAllLandmarks() throws Exception {
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

        when(landmarkService.getAllLandmarks()).thenReturn(landmarks);

        final MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.get(
                "/landmarks"
        );

        mvc.perform(builder)
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name").value("Eiffel Tower"))
                .andExpect(jsonPath("$[1].name").value("Statue of Liberty"));
    }

    @Test
    public void testGetLandmarkById() throws Exception {
        Landmark landmark1 = new Landmark();
        landmark1.setId(1L);
        landmark1.setName("Eiffel Tower");
        landmark1.setDescription("Big tower");
        landmark1.setCategory("Tower");
        landmark1.setCountryCode("FR");

        when(landmarkService.getLandmarkById(1L)).thenReturn(landmark1);

        final MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.get(
                "/landmark/1"
        );

        mvc.perform(builder)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Eiffel Tower"))
                .andExpect(jsonPath("$.countryCode").value("FR"));
    }

    @Test
    public void testCreateLandmark() throws Exception {
        Landmark landmark1 = new Landmark();
        landmark1.setId(1L);
        landmark1.setName("Eiffel Tower");
        landmark1.setDescription("Big tower");
        landmark1.setCategory("Tower");
        landmark1.setCountryCode("FR");

        when(landmarkService.updateLandmark(any(Landmark.class))).thenReturn(landmark1);

        final MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.post(
                "/landmark/"
        );

        mvc.perform(builder
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"id\":1,\"name\":\"Eiffel Tower\",\"description\":\"Big tower\",\"category\":\"Tower\",\"countryCode\":\"FR\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Eiffel Tower"));
    }

    @Test
    public void testUpdateLandmark() throws Exception {
        Landmark landmark1 = new Landmark();
        landmark1.setId(1L);
        landmark1.setName("Eiffel Tower");
        landmark1.setDescription("Big tower");
        landmark1.setCategory("Tower");
        landmark1.setCountryCode("FR");

        when(landmarkService.updateLandmark(any(Landmark.class))).thenReturn(landmark1);

        final MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.put(
                "/landmark/1"
        );

        mvc.perform(builder
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"id\":1,\"name\":\"Eiffel Tower\",\"description\":\"Big tower\",\"category\":\"Tower\",\"countryCode\":\"FR\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Eiffel Tower"));
    }

    @Test
    public void testDeleteLandmark() throws Exception {
        doNothing().when(landmarkService).deleteLandmark(1L);

        final MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.delete(
                "/landmark/1"
        );

        mvc.perform(builder)
                .andExpect(status().isOk());

        verify(landmarkService, times(1)).deleteLandmark(1L);
    }
}
