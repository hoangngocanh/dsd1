package com.skyrone.drone.demo.repository;

import com.skyrone.drone.demo.model.DroneMaintenance;
import com.skyrone.drone.demo.model.FlightPath;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface MaintenanceRepository  extends MongoRepository<DroneMaintenance, String> {

    Optional<DroneMaintenance> findById(String id);

    @Query("{$and : [{'timeEnd' : {$gte : ?0}}, {'timeStart' : {$lte : ?0}}, {'id' : ?1}]}")
    Optional<DroneMaintenance> findByIdNow(Date now, String id);

    @Query("{$and : [{ 'timeStart': {$gte : ?0}}, {'timeEnd' : {$lte : ?1}}, {'idDrone' : ?2}]}")
    List<DroneMaintenance> getByIdDroneDate(Date timeStart, Date timeEnd, String idDrone);

    @Query("{$and : [{ 'timeStart': {$gte : ?0}}, {'idDrone' : ?1}]}")
    List<DroneMaintenance> getByIdDroneFrom(Date timeStart, String idDrone);

    @Query("{$and : [{'timeEnd' : {$lte : ?0}}, {'idDrone' : ?1}]}")
    List<DroneMaintenance> getByIdDroneTo(Date timeEnd, String idDrone);

    @Query("{$and : [{ 'timeStart': {$gte : ?0}}, {'timeEnd' : {$lte : ?1}}, {isMaintenance : ?2}]}")
    List<DroneMaintenance> getByDate(Date timeStart, Date timeEnd, boolean isMaintenance);

    @Query("{$and: [{'timeStart': {$gte : ?0}}, , {isMaintenance : ?1}]}")
    List<DroneMaintenance> getByFrom(Date timeStart, boolean isMaintenance);

    @Query("{$and : [{'timeEnd' : {$lte : ?0}}, {isMaintenance : ?1}]}")
    List<DroneMaintenance> getByTo(Date timeEnd, boolean isMaintenance);

    @Query("{ 'isMaintenance': ?0}")
    List<DroneMaintenance> findByIsMaintenance(boolean isMaintenance);
}
