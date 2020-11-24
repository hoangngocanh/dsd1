package com.skyrone.drone.demo.service;

import com.skyrone.drone.demo.dto.ActiveDroneDto;
import com.skyrone.drone.demo.dto.InfoDroneDto;
import com.skyrone.drone.demo.model.Drone;
import com.skyrone.drone.demo.repository.DroneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DroneService {
    @Autowired
    DroneRepository droneRepository;

    @Autowired
    ConvertDtoService convertDtoService;

    public List<Drone> findByName(String name) {
        return droneRepository.findByName(name);
    }

    public Drone findByCode(String code) {
        return droneRepository.findByCode(code);
    }


    public List<Drone> findAll() {
        return droneRepository.findAll();
    }


    public void save(Drone drone) {

        droneRepository.save(drone);
    }

    public List<InfoDroneDto> getInfo() {
        return convertDtoService.convertToListInfoDto(droneRepository.findAll());
    }

    public List<ActiveDroneDto> getActive() {
        return convertDtoService.convertToListActiveDto(droneRepository.findAll());
    }
}
