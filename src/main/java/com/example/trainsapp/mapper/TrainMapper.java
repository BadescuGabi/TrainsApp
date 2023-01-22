package com.example.trainsapp.mapper;

import com.example.trainsapp.dto.TrainDto;
import com.example.trainsapp.model.Train;
import org.springframework.stereotype.Component;

import static java.util.Objects.isNull;

@Component
public class TrainMapper {

    public Train convertFromDto(TrainDto trainDto) {
        if (isNull(trainDto)) {
            return null;
        }
        return new Train(
                trainDto.getTrainId(),
                trainDto.getTrainModel(),
                trainDto.getSeatsNo(),
                trainDto.getFabricationYear()
        );
    }

    public TrainDto convertToDto(Train train) {
        if (isNull(train)) {
            return null;
        }
        return new TrainDto(
                train.getTrainId(),
                train.getTrainModel(),
                train.getSeatsNo(),
                train.getFabricationYear()
        );
    }
}
