package com.skyrone.drone.demo.service;

import com.skyrone.drone.demo.dto.DroneStateDto;
import com.skyrone.drone.demo.dto.ParamFlightResponse;
import com.skyrone.drone.demo.dto.ResponseCase;
import com.skyrone.drone.demo.dto.ServerResponseDto;
import com.skyrone.drone.demo.model.Drone;
import com.skyrone.drone.demo.model.DroneMaintenance;
import com.skyrone.drone.demo.model.FlightPath;
import com.skyrone.drone.demo.model.FlightPoint;
import com.skyrone.drone.demo.repository.DroneRepository;
import com.skyrone.drone.demo.repository.FlightPointRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class DroneStateService {
    @Autowired
    DroneMaintenanceService droneMaintenanceService;

    @Autowired
    DroneService droneService;

    @Autowired
    FlightPathService flightPathService;

    @Autowired
    DroneRepository droneRepository;

    @Autowired
    FlightPointRepository flightPointRepository;

    public DroneStateDto getById(String id) {
        Optional<Drone> droneOptional = droneService.getById(id);
        if (!droneOptional.isPresent()) {
            return null;
        }
        Drone drone = droneOptional.get();
        DroneStateDto droneStateDto = new DroneStateDto(drone.getId(), drone.getName(), drone.isUsed());
        if (!drone.isUsed()) {
            droneStateDto.setState(4);
            droneStateDto.setMessage(4);
        } else {
            Optional<DroneMaintenance> droneMaintenance = droneMaintenanceService.findByIdNow(drone.getId());
            if (droneMaintenance.isPresent()) {
                if (droneMaintenance.get().isMaintenance()) {
                    droneStateDto.setState(3);
                    droneStateDto.setMessage(3);
                } else {
                    droneStateDto.setState(2);
                    droneStateDto.setMessage(2);
                }
            } else {
                FlightPath flightPath = flightPathService.getFlightPathRealTime(id);
                if (flightPath != null) {
                    droneStateDto.setState(1);
                    droneStateDto.setMessage(1);
                } else {
                    droneStateDto.setState(0);
                    droneStateDto.setMessage(0);
                }
            }
        }
        return droneStateDto;
    }

    public List<DroneStateDto> getAll() {
        List<Drone> listDrone = droneService.findAll();
        if (listDrone.size() < 1) {
            return null;
        }
        List<DroneStateDto> listDroneState = new ArrayList<>();
        for (Drone drone : listDrone) {
            listDroneState.add(getById(drone.getId()));
        }
        return listDroneState;
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

    public List<Drone> getAllDroneActive(Date timeStart, Date timeEnd){
        List<FlightPath> listFlightPath = flightPathService.getFLightPath(timeStart, timeEnd);
        if (listFlightPath == null) {
            return null;
        }
        List<Drone> drones = new ArrayList<>();
        for (FlightPath flightPath : listFlightPath) {

            Optional<Drone> drone = droneRepository.findById(flightPath.getIdDrone());
            if (drone.isPresent()) {
                if (drone.get().isUsed()) {
                    drones.add(drone.get());
                }

            }
        }
        return drones;
    }

    public ServerResponseDto setDroneBroken(String id) {
        Optional<Drone> drone = droneRepository.findById(id);
        if (drone.isPresent()) {
            drone.get().setUsed(false);
            return new ServerResponseDto(ResponseCase.SUCCESS);
        }
        return new ServerResponseDto(ResponseCase.NOT_FOUND_DRONE);
    }

    public List<Drone> getAllDroneBroken() {
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

    public List<Drone> getAllDroneCharging(Date timeStart, Date timeEnd) {
        List<DroneMaintenance> droneMaintenanceList = droneMaintenanceService.getByDate(timeStart, timeEnd, false);
        List<Drone> droneList = new ArrayList<>();
        if (droneMaintenanceList.size() < 1) {
            return null;
        }
        for (DroneMaintenance droneMaintenance : droneMaintenanceList) {
            Optional<Drone> drone = droneService.getById(droneMaintenance.getId());
            if (drone.isPresent()) {
                if (drone.get().isUsed()) {
                    droneList.add(drone.get());
                }
            }
        }
        return droneList;
    }


    public List<Drone> getAllDroneMaintenance(Date timeStart, Date timeEnd) {
        List<DroneMaintenance> droneMaintenanceList = droneMaintenanceService.getByDate(timeStart, timeEnd, true);
        List<Drone> droneList = new ArrayList<>();
        if (droneMaintenanceList.size() < 1) {
            return null;
        }
        for (DroneMaintenance droneMaintenance : droneMaintenanceList) {
            Optional<Drone> drone = droneService.getById(droneMaintenance.getId());
            if (drone.isPresent()) {
                if (drone.get().isUsed()) {
                    droneList.add(drone.get());
                }
            }
        }
        return droneList;
    }
}
