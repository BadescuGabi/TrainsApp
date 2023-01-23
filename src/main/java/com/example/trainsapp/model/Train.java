package com.example.trainsapp.model;

import lombok.*;

import javax.persistence.*;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
@Table(name = "trains")
public class Train {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int trainId;
    @Column(name = "train_model")
    private String trainModel;
    @Column(name = "seats_no")
    private int seatsNo;
    @Column(name = "fabrication_year")
    private int fabricationYear;

//    @OneToMany(mappedBy = "scheduleId")
//    private List<Schedule> schedules = new ArrayList<>();

}
