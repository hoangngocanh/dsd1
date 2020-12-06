package com.skyrone.drone.demo.repository;

import com.skyrone.drone.demo.model.FlightPoint;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface FlightPointRepository extends MongoRepository<FlightPoint, String> {

    List<FlightPoint> findByIdFlightPath(String idPath);

}
