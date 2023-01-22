package com.example.trainsapp.mapper;

import com.example.trainsapp.dto.PassengerDto;
import com.example.trainsapp.model.Passenger;
import org.springframework.stereotype.Component;

import static java.util.Objects.isNull;

@Component
public class PassengerMapper {

    public Passenger convertFromDto(PassengerDto passengerDto) {
        if (isNull(passengerDto)) {
            return null;
        }

        return new Passenger(
                passengerDto.getPassengerId(),
                passengerDto.getName(),
                passengerDto.getAge()
        );
    }

    public PassengerDto convertToDto(Passenger passenger) {
        if (isNull(passenger)) {
            return null;
        }
        return new PassengerDto(
                passenger.getPassengerId(),
                passenger.getName(),
                passenger.getAge()
                );
    }
}
