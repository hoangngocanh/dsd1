package com.skyrone.drone.demo.service;

import com.skyrone.drone.demo.model.FlightPoint;
import com.skyrone.drone.demo.repository.FlightPointRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FlightPointService {
    @Autowired
    FlightPointRepository flightPointRepository;

    public List<FlightPoint> getByIdPath(String idPath) {
        return flightPointRepository.findByIdFlightPath(idPath);
    }

    public void save(FlightPoint flightPoint) {
        if (flightPoint != null) {
            flightPointRepository.save(flightPoint);
        }
    }

    public void delete(String id) {
        flightPointRepository.deleteById(id);
    }

    public void deleteByPath(String id) {
        flightPointRepository.deleteByIdFlightPath(id);
    }
}
