package com.example.trainsapp.model;



import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.Optional;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "tracks")
public class Track {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int trackId;
//    private int station1Id;
//    private int station2Id;
    private double distance;

//    @OneToMany(mappedBy = "scheduleId")
//    private ArrayList<Schedule> schedules = new ArrayList<>();

    @ManyToOne()
    @JoinColumn(name = "station1Id")
    private Station station1Id;
    @ManyToOne
    @JoinColumn(name = "station2Id")
    private Station station2Id;


}
