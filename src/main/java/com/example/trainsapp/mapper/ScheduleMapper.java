package com.example.trainsapp.mapper;

import com.example.trainsapp.dto.ScheduleDto;
import com.example.trainsapp.model.Schedule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import static java.util.Objects.isNull;

@Component
public class ScheduleMapper {

    private final TrackMapper trackMapper;
    private final TrainMapper trainMapper;

    @Autowired
    public ScheduleMapper(TrackMapper trackMapper, TrainMapper trainMapper) {
        this.trackMapper = trackMapper;
        this.trainMapper = trainMapper;
    }

    public Schedule convertFromDto(ScheduleDto scheduleDto) {
        if (isNull(scheduleDto)) {
            return null;
        }

        return new Schedule(
                scheduleDto.getScheduleId(),
                scheduleDto.getDate(),
                scheduleDto.getDuration(),
                scheduleDto.getHasDelay(),
                trainMapper.convertFromDto(scheduleDto.getTrainDto()),
                trackMapper.convertFromDto(scheduleDto.getTrackDto())

        );
    }


    public ScheduleDto convertToDto(Schedule schedule) {
        if (isNull(schedule)) {
            return null;
        }
        return new ScheduleDto(
                schedule.getScheduleId(),
                schedule.getDate(),
                schedule.getDuration(),
                schedule.getHasDelay(),
                trainMapper.convertToDto(schedule.getTrain()),
                trackMapper.convertToDto(schedule.getTrack())
                );
    }
}
