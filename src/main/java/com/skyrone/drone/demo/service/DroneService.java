package com.skyrone.drone.demo.service;

import com.skyrone.drone.demo.model.Drone;
import com.skyrone.drone.demo.model.FlightPath;
import com.skyrone.drone.demo.repository.DroneRepository;
import com.skyrone.drone.demo.repository.FlightPathRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class DroneService {
    @Autowired
    DroneRepository droneRepository;

    @Autowired
    FlightPathService flightPathService;

    public List<Drone> findByName(String name) {
        return droneRepository.findByName(name);
    }


    public List<Drone> findAll() {
        return droneRepository.findAll();
    }


    public void save(Drone drone) {
        droneRepository.save(drone);
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

    public List<Drone> getAllDroneActive(Date timeStart, Date timeEnd){
        List<FlightPath> listFlightPath = flightPathService.getFLightPath(timeStart, timeEnd);
        if (listFlightPath == null) {
            return null;
        }
        List<Drone> drones = new ArrayList<>();
        for (FlightPath flightPath : listFlightPath) {

            Optional<Drone> drone = getById(flightPath.getIdDrone());
            if (drone.isPresent()) {
                if (drone.get().isUsed()) {
                    drones.add(drone.get());
                }

            }
        }
        return drones;
    }
}
