package com.example.trainsapp.controller;

import com.example.trainsapp.dto.ScheduleDto;
import com.example.trainsapp.dto.TrainDto;
import com.example.trainsapp.service.ScheduleService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/schedule")
public class ScheduleController {
    private final ScheduleService scheduleService;

    public ScheduleController(ScheduleService scheduleService) {
        this.scheduleService = scheduleService;
    }


    @GetMapping
    public ResponseEntity<List<ScheduleDto>> retrieveSchedules() {
        return ResponseEntity.ok().body(scheduleService.retrieveSchedules());
    }

    @PostMapping("/add")
    public ResponseEntity<ScheduleDto> addSchedule(@RequestBody ScheduleDto scheduleDto) {
        return ResponseEntity.ok().body(scheduleService.saveScheduleDto(scheduleDto));
    }

    @PutMapping("/edit/{scheduleId}")
    public ResponseEntity<ScheduleDto> editSchedule(@PathVariable int scheduleId, @RequestBody ScheduleDto editedScheduleDto) {
        return ResponseEntity.ok().body(scheduleService.updateSchedule(scheduleId, editedScheduleDto));
    }

    @DeleteMapping("/delete/{scheduleId}")
    public ResponseEntity<String> deleteSchedule(@PathVariable int scheduleId) {
        String message = scheduleService.deleteScheduleById(scheduleId);
        return ResponseEntity.ok().body(message);
    }
}
