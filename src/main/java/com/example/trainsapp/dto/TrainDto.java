package com.example.trainsapp.dto;

import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Builder
@ToString
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TrainDto {

    private int trainId;
    @NotBlank
    private String trainModel;
    @NotNull
    private int seatsNo;
    @NotNull
    private int fabricationYear;

}
