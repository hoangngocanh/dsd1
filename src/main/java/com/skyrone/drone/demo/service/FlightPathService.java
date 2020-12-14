package com.skyrone.drone.demo.service;

import com.skyrone.drone.demo.model.FlightPath;
import com.skyrone.drone.demo.repository.FlightPathRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FlightPathService {
    @Autowired
    FlightPathRepository flightPathRepository;

    public List<FlightPath> getAllBySupervisedArea(String id) {
        return flightPathRepository.findByIdSupervisedArea(id);
    }

    public Optional<FlightPath> getById(String id) {
        return flightPathRepository.findById(id);
    }

    public FlightPath save(FlightPath flightPath) {
        return flightPathRepository.save(flightPath);
    }

    public void delete(String id) {
        flightPathRepository.deleteById(id);
    }


    public List<FlightPath> getAll() {
        return flightPathRepository.findAll();
    }
}