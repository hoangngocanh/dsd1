package com.skyrone.drone.demo.service;

import com.google.common.collect.Lists;
import com.skyrone.drone.demo.model.Drone;
import com.skyrone.drone.demo.model.DroneMaintenance;
import com.skyrone.drone.demo.repository.DroneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class DroneService {
    @Autowired
    DroneRepository droneRepository;

    @Autowired
    DroneMaintenanceService droneMaintenanceService;



    public List<Drone> findByName(String name) {
        return droneRepository.findByName(name);
    }


    public List<Drone> findAll() {
//        List<Drone> list = droneRepository.findAll();
//        int dem = 0;
//        Random generator = new Random();
//        for (Drone drone : list) {
//            dem ++;
//            drone.setType(generator.nextInt(6) +1);
//            if (drone.getName() == null) {
//                drone.setName("Xman2021"+generator.nextInt(100));
//            } else if (drone.getName().length() < 1) {
//                drone.setName("Xman2021"+generator.nextInt(100));
//            }
//            save(drone);
//        }
        return Lists.reverse(droneRepository.findAll());
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
        droneMaintenanceService.delete(id);
    }

}
