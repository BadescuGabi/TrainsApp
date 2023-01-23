package com.example.trainsapp.service;

import com.example.trainsapp.controller.TrainController;
import com.example.trainsapp.dto.TrainDto;
import com.example.trainsapp.mapper.TrainMapper;
import com.example.trainsapp.model.Train;
import com.example.trainsapp.repository.TrainRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static java.util.Objects.isNull;

@Service
public class TrainService {

    private final TrainRepository trainRepository;

    private final TrainMapper trainMapper;

    private static final Logger log = LoggerFactory.getLogger(TrainController.class);

    public TrainService(TrainRepository trainRepository, TrainMapper trainMapper) {
        this.trainRepository = trainRepository;
        this.trainMapper = trainMapper;
    }


//    public TrainService() {
//    }
//    @Autowired


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
        Train train = trainMapper.convertFromDto(trainDto);
        log.info("Converted from dto: {}", train);
        trainRepository.save(train);
        log.info("Saved into repository: {}", train);
        TrainDto trainDtoNew = trainMapper.convertToDto(train);
        log.info("New trainDTO: {}", trainDtoNew);
       return trainDtoNew;
    }

    public String deleteTrainById(Integer trainId) {
        Train train = trainRepository.findById(trainId).orElseThrow(() -> new RuntimeException(/*LocationConstants.LOCATION_NOT_FOUND_MESSAGE*/));
        trainRepository.deleteById(train.getTrainId());
        return ""/*LocationConstants.DELETE_OK_MESSAGE*/;
    }
}
