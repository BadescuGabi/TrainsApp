package com.example.trainsapp.dto;


import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Date;

@ToString
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ScheduleDto {
    private int scheduleId;

    @NotBlank
    private Date date;
    @NotBlank
    private int duration;
    @NotNull
    private int hasDelay;
    @NotEmpty
    private TrainDto trainDto;
    @NotEmpty
    private TrackDto trackDto;
}
