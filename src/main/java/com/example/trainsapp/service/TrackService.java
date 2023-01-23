package com.example.trainsapp.service;

import com.example.trainsapp.constants.Constants;
import com.example.trainsapp.dto.TrackDto;
import com.example.trainsapp.exception.StationNotFoundException;
import com.example.trainsapp.mapper.TrackMapper;
import com.example.trainsapp.model.Station;
import com.example.trainsapp.model.Track;
import com.example.trainsapp.repository.StationRepository;
import com.example.trainsapp.repository.TrackRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static java.util.Objects.isNull;

@Service
public class TrackService {
    private TrackRepository trackRepository;
    private StationRepository stationRepository;
    private TrackMapper trackMapper;

    @Autowired
    public TrackService(TrackRepository trackRepository, StationRepository stationRepository, TrackMapper trackMapper) {
        this.trackRepository = trackRepository;
        this.stationRepository = stationRepository;
        this.trackMapper = trackMapper;
    }

    public List<TrackDto> retrieveTracks() {
        List<TrackDto> list = trackRepository.findAll()
                .stream().map(trackMapper::convertToDto)
                .collect(Collectors.toList());
        return list;
    }

    public TrackDto findTrackById(int trackId) {
        Track track = trackRepository.findById(trackId).orElseThrow();
        return trackMapper.convertToDto(track);
    }

    public TrackDto updateTrack(int trackId, TrackDto updatedTrackDto) {
        Track track = trackRepository.findById(trackId).orElseThrow();
//            Track newTrackDto = getTrackDtoWithStation (updatedTrackDto, track);
        if (!isNull(updatedTrackDto.getStation1Id()) && !isNull(updatedTrackDto.getStation1Id())) {
            Station station1 = stationRepository.findById(updatedTrackDto.getStation1Id().getStationId())
                    .orElseThrow(() -> new RuntimeException(Constants.STATION_NOT_FOUND));
            Station station2 = stationRepository.findById(updatedTrackDto.getStation2Id().getStationId())
                    .orElseThrow(() -> new RuntimeException(Constants.STATION_NOT_FOUND));
            track.setStation1Id(station1);
            track.setStation2Id(station2);
        }
        track.setDistance(updatedTrackDto.getDistance());
        return trackMapper.convertToDto(trackRepository.save(track));
    }

    public TrackDto saveTrack(TrackDto trackDto) {
        Track track = trackMapper.convertFromDto(trackDto);
//            Track newTrackDto = getTrackDtoWithStation (updatedTrackDto, track);
        if (!isNull(trackDto.getStation1Id()) && !isNull(trackDto.getStation1Id())) {
            Station station1 = stationRepository.findById(trackDto.getStation1Id().getStationId())
                    .orElseThrow(() -> new RuntimeException(Constants.STATION_NOT_FOUND));
            Station station2 = stationRepository.findById(trackDto.getStation2Id().getStationId())
                    .orElseThrow(() -> new RuntimeException(Constants.STATION_NOT_FOUND));
            track.setStation1Id(station1);
            track.setStation2Id(station2);
        }
        track.setDistance(trackDto.getDistance());
        return trackMapper.convertToDto(trackRepository.save(track));
    }

    public String deleteTrackById(Integer trackId) {
        Track track = trackRepository.findById(trackId).orElseThrow(() -> new RuntimeException(Constants.ID_NOT_FOUND));
        trackRepository.deleteById(track.getTrackId());
        return ""/*LocationConstants.DELETE_OK_MESSAGE*/;
    }
}