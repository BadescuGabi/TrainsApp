package com.example.trainsapp.controller;

import com.example.trainsapp.dto.TrainDto;
import com.example.trainsapp.model.Train;
import com.example.trainsapp.repository.TrainRepository;
import com.example.trainsapp.service.TrainService;
import com.example.trainsapp.utils.TrainMocks;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

//@ExtendWith(MockitoExtension.class)
@WebMvcTest(controllers = TrainController.class)
public class TrainControllerIT {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;
    @MockBean
    TrainService trainService;
    TrainDto trainDto;

    @MockBean
    TrainRepository trainRepository;

    @Test
    public void retrieveTrainsTest() throws Exception {
        //GIVEN
        trainDto = TrainMocks.mockTrainDto();

        List<TrainDto> trainDtos = new ArrayList<>();
        trainDtos.add(trainDto);

        //WHEN
        when(trainService.retrieveTrains()).thenReturn(trainDtos);

        //THEN
        mockMvc.perform(get("/train"))
                .andExpect(status().isOk())
                .andExpect(content().string(objectMapper.writeValueAsString(trainDtos)));
    }

    @Test
    public void addNewTrainTest() throws Exception {
        //GIVEN
        trainDto = TrainMocks.mockTrainDto();

        //WHEN
        when(trainService.saveTrain(trainDto)).thenReturn(trainDto);


        //THEN
        mockMvc.perform(post("/train/add")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(trainDto)))
                .andExpect(status().isOk());

    }

    //
    @Test
    public void editTrainTest() throws Exception {
        //GIVEN
        trainDto = TrainMocks.mockTrainDto();
        //WHEN
        when(trainService.updateTrain(trainDto.getTrainId(), trainDto)).thenReturn(trainDto);

        //THEN
        mockMvc.perform(put("/train/edit/" + trainDto.getTrainId())
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(trainDto)))
                .andExpect(status().isOk());

    }

    //
    @Test
    public void deleteTrainTest() throws Exception {
        //GIVEN
        trainDto = TrainMocks.mockTrainDto();
        //WHEN
        when(trainService.deleteTrainById(trainDto.getTrainId())).thenReturn("Train was deleted");

        //THEN
        mockMvc.perform(delete("/train/delete/"  + trainDto.getTrainId()))
                .andExpect(status().isOk())
                .andExpect(content().string("Train was deleted"));
    }
}

