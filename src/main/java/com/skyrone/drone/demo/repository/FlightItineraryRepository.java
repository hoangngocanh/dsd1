package com.skyrone.drone.demo.repository;

import com.skyrone.drone.demo.model.FlightItinerary;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.Date;
import java.util.List;

public interface FlightItineraryRepository extends MongoRepository<FlightItinerary, String> {
    @Query("{$and : [{ 'timeStart': {$gte : ?0}}, {'timeEnd' : {$lte : ?1}}]}")
    List<FlightItinerary> getAllPathActive(Date timeStart, Date timeEnd);

    @Query("{ 'timeStart': {$gte : ?0}}")
    List<FlightItinerary> getAllPathActiveFrom(Date timeStart);

    @Query("{'timeEnd' : {$lte : ?1}}")
    List<FlightItinerary> getAllPathActiveTo(Date timeEnd);

    @Query("{$and : [{ 'timeStart': {$gte : ?0}}, {'timeEnd' : {$lte : ?1}}, {'idDroneList' : {'$all' : [?2]}}]}")
    List<FlightItinerary> getPathByIdDroneDate(Date timeStart, Date timeEnd, String idDrone);

    @Query("{$and : [{ 'timeStart': {$gte : ?0}}, {'idDrone' : ?1}]}")
    List<FlightItinerary> getPathByIdDroneFrom(Date timeStart, String idDrone);

    @Query("{$and : [{'timeEnd' : {$lte : ?0}}, {'idDroneList' : {'$all' : [?1]}}]}")
    List<FlightItinerary> getPathByIdDroneTo(Date timeEnd, String idDrone);

    @Query("{$and : [{'timeEnd' : {$gte : ?0}}, {'timeStart' : {$lte : ?0}}, {'idDroneList' : {'$all' : [?1]}} ]}")
    List<FlightItinerary> getByIdDroneRealTime(Date realTime, String idDrone);

    @Query("{'idDroneList' : {'$all' : [?0]}}")
    List<FlightItinerary> findByIdDrone(String id);

    List<FlightItinerary> findByIdSupervisedArea(String id);
}
