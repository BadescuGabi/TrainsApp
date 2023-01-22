package com.example.trainsapp.controller;

import com.example.trainsapp.dto.TicketDto;
import com.example.trainsapp.dto.TrainDto;
import com.example.trainsapp.service.TicketService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ticket")
public class TicketController {
    private final TicketService ticketService;

    public TicketController(TicketService ticketService) {
        this.ticketService = ticketService;
    }


    @GetMapping
    public ResponseEntity<List<TicketDto>> retrieveTickets() {
        return ResponseEntity.ok().body(ticketService.retrieveTickets());
    }

    @PostMapping("/add")
    public ResponseEntity<TicketDto> addTicket(@RequestBody TicketDto ticketDto) {
        return ResponseEntity.ok().body(ticketService.saveTicketDto(ticketDto));
    }

    @PutMapping("/edit/{ticketId}")
    public ResponseEntity<TicketDto> editTicket(@PathVariable int ticketId, @RequestBody TicketDto editedTicketDto) {
        return ResponseEntity.ok().body(ticketService.updateTicket(ticketId, editedTicketDto));
    }

    @DeleteMapping("/delete/{ticketId}")
    public ResponseEntity<String> deleteTrain(@PathVariable int ticketId) {
        String message = ticketService.deleteTicketById(ticketId);
        return ResponseEntity.ok().body(message);
    }
}
