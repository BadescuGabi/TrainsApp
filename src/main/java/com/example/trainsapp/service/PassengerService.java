package com.example.trainsapp.service;

import com.example.trainsapp.dto.PassengerDto;
import com.example.trainsapp.dto.TrainDto;
import com.example.trainsapp.mapper.PassengerMapper;
import com.example.trainsapp.model.Passenger;
import com.example.trainsapp.model.Train;
import com.example.trainsapp.repository.PassengerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static java.util.Objects.isNull;

@Service
public class PassengerService {
    private final PassengerRepository passengerRepository;
    private final PassengerMapper passengerMapper;

    @Autowired
    public PassengerService(PassengerRepository passengerRepository, PassengerMapper passengerMapper) {
        this.passengerRepository = passengerRepository;
        this.passengerMapper = passengerMapper;
    }

    public List<PassengerDto> retrievePassengers() {
        return passengerRepository.findAll()
                .stream().map(passengerMapper::convertToDto)
                .collect(Collectors.toList());
    }

    public PassengerDto updatePassenger (int passengerId, PassengerDto updatedPassenger){
        Passenger passenger = passengerRepository.findById(passengerId).orElseThrow(() -> new RuntimeException(/*message*/));
        passenger.setAge(updatedPassenger.getAge());
        passenger.setName(updatedPassenger.getName());

        return passengerMapper.convertToDto(passengerRepository.save(passenger));
    }

    public PassengerDto savePassenger(PassengerDto passengerDto){
        return passengerMapper.convertToDto(passengerRepository.save((passengerMapper.convertFromDto(passengerDto))));
    }

    public String deletePassengerById(Integer passengerId) {
        Passenger passenger = passengerRepository.findById(passengerId).orElseThrow(() -> new RuntimeException(/*LocationConstants.LOCATION_NOT_FOUND_MESSAGE*/));
        passengerRepository.deleteById(passenger.getPassengerId());
        return ""/*LocationConstants.DELETE_OK_MESSAGE*/;
    }
}
