package com.skyrone.drone.demo.service;


import com.skyrone.drone.demo.dto.ResponseCase;
import com.skyrone.drone.demo.dto.ServerResponseDto;
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

   @Autowired
   DroneService droneService;
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

    List<DroneMaintenance> getAllNow() {
        Date now = new Date();
        return maintenanceRepository.findAllNow(now);
    }


    public List<DroneMaintenance> getByDate(Date timeStart, Date timeEnd, boolean isMaintenance) {
        if (timeStart != null && timeEnd != null) {
            return maintenanceRepository.getByDate(timeStart, timeEnd, isMaintenance);
        } else if (timeStart != null) {
            return maintenanceRepository.getByFrom(timeStart, isMaintenance);
        } else if (timeEnd != null) {
            return maintenanceRepository.getByTo(timeEnd, isMaintenance);
        } else {
            return maintenanceRepository.findByIsMaintenance(isMaintenance);
        }
    }

    public void delete(String id) {
        maintenanceRepository.deleteById(id);
    }

    public ServerResponseDto save(DroneMaintenance droneMaintenance) {
        Optional<Drone> drone = droneService.getById(droneMaintenance.getId());
        if (drone.isPresent()) {
            drone.get().setUsed(true);
            droneService.save(drone.get());
            droneMaintenance.setName(drone.get().getName());
            maintenanceRepository.save(droneMaintenance);
            return new ServerResponseDto(ResponseCase.SUCCESS);
        }
        return new ServerResponseDto(ResponseCase.NOT_FOUND_DRONE);
    }
}
