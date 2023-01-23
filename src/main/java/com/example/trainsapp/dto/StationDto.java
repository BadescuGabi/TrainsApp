package com.example.trainsapp.dto;


import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@ToString
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class StationDto {
    private int stationId;
    @NotBlank
    private String city;
    @NotBlank
    private String contact;
}
