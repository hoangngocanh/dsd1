package com.skyrone.drone.demo.service;

import com.skyrone.drone.demo.dto.DroneStateDto;
import com.skyrone.drone.demo.model.Drone;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DroneStateService {
    @Autowired
    DroneMaintenanceService droneMaintenanceService;

    @Autowired
    DroneService droneService;

    public List<DroneStateDto> getAll() {
        List<Drone> listDrone = droneService.findAll();
        if (listDrone.size() < 1) {
            return null;
        }
        for (Drone drone : listDrone) {
            if (!drone.isUsed()) {

            }
        }
        return null;
    }
}
