package com.example.trainsapp.service;

import com.example.trainsapp.constants.Constants;
import com.example.trainsapp.dto.TicketDto;
import com.example.trainsapp.mapper.TicketMapper;
import com.example.trainsapp.model.*;
import com.example.trainsapp.repository.PassengerRepository;
import com.example.trainsapp.repository.ScheduleRepository;
import com.example.trainsapp.repository.TicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static java.util.Objects.isNull;

@Service
public class TicketService {
    private final TicketRepository ticketRepository;
    private final PassengerRepository passengerRepository;
    private final ScheduleRepository scheduleRepository;
    private final TicketMapper ticketMapper;

    @Autowired
    public TicketService(TicketRepository ticketRepository, PassengerRepository passengerRepository, ScheduleRepository scheduleRepository, TicketMapper ticketMapper) {
        this.ticketRepository = ticketRepository;
        this.passengerRepository = passengerRepository;
        this.scheduleRepository = scheduleRepository;
        this.ticketMapper = ticketMapper;
    }

    public List<TicketDto> retrieveTickets() {
        return ticketRepository.findAll()
                .stream().map(ticketMapper::convertToDto)
                .collect(Collectors.toList());
    }

    public TicketDto findTicketById(int ticketId) {
        Ticket ticket = ticketRepository.findById(ticketId) .orElseThrow(() -> new RuntimeException(Constants.ID_NOT_FOUND));
        return ticketMapper.convertToDto(ticket);
    }

    public TicketDto updateTicket(int ticketId, TicketDto updatedTicketDto) {
        Ticket ticket = ticketRepository.findById(ticketId).orElseThrow(() -> new RuntimeException(Constants.ID_NOT_FOUND));
//            Track newTrackDto = getTrackDtoWithStation (updatedTrackDto, track);
        if (!isNull(updatedTicketDto.getScheduleDto())) {
            Schedule schedule = scheduleRepository.findById(updatedTicketDto.getScheduleDto().getScheduleId())
                    .orElseThrow(() -> new RuntimeException(Constants.ID_NOT_FOUND));;
            ticket.setSchedule(schedule);
        }
        if (!isNull(updatedTicketDto.getPassengerDto())) {
            Passenger passenger = passengerRepository.findById(updatedTicketDto.getPassengerDto().getPassengerId())
                    .orElseThrow(() -> new RuntimeException(Constants.ID_NOT_FOUND));;
            ticket.setPassenger(passenger);
        }
        ticket.setStandardPrice(updatedTicketDto.getStandardPrice());
        return ticketMapper.convertToDto(ticketRepository.save(ticket));
    }

    public TicketDto saveTicketDto(TicketDto ticketDto) {
        Ticket ticket = ticketMapper.convertFromDto(ticketDto);
//            Track newTrackDto = getTrackDtoWithStation (updatedTrackDto, track);
        if (!isNull(ticketDto.getScheduleDto())) {
            Schedule schedule = scheduleRepository.findById(ticketDto.getScheduleDto().getScheduleId())
                    .orElseThrow(() -> new RuntimeException(Constants.ID_NOT_FOUND));;
            ticket.setSchedule(schedule);
        }
        if (!isNull(ticketDto.getPassengerDto())) {
            Passenger passenger = passengerRepository.findById(ticketDto.getPassengerDto().getPassengerId()).orElseThrow();
            ticket.setPassenger(passenger);
        }
        ticket.setStandardPrice(ticketDto.getStandardPrice());
        return ticketMapper.convertToDto(ticketRepository.save(ticket));
    }

    public String deleteTicketById(Integer ticketId) {
        Ticket ticket = ticketRepository.findById(ticketId).orElseThrow(() -> new RuntimeException(Constants.ID_NOT_FOUND));
        ticketRepository.deleteById(ticket.getTicketId());
        return ""/*LocationConstants.DELETE_OK_MESSAGE*/;
    }
}

