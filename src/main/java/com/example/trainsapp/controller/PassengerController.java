package com.example.trainsapp.controller;

import com.example.trainsapp.dto.PassengerDto;
import com.example.trainsapp.dto.TrainDto;
import com.example.trainsapp.service.PassengerService;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/passenger")
public class PassengerController {
    private final PassengerService passengerService;

    public PassengerController(PassengerService passengerService) {
        this.passengerService = passengerService;
    }


    @GetMapping
    @ApiOperation("Get all existing passenger")
    public ResponseEntity<List<PassengerDto>> retrievePassengers() {
        return ResponseEntity.ok().body(passengerService.retrievePassengers());
    }

    @PostMapping("/add")
    @ApiOperation("Add a passenger")
    public ResponseEntity<PassengerDto> addPassenger(@RequestBody PassengerDto passengerDto) {
        return ResponseEntity.ok().body(passengerService.savePassenger(passengerDto));
    }

    @PutMapping("/edit/{passengerId}")
    @ApiOperation("Edit a passenger by id")
    public ResponseEntity<PassengerDto> editPassenger(@PathVariable int passengerId, @RequestBody PassengerDto editedPassengerDto) {
        return ResponseEntity.ok().body(passengerService.updatePassenger(passengerId, editedPassengerDto));
    }

    @DeleteMapping("/delete/{passengerId}")
    @ApiOperation("Delete a passenger by id")
    public ResponseEntity<String> deletePassenger(@PathVariable int passengerId) {
        String message = passengerService.deletePassengerById(passengerId);
        return ResponseEntity.ok().body(message);
    }
}
