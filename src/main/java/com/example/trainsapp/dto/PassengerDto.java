package com.example.trainsapp.dto;


import lombok.*;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@ToString
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PassengerDto {
    private int passengerId;
    @NotBlank
    private String name;
    @NotNull
    @Min(0)
    private int age;
}
