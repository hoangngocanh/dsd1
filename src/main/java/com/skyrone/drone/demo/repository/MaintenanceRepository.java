package com.skyrone.drone.demo.repository;

import com.skyrone.drone.demo.model.DroneMaintenance;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface MaintenanceRepository  extends MongoRepository<DroneMaintenance, String> {

    Optional<DroneMaintenance> findById(String id);

    @Query("{$and : [{'timeEnd' : {$gte : ?0}}, {'timeStart' : {$lte : ?0}}, {'id' : ?1}]}")
    Optional<DroneMaintenance> findByIdNow(Date now, String id);
}
