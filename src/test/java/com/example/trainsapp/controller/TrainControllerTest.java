package com.example.trainsapp.controller;


import com.example.trainsapp.dto.TrainDto;
import com.example.trainsapp.service.TrainService;
import com.example.trainsapp.utils.TrainMocks;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
public class TrainControllerTest {

    @InjectMocks
    TrainController trainController;

    @Mock
    TrainService trainService;
    TrainDto trainDto;


    @Test
    public void retrieveTrainsTest(){
        trainDto = TrainMocks.mockTrainDto();
        List<TrainDto> trainDtos = new ArrayList<>();
        trainDtos.add(trainDto);

        //WHEN
        when(trainService.retrieveTrains()).thenReturn(trainDtos);

        //THEN
        ResponseEntity<List<TrainDto>> result = trainController.retrieveTrains();
        assertEquals(result.getBody(), trainDtos);
        assertEquals(result.getStatusCode().value(), 200);
    }

    @Test
    public void addNewTrainTest() {
        //GIVEN
        trainDto = TrainMocks.mockTrainDto();
        //WHEN
        when(trainService.saveTrain(trainDto)).thenReturn(trainDto);

        //THEN
        ResponseEntity<TrainDto> result = trainController.addTrain(trainDto);
        assertEquals(result.getBody(), trainDto);
        assertEquals(result.getStatusCode().value(), 200);
    }

    @Test
    public void editTrainTest() {
        //GIVEN
        trainDto = TrainMocks.mockTrainDto();
        //WHEN
        when(trainService.updateTrain(trainDto.getTrainId(),trainDto)).thenReturn(trainDto);

        //THEN
        ResponseEntity<TrainDto> result = trainController.editTrain(trainDto.getTrainId(),trainDto);
        assertEquals(result.getBody(), trainDto);
        assertEquals(result.getStatusCode().value(), 200);
    }

    @Test
    public void deleteTrainTest() {
        //GIVEN
        trainDto = TrainMocks.mockTrainDto();
        //WHEN
        when(trainService.deleteTrainById(trainDto.getTrainId())).thenReturn("Train was deleted");

        //THEN
        ResponseEntity<String> result = trainController.deleteTrain(trainDto.getTrainId());
        assertEquals(result.getBody(), "Train was deleted");
        assertEquals(result.getStatusCode().value(), 200);
    }
}
