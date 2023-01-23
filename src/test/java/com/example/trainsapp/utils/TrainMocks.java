package com.example.trainsapp.utils;

import com.example.trainsapp.dto.TrainDto;
import com.example.trainsapp.model.Train;
import org.springframework.stereotype.Component;

@Component
public class TrainMocks {

    public static Train mockTrain(){
        return Train.builder()
                .trainId(1)
                .trainModel("ASC22")
                .fabricationYear(1992)
                .seatsNo(45)
                .build();
    }

    public static TrainDto mockTrainDto(){
        return TrainDto.builder()
                .trainId(5)
                .trainModel("AZ93")
                .seatsNo(20)
                .fabricationYear(2021)
                .build();
    }
}
