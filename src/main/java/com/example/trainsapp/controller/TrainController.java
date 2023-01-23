package com.example.trainsapp.controller;

import com.example.trainsapp.dto.TrainDto;
import com.example.trainsapp.model.Train;
import com.example.trainsapp.service.TrainService;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/train")
public class TrainController {
    private final TrainService trainService;

    public TrainController(TrainService trainService) {
        this.trainService = trainService;
    }


    @GetMapping
    @ApiOperation("Get all existing trains")
    public ResponseEntity<List<TrainDto>> retrieveTrains() {
        return ResponseEntity.ok().body(trainService.retrieveTrains());
    }

    @PostMapping("/add")
    @ApiOperation("Add a train")
    public ResponseEntity<TrainDto> addTrain(@RequestBody TrainDto trainDto) {
        return ResponseEntity.ok().body(trainService.saveTrain(trainDto));
    }

    @PutMapping("/edit/{trainId}")
    @ApiOperation("Edit an existing train by id")
    public ResponseEntity<TrainDto> editTrain(@PathVariable int trainId, @RequestBody TrainDto editedTrainDto) {
        return ResponseEntity.ok().body(trainService.updateTrain(trainId, editedTrainDto));
    }

    @DeleteMapping("/delete/{trainId}")
    @ApiOperation("Delete a train by id")
    public ResponseEntity<String> deleteTrain(@PathVariable int trainId) {
        String message = trainService.deleteTrainById(trainId);
        return ResponseEntity.ok().body(message);
    }

}
