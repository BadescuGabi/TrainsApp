package com.example.trainsapp.controller;

import com.example.trainsapp.dto.StationDto;
import com.example.trainsapp.dto.TrainDto;
import com.example.trainsapp.service.StationService;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/station")
public class StationController {
    private final StationService stationService;

    public StationController(StationService stationService) {
        this.stationService = stationService;
    }


    @GetMapping
    @ApiOperation("Get all existing stations")
    public ResponseEntity<List<StationDto>> retrieveStations() {
        return ResponseEntity.ok().body(stationService.retrieveStations());
    }

    @PostMapping("/add")
    @ApiOperation("Add a station")
    public ResponseEntity<StationDto> addStation(@RequestBody StationDto stationDto) {
        return ResponseEntity.ok().body(stationService.saveStation(stationDto));
    }

    @PutMapping("/edit/{stationId}")
    @ApiOperation("Edit an existing station by id")
    public ResponseEntity<StationDto> editStation(@PathVariable int stationId, @RequestBody StationDto editedStationDto) {
        return ResponseEntity.ok().body(stationService.updateStation(stationId, editedStationDto));
    }

    @DeleteMapping("/delete/{stationId}")
    @ApiOperation("Delete a station by id")
    public ResponseEntity<String> deleteStation(@PathVariable int stationId) {
        String message = stationService.deleteStationById(stationId);
        return ResponseEntity.ok().body(message);
    }
}
