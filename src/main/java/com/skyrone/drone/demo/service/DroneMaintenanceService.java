package com.skyrone.drone.demo.service;


import com.skyrone.drone.demo.model.Drone;
import com.skyrone.drone.demo.model.DroneMaintenance;
import com.skyrone.drone.demo.repository.MaintenanceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class DroneMaintenanceService {
   @Autowired
    MaintenanceRepository maintenanceRepository;

   List<DroneMaintenance> getAll() {
       return maintenanceRepository.findAll();
   }

    Optional<DroneMaintenance> findById(String id) {
        return maintenanceRepository.findById(id);
    }

    Optional<DroneMaintenance> findByIdNow(String id) {
       Date now = new Date();
        return maintenanceRepository.findByIdNow(now, id);
    }

    DroneMaintenance save(DroneMaintenance droneMaintenance) {
       return maintenanceRepository.save(droneMaintenance);
    }


}
