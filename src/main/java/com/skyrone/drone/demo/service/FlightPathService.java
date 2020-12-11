package com.skyrone.drone.demo.service;

import com.skyrone.drone.demo.dto.FlightPathDto;
import com.skyrone.drone.demo.model.FlightPath;
import com.skyrone.drone.demo.model.FlightPoint;
import com.skyrone.drone.demo.repository.FlightPathRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class FlightPathService {
    @Autowired
    FlightPathRepository flightPathRepository;

    @Autowired
    FlightPointService flightPointService;

    public List<FlightPathDto> getAllBySupervisedArea(String id) {
        List<FlightPath> flightPaths = flightPathRepository.findByIdSupervisedArea(id);
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

    public FlightPath save(FlightPath flightPath) {
        return flightPathRepository.save(flightPath);
    }

    public void delete(String id) {
        flightPathRepository.deleteById(id);
        flightPointService.deleteByPath(id);
    }


    public List<FlightPathDto> getAll() {
        List<FlightPath> flightPaths = flightPathRepository.findAll();
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
}