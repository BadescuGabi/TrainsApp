package com.example.trainsapp.mapper;

import com.example.trainsapp.dto.StationDto;
import com.example.trainsapp.model.Station;
import org.springframework.stereotype.Component;

import static java.util.Objects.isNull;

@Component
public class StationMapper {

    public Station convertFromDto(StationDto stationDto) {
        if (isNull(stationDto)) {
            return null;
        }

        return new Station(
                stationDto.getStationId(),
                stationDto.getCity(),
                stationDto.getContact()
        );
    }


    public StationDto convertToDto(Station station) {
        if (isNull(station)) {
            return null;
        }
        return new StationDto(
                station.getStationId(),
                station.getCity(),
                station.getContact()
                );
    }
}
