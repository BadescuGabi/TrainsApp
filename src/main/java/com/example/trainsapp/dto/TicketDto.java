package com.example.trainsapp.dto;

import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

@ToString
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TicketDto {
    private int ticketId;
    @NotBlank
    private double standardPrice;
    @NotEmpty
    private ScheduleDto scheduleDto;
    @NotEmpty
    private PassengerDto passengerDto;
}
