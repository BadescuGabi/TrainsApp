package com.example.trainsapp.dto;

import lombok.*;

import javax.validation.constraints.NotBlank;

@ToString
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TrainDto {

    private int trainId;
    @NotBlank
    private String trainModel;
    @NotBlank
    private int seatsNo;
    @NotBlank
    private int fabricationYear;

}
