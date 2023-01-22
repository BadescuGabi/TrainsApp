package com.example.trainsapp.service;

import com.example.trainsapp.dto.StationDto;
import com.example.trainsapp.mapper.StationMapper;
import com.example.trainsapp.model.Station;
import com.example.trainsapp.repository.StationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class StationService {
    private StationRepository stationRepository;
    private StationMapper stationMapper;

    @Autowired
    public StationService(StationRepository stationRepository, StationMapper stationMapper) {
        this.stationRepository = stationRepository;
        this.stationMapper = stationMapper;
    }


    public List<StationDto> retrieveStations() {
        return stationRepository.findAll()
                .stream().map(stationMapper::convertToDto)
                .collect(Collectors.toList());
    }

    public StationDto findStationById(int stationId) {
        Station station = stationRepository.findById(stationId).orElseThrow();
        return stationMapper.convertToDto(station);
    }

    public StationDto updateStation(int stationId, StationDto updatedStationDto) {
        Station station = stationRepository.findById(stationId).orElseThrow();
        station.setCity(updatedStationDto.getCity());
        station.setContact(updatedStationDto.getContact());
//            Track newTrackDto = getTrackDtoWithStation (updatedTrackDto, track);
        return stationMapper.convertToDto(stationRepository.save(station));
    }

    public StationDto saveStation(StationDto stationDto) {
        Station station = stationMapper.convertFromDto(stationDto);
//            Track newTrackDto = getTrackDtoWithStation (updatedTrackDto, track);

        return stationMapper.convertToDto(stationRepository.save(station));
    }

    public String deleteStationById(Integer stationId) {
        Station station = stationRepository.findById(stationId).orElseThrow(() -> new RuntimeException(/*LocationConstants.LOCATION_NOT_FOUND_MESSAGE*/));
        stationRepository.deleteById(station.getStationId());
        return ""/*LocationConstants.DELETE_OK_MESSAGE*/;
    }
}
