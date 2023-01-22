package com.example.trainsapp.repository;

import com.example.trainsapp.model.Train;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TrainRepository extends JpaRepository<Train, Integer> {
}
