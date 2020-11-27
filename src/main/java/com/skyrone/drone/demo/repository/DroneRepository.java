package com.skyrone.drone.demo.repository;

import com.skyrone.drone.demo.model.Drone;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DroneRepository extends MongoRepository<Drone, String> {

    @Query("{ 'name': ?0}")
    List<Drone> findByName(String name);

    @Query("{ 'id': ?0}")
    Optional<Drone> findById(String id);

    @Query("{ 'code': ?0}")
    Drone findByCode(String code);

    void deleteById(String id);

}
