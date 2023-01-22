package com.example.trainsapp.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
@Table(name = "schedules")
public class Schedule {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int scheduleId;

    @Column(name = "date")
    private Date date;
    @Column(name = "duration")
    private int duration;
    @Column(name = "hasDelay")
    private int hasDelay;

    @ManyToOne
    @JoinColumn(name = "trainId")
    private Train train;

    @ManyToOne
    @JoinColumn(name = "trackId")
    private Track track;

//    @OneToMany(mappedBy = "ticketId")
//    private ArrayList<Ticket> tickets;
}
