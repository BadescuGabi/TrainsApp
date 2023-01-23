package com.example.trainsapp.controller;

import com.example.trainsapp.dto.TrainDto;
import com.example.trainsapp.repository.TrainRepository;
import com.example.trainsapp.service.TrainService;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/train")
public class TrainController {
    private final TrainService trainService;
    private final TrainRepository trainRepository;
    private static final Logger log = LoggerFactory.getLogger(TrainController.class);

    @Autowired
    public TrainController(TrainService trainService, TrainRepository trainRepository) {
        this.trainService = trainService;
        this.trainRepository = trainRepository;
    }


    @GetMapping
    @ApiOperation("Get all existing trains")
    public ResponseEntity<List<TrainDto>> retrieveTrains() {
        return ResponseEntity.ok().body(trainService.retrieveTrains());
    }

    @PostMapping("/add")
    @ApiOperation("Add a train")
    public ResponseEntity<TrainDto> addTrain(@Valid @RequestBody TrainDto trainDto) {
        TrainDto savedTrainDto = trainService.saveTrain(trainDto);
        log.info("Train saved: {}", savedTrainDto);
        return ResponseEntity.ok(savedTrainDto);
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
