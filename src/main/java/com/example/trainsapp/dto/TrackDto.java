package com.example.trainsapp.dto;


import lombok.*;

import javax.validation.constraints.NotBlank;

@ToString
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TrackDto {
    private int trackId;
    @NotBlank
    private double distance;

    private StationDto station1Id;

    private StationDto station2Id;
}
