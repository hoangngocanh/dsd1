package com.skyrone.drone.demo.service;

import com.skyrone.drone.demo.dto.ActiveDroneDto;
import com.skyrone.drone.demo.dto.InfoDroneDto;
import com.skyrone.drone.demo.model.Drone;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ConvertDtoService {
    public List<InfoDroneDto> convertToListInfoDto(List<Drone> drones) {
        List<InfoDroneDto> listInfo = new ArrayList<>();
        for (Drone drone : drones) {
            listInfo.add(new InfoDroneDto(drone));
        }
        return listInfo;
    }

    public List<ActiveDroneDto> convertToListActiveDto(List<Drone> drones) {
        List<ActiveDroneDto> listActive = new ArrayList<>();
        for (Drone drone : drones) {
            listActive.add(new ActiveDroneDto(drone));
        }
        return listActive;
    }
}
