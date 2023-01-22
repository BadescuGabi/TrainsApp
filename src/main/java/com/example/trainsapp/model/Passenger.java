package com.example.trainsapp.model;


import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "passengers")
public class Passenger {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int passengerId;
    @Column(name = "name")
    private String name;
    @Column(name = "age")
    private int age;

//    @OneToMany(mappedBy = "ticketId")
//    private ArrayList<Ticket> tickets = new ArrayList<>();
}
