package com.example.trainsapp.service;

import com.example.trainsapp.dto.ScheduleDto;
import com.example.trainsapp.dto.TicketDto;
import com.example.trainsapp.mapper.ScheduleMapper;
import com.example.trainsapp.model.*;
import com.example.trainsapp.repository.ScheduleRepository;
import com.example.trainsapp.repository.TicketRepository;
import com.example.trainsapp.repository.TrackRepository;
import com.example.trainsapp.repository.TrainRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static java.util.Objects.isNull;

@Service
public class ScheduleService {
    private final ScheduleRepository scheduleRepository;
    private final TrackRepository trackRepository;
    private final TrainRepository trainRepository;


    private final ScheduleMapper scheduleMapper;

    @Autowired
    public ScheduleService(ScheduleRepository scheduleRepository, TrackRepository trackRepository, TrainRepository trainRepository, ScheduleMapper scheduleMapper) {
        this.scheduleRepository = scheduleRepository;
        this.trackRepository = trackRepository;
        this.trainRepository = trainRepository;
        this.scheduleMapper = scheduleMapper;
    }

    public List<ScheduleDto> retrieveSchedules() {
        return scheduleRepository.findAll()
                .stream().map(scheduleMapper::convertToDto)
                .collect(Collectors.toList());
    }

    public ScheduleDto findScheduleById(int scheduleId) {
        Schedule schedule = scheduleRepository.findById(scheduleId).orElseThrow();
        return scheduleMapper.convertToDto(schedule);
    }

    public ScheduleDto updateSchedule(int scheduleId, ScheduleDto updatedScheduleDto) {
        Schedule schedule = scheduleRepository.findById(scheduleId).orElseThrow();
//            Track newTrackDto = getTrackDtoWithStation (updatedTrackDto, track);
        if (!isNull(updatedScheduleDto.getTrackDto())) {
            Track track = trackRepository.findById(updatedScheduleDto.getTrackDto().getTrackId()).orElseThrow();
            schedule.setTrack(track);
        }
        if (!isNull(updatedScheduleDto.getTrainDto())) {
            Train train = trainRepository.findById(updatedScheduleDto.getTrainDto().getTrainId()).orElseThrow();
            schedule.setTrain(train);
        }
        schedule.setDate(updatedScheduleDto.getDate());
        schedule.setDuration(updatedScheduleDto.getDuration());
        schedule.setHasDelay(updatedScheduleDto.getHasDelay());
        return scheduleMapper.convertToDto(scheduleRepository.save(schedule));
    }

    public ScheduleDto saveScheduleDto(ScheduleDto scheduleDto) {
        Schedule schedule = scheduleMapper.convertFromDto(scheduleDto);
//            Track newTrackDto = getTrackDtoWithStation (updatedTrackDto, track);
        if (!isNull(scheduleDto.getTrackDto())) {
            Track track = trackRepository.findById(scheduleDto.getTrackDto().getTrackId()).orElseThrow();
            schedule.setTrack(track);
        }
        if (!isNull(scheduleDto.getTrainDto())) {
            Train train = trainRepository.findById(scheduleDto.getTrainDto().getTrainId()).orElseThrow();
            schedule.setTrain(train);
        }
        schedule.setDate(scheduleDto.getDate());
        schedule.setDuration(scheduleDto.getDuration());
        schedule.setHasDelay(scheduleDto.getHasDelay());
        return scheduleMapper.convertToDto(scheduleRepository.save(schedule));
    }

    public String deleteScheduleById(Integer scheduleId) {
        Schedule schedule = scheduleRepository.findById(scheduleId).orElseThrow(() -> new RuntimeException(/*LocationConstants.LOCATION_NOT_FOUND_MESSAGE*/));
        scheduleRepository.deleteById(schedule.getScheduleId());
        return ""/*LocationConstants.DELETE_OK_MESSAGE*/;
    }
}
