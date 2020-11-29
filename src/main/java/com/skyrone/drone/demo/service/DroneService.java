package com.skyrone.drone.demo.service;

import com.skyrone.drone.demo.dto.ParamFlightResponse;
import com.skyrone.drone.demo.dto.ResponseCase;
import com.skyrone.drone.demo.dto.ServerResponseDto;
import com.skyrone.drone.demo.model.Drone;
import com.skyrone.drone.demo.model.FlightPath;
import com.skyrone.drone.demo.model.FlightPoint;
import com.skyrone.drone.demo.repository.DroneRepository;
import com.skyrone.drone.demo.repository.FlightPointRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class DroneService {
    @Autowired
    DroneRepository droneRepository;

    @Autowired
    FlightPathService flightPathService;

    @Autowired
    FlightPointRepository flightPointRepository;

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

    public List<Drone> getAllDroneAvailable(Date timeStart, Date timeEnd){
        List<FlightPath> listFlightPath = flightPathService.getFLightPath(timeStart, timeEnd);
        if (listFlightPath == null) {
            return null;
        }
        List<String> listIdDrone = new ArrayList<>();
        for (FlightPath flightPath : listFlightPath) {
            listIdDrone.add(flightPath.getIdDrone());
        }
        List<Drone> drones = droneRepository.findByIdNotInAndIsUsed(listIdDrone, true);
        return drones;
    }

    public ServerResponseDto setMaintenance(String id) {
        Optional<Drone> drone = droneRepository.findById(id);
        if (drone.isPresent()) {
            drone.get().setUsed(false);
            return new ServerResponseDto(ResponseCase.SUCCESS);
        }
        return new ServerResponseDto(ResponseCase.NOT_FOUND_DRONE);
    }

    public List<Drone> getAllDroneMaintenance() {
        return droneRepository.findByIsUsed(false);
    }

    public ServerResponseDto getParameterFlightRealTime(String idDrone) {
        Optional<Drone> drone = droneRepository.findById(idDrone);
        if (!drone.isPresent()) return new ServerResponseDto(ResponseCase.NOT_FOUND_DRONE);
        if (!drone.get().isUsed()) return new ServerResponseDto(ResponseCase.DRONE_MAINTENANCE);
        FlightPath flightPath = flightPathService.getFlightPathRealTime(idDrone);
        if (flightPath == null) {
            return new ServerResponseDto(ResponseCase.DRONE_NOT_FLIGHT);
        }
        float locationLat = new Random().nextFloat();
        float locationLng = new Random().nextFloat();
        Date realTime = new Date();
        float percentBattery = ((float ) (flightPath.getTimeEnd().getTime() - realTime.getTime()))
                /((float) (flightPath.getTimeEnd().getTime() - flightPath.getTimeStart().getTime())) * 100f;
        List<FlightPoint> listPoint = flightPointRepository.findByIdFlightPath(flightPath.getId());
        List<String> listSupervisedObject = new ArrayList<>();
        if (listPoint != null) {
            for (FlightPoint flightPoint : listPoint) {
                listSupervisedObject.add(flightPoint.getIdSupervisedObject());
            }
        }
        ParamFlightResponse paramFlightResponse = new ParamFlightResponse(locationLat, locationLng, idDrone, flightPath.getId(), flightPath.getIdSupervisedArea(),
                listSupervisedObject, flightPath.getHeightFlight(), new Date(), 30.f, percentBattery);
        return new ServerResponseDto(ResponseCase.SUCCESS, paramFlightResponse);
    }
}
