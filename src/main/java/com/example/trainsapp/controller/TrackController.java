package com.example.trainsapp.controller;

import com.example.trainsapp.dto.TrackDto;
import com.example.trainsapp.dto.TrainDto;
import com.example.trainsapp.service.TrackService;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/track")
public class TrackController {
    private final TrackService trackService;

    public TrackController(TrackService trackService) {
        this.trackService = trackService;
    }


    @GetMapping
    @ApiOperation("Get all existing tracks")
    public ResponseEntity<List<TrackDto>> retrieveTracks() {
        return ResponseEntity.ok().body(trackService.retrieveTracks());
    }

    @PostMapping("/add")
    @ApiOperation("Add a track")
    public ResponseEntity<TrackDto> addTrack(@RequestBody TrackDto trackDto) {
        return ResponseEntity.ok().body(trackService.saveTrack(trackDto));
    }

    @PutMapping("/edit/{trackId}")
    @ApiOperation("Edit an existing track by id")
    public ResponseEntity<TrackDto> editTrack(@PathVariable int trackId, @RequestBody TrackDto editedTrackDto) {
        return ResponseEntity.ok().body(trackService.updateTrack(trackId, editedTrackDto));
    }

    @DeleteMapping("/delete/{trackId}")
    @ApiOperation("Delete a track by id")
    public ResponseEntity<String> deleteTrack(@PathVariable int trackId) {
        String message = trackService.deleteTrackById(trackId);
        return ResponseEntity.ok().body(message);
    }
}
