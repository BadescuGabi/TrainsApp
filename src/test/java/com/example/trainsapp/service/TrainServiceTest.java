package com.example.trainsapp.service;

import com.example.trainsapp.dto.TrainDto;
import com.example.trainsapp.mapper.TrainMapper;
import com.example.trainsapp.model.Train;
import com.example.trainsapp.repository.TrainRepository;
import com.example.trainsapp.utils.TrainMocks;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.hibernate.validator.internal.util.Contracts.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class TrainServiceTest {
    @InjectMocks
    TrainService trainService;

    @Mock
    TrainRepository trainRepository;

    @Mock
    TrainMapper trainMapper;

    Train train;

    TrainDto trainDto;

    @Test
    public void testRetrieveAllTrains(){
        //GIVEN
        train = TrainMocks.mockTrain();
        trainDto = TrainMocks.mockTrainDto();

        List<Train> trains = new ArrayList<>();
        trains.add(train);
        List<TrainDto> trainDtos = new ArrayList<>();
        trainDtos.add(trainDto);

        //WHEN
        when(trainRepository.findAll()).thenReturn(trains);
        when(trainMapper.convertToDto(train)).thenReturn(trainDto);

        //THEN
        List<TrainDto> result = trainService.retrieveTrains();
        assertEquals(result, trainDtos);
    }

    @Test
    public void testSaveTrain() {
        //GIVEN
        train = TrainMocks.mockTrain();
        trainDto = TrainMocks.mockTrainDto();

        //WHEN
        when(trainMapper.convertFromDto(trainDto)).thenReturn(train);
        when(trainMapper.convertToDto(train)).thenReturn(trainDto);
        when(trainRepository.save(train)).thenReturn(train);

        //THEN
        TrainDto result = trainService.saveTrain(trainDto);
        assertEquals(result, trainDto);
    }

    @Test
    public void testUpdateTrain() {
        //GIVEN
        train = TrainMocks.mockTrain();
        trainDto = TrainMocks.mockTrainDto();

        //WHEN
        when(trainRepository.findById(trainDto.getTrainId())).thenReturn(Optional.ofNullable(train));
        when(trainMapper.convertToDto(train)).thenReturn(trainDto);
        when(trainRepository.save(train)).thenReturn(train);

        //THEN
        TrainDto result = trainService.updateTrain(train.getTrainId(), trainDto);
        assertEquals(result, trainDto);
    }

    @Test
    public void testDeleteTrain() {
        //GIVEN
        train = TrainMocks.mockTrain();
        trainDto = TrainMocks.mockTrainDto();

        //WHEN
        when(trainRepository.findById(trainDto.getTrainId())).thenReturn(Optional.ofNullable(train));

        //THEN
        String result = trainService.deleteTrainById(train.getTrainId());
        assertEquals(result, "");
    }
}
