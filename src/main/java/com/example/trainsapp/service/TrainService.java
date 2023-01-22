package com.example.trainsapp.service;

import com.example.trainsapp.dto.TrainDto;
import com.example.trainsapp.mapper.TrainMapper;
import com.example.trainsapp.model.Train;
import com.example.trainsapp.repository.TrainRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static java.util.Objects.isNull;

@Service
public class TrainService {
    private final TrainRepository trainRepository;
    private final TrainMapper trainMapper;

    @Autowired
    public TrainService(TrainRepository trainRepository, TrainMapper trainMapper) {
        this.trainRepository = trainRepository;
        this.trainMapper = trainMapper;
    }

    public List<TrainDto> retrieveTrains() {
        return trainRepository.findAll()
                .stream().map(trainMapper::convertToDto)
                .collect(Collectors.toList());
    }

    public TrainDto updateTrain (int trainId, TrainDto updatedTrain){
        Train train = trainRepository.findById(trainId).orElseThrow(() -> new RuntimeException(/*message*/));
        if (!isNull(updatedTrain.getTrainModel())) {
            train.setTrainModel(updatedTrain.getTrainModel());
        }
        train.setFabricationYear(updatedTrain.getFabricationYear());
        train.setSeatsNo(updatedTrain.getSeatsNo());

        return trainMapper.convertToDto(trainRepository.save(train));
    }


    public TrainDto saveTrain(TrainDto trainDto){
       return trainMapper.convertToDto(trainRepository.save((trainMapper.convertFromDto(trainDto))));
    }

    public String deleteTrainById(Integer trainId) {
        Train train = trainRepository.findById(trainId).orElseThrow(() -> new RuntimeException(/*LocationConstants.LOCATION_NOT_FOUND_MESSAGE*/));
        trainRepository.deleteById(train.getTrainId());
        return ""/*LocationConstants.DELETE_OK_MESSAGE*/;
    }
}
