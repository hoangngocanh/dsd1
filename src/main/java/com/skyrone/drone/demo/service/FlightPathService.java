package com.skyrone.drone.demo.service;

import com.skyrone.drone.demo.dto.DroneStateDto;
import com.skyrone.drone.demo.dto.FlightPathDto;
import com.skyrone.drone.demo.dto.ResponseCase;
import com.skyrone.drone.demo.dto.ServerResponseDto;
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

    @Autowired
    DroneStateService droneStateService;



    public ServerResponseDto save(FlightPath flightPath) {
        Optional<Drone> drone = droneRepository.findById(flightPath.getIdDrone());
        if (drone.isPresent()) {
            DroneStateDto droneStateDto = droneStateService.getById(flightPath.getIdDrone());
            if (droneStateDto.getState() == 0) {
                return new ServerResponseDto(ResponseCase.SUCCESS, flightPathRepository.save(flightPath));
            }
            return new ServerResponseDto(ResponseCase.DRONE_BUSY);
        }
        return new ServerResponseDto(ResponseCase.NOT_FOUND_DRONE);
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

    public List<FlightPathDto> getAllFLightPath(Date timeStart, Date timeEnd) {
        List<FlightPath> flightPaths = getFLightPath(timeStart, timeEnd);
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
            flightPaths  = flightPathRepository.getPathByIdDroneDate(timeStart, timeEnd, idDrone);
        } else if (timeStart != null) {
            flightPaths = flightPathRepository.getPathByIdDroneFrom(timeStart, idDrone);
        } else if (timeEnd != null) {
            flightPaths = flightPathRepository.getPathByIdDroneTo(timeEnd, idDrone);
        } else {
            flightPaths = flightPathRepository.findByIdDrone(idDrone);
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


    public FlightPath getFlightPathRealTime(String idDrone) {
        List<FlightPath> flightPathList =  flightPathRepository.getByIdDroneRealTime(new Date(), idDrone);
        if (flightPathList.size() < 1) {
            return null;
        }
        return flightPathList.get(0);
    }

    public void delete(String id) {
        flightPathRepository.deleteById(id);
        flightPointService.deleteByPath(id);
    }

    public List<FlightPathDto> getAllBySupervisedArea(String id) {
        List<FlightPath> flightPaths =  flightPathRepository.findByIdSupervisedArea(id);
        if (flightPaths.size() < 1) {
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
