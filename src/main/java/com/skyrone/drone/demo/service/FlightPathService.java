package com.skyrone.drone.demo.service;

import com.skyrone.drone.demo.model.FlightPath;
import com.skyrone.drone.demo.model.FlightPoint;
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

    public void saveList(List<FlightPath> flightPathList) {
        if (flightPathList != null) {
            for (FlightPath flightPath : flightPathList) {
                flightPathRepository.save(flightPath);
            }
        }
    }

    public void delete(String id) {
        Optional<FlightPath> flightPath = flightPathRepository.findById(id);
        if (flightPath.isPresent()) {
            flightPath.get().setDelete(true);
        }
    }


    public List<FlightPath> getAll() {
//        List<FlightPath> list = flightPathRepository.findAll();
//        for (FlightPath flightPath : list) {
//            for (FlightPoint flightPoint : flightPath.getFlightPoints()) {
//                flightPoint.setFlightHeightDown(10.f);
//            }
//            save(flightPath);
//        }
//        return list;
        return flightPathRepository.getAll(false);
    }
}