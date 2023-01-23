package com.example.trainsapp.model;

import javax.persistence.*;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "stations")
public class Station {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int stationId;
    @Column(name = "city")
    private String city;
    @Column(name = "contact")
    private String contact;

//    @OneToMany(mappedBy = "trackId")
//    private ArrayList<Track> tracks = new ArrayList<>();
}
