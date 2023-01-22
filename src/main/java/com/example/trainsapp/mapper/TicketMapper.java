package com.example.trainsapp.mapper;



import com.example.trainsapp.dto.TicketDto;
import com.example.trainsapp.model.Ticket;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import static java.util.Objects.isNull;

@Component
public class TicketMapper {

    private final ScheduleMapper scheduleMapper;
    private final PassengerMapper passengerMapper;

    @Autowired
    public TicketMapper(ScheduleMapper scheduleMapper, PassengerMapper passengerMapper) {
        this.scheduleMapper = scheduleMapper;
        this.passengerMapper = passengerMapper;
    }


    public Ticket convertFromDto(TicketDto ticketDto) {
        if (isNull(ticketDto)) {
            return null;
        }

        return new Ticket(
                ticketDto.getTicketId(),
                ticketDto.getStandardPrice(),
                scheduleMapper.convertFromDto(ticketDto.getScheduleDto()),
                passengerMapper.convertFromDto(ticketDto.getPassengerDto())
        );
    }


    public TicketDto convertToDto(Ticket ticket) {
        if (isNull(ticket)) {
            return null;
        }
        return new TicketDto(
                ticket.getTicketId(),
                ticket.getStandardPrice(),
                scheduleMapper.convertToDto(ticket.getSchedule()),
                passengerMapper.convertToDto(ticket.getPassenger())
                );
    }
}
