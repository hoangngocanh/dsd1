package com.skyrone.drone.demo.repository;

import com.skyrone.drone.demo.model.FlightPath;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface FlightPathRepository extends MongoRepository<FlightPath, String> {

    List<FlightPath> findByIdSupervisedArea(String id);

    @Query("{ 'isDelete': ?0}")
    List<FlightPath> getAll(boolean isDelete);
}
