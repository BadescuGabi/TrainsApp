package com.example.trainsapp.controller;

import com.example.trainsapp.dto.TrainDto;
import com.example.trainsapp.model.Train;
import com.example.trainsapp.service.TrainService;
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
    public ResponseEntity<List<TrainDto>> retrieveTrains() {
        return ResponseEntity.ok().body(trainService.retrieveTrains());
    }

    @PostMapping("/add")
    public ResponseEntity<TrainDto> addTrain(@RequestBody TrainDto trainDto) {
        return ResponseEntity.ok().body(trainService.saveTrain(trainDto));
    }

    @PutMapping("/edit/{trainId}")
    public ResponseEntity<TrainDto> editTrain(@PathVariable int trainId, @RequestBody TrainDto editedTrainDto) {
        return ResponseEntity.ok().body(trainService.updateTrain(trainId, editedTrainDto));
    }

    @DeleteMapping("/delete/{trainId}")
    public ResponseEntity<String> deleteTrain(@PathVariable int trainId) {
        String message = trainService.deleteTrainById(trainId);
        return ResponseEntity.ok().body(message);
    }

}
