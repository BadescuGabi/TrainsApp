package com.example.trainsapp.mapper;

import com.example.trainsapp.dto.TrackDto;
import com.example.trainsapp.dto.TrainDto;
import com.example.trainsapp.model.Track;
import com.example.trainsapp.model.Train;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import static java.util.Objects.isNull;

@Component
public class TrackMapper {

    private final StationMapper stationMapper;
    @Autowired
    public TrackMapper(StationMapper stationMapper) {
        this.stationMapper = stationMapper;
    }


    public Track convertFromDto(TrackDto trackDto) {
        if (isNull(trackDto)) {
            return null;
        }

            return new Track(
                    trackDto.getTrackId(),
                    trackDto.getDistance(),
                    stationMapper.convertFromDto(trackDto.getStation1Id()),
                    stationMapper.convertFromDto(trackDto.getStation2Id())
            );
        }


    public TrackDto convertToDto(Track track) {
        if (isNull(track)) {
            return null;
        }
        return new TrackDto(
                track.getTrackId(),
                track.getDistance(),
                stationMapper.convertToDto(track.getStation1Id()),
                stationMapper.convertToDto(track.getStation2Id())
        );
    }
}
