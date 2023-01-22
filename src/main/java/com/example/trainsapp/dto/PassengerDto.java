package com.example.trainsapp.dto;

import jakarta.persistence.Column;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@ToString
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PassengerDto {
    private int passengerId;
    @NotBlank
    private String name;
    @NotNull @Min(0)
    private int age;
}
