package com.skyrone.drone.demo.service;

import com.skyrone.drone.demo.model.Drone;
import com.skyrone.drone.demo.repository.DroneRepository;
import com.skyrone.drone.demo.repository.FlightPointRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class DroneService {
    @Autowired
    DroneRepository droneRepository;

    @Autowired
    FlightItineraryService flightItineraryService;

    @Autowired
    FlightPointRepository flightPointRepository;

    public List<Drone> findByName(String name) {
        return droneRepository.findByName(name);
    }


    public List<Drone> findAll() {
        return droneRepository.findAll();
    }


    public Drone save(Drone drone) {
        return droneRepository.save(drone);
    }


    public Drone getByCode(String code) {
        return droneRepository.findByCode(code);
    }

    public Optional<Drone> getById(String id) {
        return droneRepository.findById(id);
    }

    public void delete(String id) {
        droneRepository.deleteById(id);
    }

}
