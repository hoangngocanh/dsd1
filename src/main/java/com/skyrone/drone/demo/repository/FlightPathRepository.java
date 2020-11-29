package com.skyrone.drone.demo.repository;

import com.skyrone.drone.demo.model.FlightPath;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.Date;
import java.util.List;

public interface FlightPathRepository extends MongoRepository<FlightPath, String> {
    @Query("{$and : [{ 'timeStart': {$gte : ?0}}, {'timeEnd' : {$lte : ?1}}]}")
    List<FlightPath> getAllPathActive(Date timeStart, Date timeEnd);

    @Query("{ 'timeStart': {$gte : ?0}}")
    List<FlightPath> getAllPathActiveFrom(Date timeStart);

    @Query("{'timeEnd' : {$lte : ?1}}")
    List<FlightPath> getAllPathActiveTo(Date timeEnd);

    @Query("{$and : [{ 'timeStart': {$gte : ?0}}, {'timeEnd' : {$lte : ?1}}, {'idDrone' : ?2}]}")
    List<FlightPath> getPathByIdDrone(Date timeStart, Date timeEnd, String idDrone);

    @Query("{$and : [{ 'timeStart': {$gte : ?0}}, {'idDrone' : ?1}]}")
    List<FlightPath> getPathByIdDroneFrom(Date timeStart, String idDrone);

    @Query("{$and : [{'timeEnd' : {$lte : ?0}}, {'idDrone' : ?1}]}")
    List<FlightPath> getPathByIdDroneTo(Date timeEnd, String idDrone);

    @Query("{$and : [{'timeEnd' : {$gte : ?0}}, {'timeStart' : {$lte : ?0}}, {'idDrone' : ?1}]}")
    List<FlightPath> getByIdDroneRealTime(Date realTime, String idDrone);
}
