package com.skyrone.drone.demo.repository;

import com.skyrone.drone.demo.model.FlightPath;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface FlightPathRepository extends MongoRepository<FlightPath, String> {

    List<FlightPath> findByIdSupervisedArea(String id);
}
