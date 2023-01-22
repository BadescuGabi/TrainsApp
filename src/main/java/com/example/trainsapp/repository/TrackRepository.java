package com.example.trainsapp.repository;

import com.example.trainsapp.model.Track;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TrackRepository extends JpaRepository<Track, Integer> {
}
