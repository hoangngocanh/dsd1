package com.skyrone.drone.demo.service;

import com.skyrone.drone.demo.dto.FlightPathDto;
import com.skyrone.drone.demo.model.Drone;
import com.skyrone.drone.demo.model.FlightPath;
import com.skyrone.drone.demo.model.FlightPoint;
import com.skyrone.drone.demo.repository.DroneRepository;
import com.skyrone.drone.demo.repository.FlightPathRepository;
import com.skyrone.drone.demo.repository.FlightPointRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class FlightPathService {
    @Autowired
    FlightPathRepository flightPathRepository;

    @Autowired
    FlightPointRepository flightPointRepository;

    @Autowired
    DroneRepository droneRepository;

    @Autowired
    FlightPointService flightPointService;

    public void save(FlightPath flightPath) {
        flightPathRepository.save(flightPath);
    }

    public List<FlightPath> getFLightPath(Date timeStart, Date timeEnd) {
        if (timeStart != null && timeEnd != null) {
            return flightPathRepository.getAllPathActive(timeStart, timeEnd);
        } else if (timeStart != null) {
            return flightPathRepository.getAllPathActiveFrom(timeStart);
        } else if (timeEnd != null) {
            return flightPathRepository.getAllPathActiveTo(timeEnd);
        } else {
            return flightPathRepository.findAll();
        }
    }



    public List<FlightPathDto> getAllPathOfDrone(String idDrone, Date timeStart, Date timeEnd) {
        Optional<Drone> drone = droneRepository.findById(idDrone);
        if (!drone.isPresent()) {
            return null;
        }
        if (!drone.get().isUsed()) {
            return null;
        }
        List<FlightPath> flightPaths;
        if (timeStart != null && timeEnd != null) {
            flightPaths  = flightPathRepository.getPathByIdDrone(timeStart, timeEnd, idDrone);
        } else if (timeStart != null) {
            flightPaths = flightPathRepository.getPathByIdDroneFrom(timeStart, idDrone);
        } else if (timeEnd != null) {
            flightPaths = flightPathRepository.getPathByIdDroneTo(timeEnd, idDrone);
        } else {
            flightPaths = flightPathRepository.findAll();
        }

        if (flightPaths == null) {
            return null;
        }
        List<FlightPathDto> flightPathDtos = new ArrayList<>();
        for (FlightPath flightPath : flightPaths) {
            List<FlightPoint> flightPoints = flightPointService.getByIdPath(flightPath.getId());
            FlightPathDto flightPathDto = new FlightPathDto(flightPath, flightPoints);
            flightPathDtos.add(flightPathDto);
        }
        return flightPathDtos;
    }

    public void saveFlightPath(FlightPath flightPath) {
        flightPathRepository.save(flightPath);
    }
}
