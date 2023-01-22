package com.example.trainsapp.repository;

import com.example.trainsapp.model.Station;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StationRepository extends JpaRepository<Station, Integer> {
}
