package com.skyrone.drone.demo.service;

import com.skyrone.drone.demo.dto.DroneStateDto;
import com.skyrone.drone.demo.dto.ParamFlightResponse;
import com.skyrone.drone.demo.dto.ResponseCase;
import com.skyrone.drone.demo.dto.ServerResponseDto;
import com.skyrone.drone.demo.model.*;
import com.skyrone.drone.demo.repository.DroneRepository;
import com.skyrone.drone.demo.repository.FlightItineraryRepository;
import com.skyrone.drone.demo.repository.MaintenanceRepository;
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
    FlightItineraryService flightItineraryService;

    @Autowired
    DroneRepository droneRepository;


    @Autowired
    FlightItineraryRepository flightItineraryRepository;

    @Autowired
    MaintenanceRepository maintenanceRepository;

    @Autowired
    FlightPathService flightPathService;



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
                droneStateDto.setTimeStart(droneMaintenance.get().getTimeStart());
                droneStateDto.setTimeEnd(droneMaintenance.get().getTimeEnd());
                if (droneMaintenance.get().isMaintenance()) {
                    droneStateDto.setState(3);
                    droneStateDto.setMessage(3);
                } else {
                    droneStateDto.setState(2);
                    droneStateDto.setMessage(2);
                }
            } else {
                FlightItinerary flightItinerary = flightItineraryService.getFlightPathRealTime(id);
                if (flightItinerary != null) {
                    droneStateDto.setState(1);
                    droneStateDto.setMessage(1);
                    droneStateDto.setTimeStart(flightItinerary.getTimeStart());
                    droneStateDto.setContentProject(flightItinerary.getTask());
                    droneStateDto.setProject(flightItinerary.getTask());
                    droneStateDto.setTimeEnd(flightItinerary.getTimeEnd());
                    droneStateDto.setNameTask(flightItinerary.getName());
                } else {
                    droneStateDto.setState(0);
                    droneStateDto.setMessage(0);
                }
            }
        }
        return droneStateDto;
    }


    public List<DroneStateDto> getAllStateNow() {
        List<Drone> listDrone = droneService.findAll();
        HashMap<String, DroneStateDto> hashMapState = new HashMap<String, DroneStateDto>();

        if (listDrone.size() < 1) {
            return null;
        }

        for (Drone drone : listDrone) {
            DroneStateDto droneStateDto = new DroneStateDto(drone.getId(), drone.getName(), drone.isUsed());
            if (drone.isUsed()) {
                droneStateDto.setMessage(0);
                droneStateDto.setState(0);
            } else {
                droneStateDto.setMessage(4);
                droneStateDto.setState(4);
            }
            hashMapState.put(drone.getId(), droneStateDto);
        }

        List<DroneStateDto> listDroneFlighting = new ArrayList<>();
        listDroneFlighting = getAllDroneActiveRealTime();
        for (DroneStateDto droneStateDto : listDroneFlighting) {
            hashMapState.put(droneStateDto.getIdDrone(), droneStateDto);
        }

        List<DroneMaintenance> listDroneMaintenance = droneMaintenanceService.getAllNow();
        for (DroneMaintenance droneMaintenance : listDroneMaintenance) {
            DroneStateDto droneStateDto = new DroneStateDto(droneMaintenance.getId(), droneMaintenance.getName(), true);
            droneStateDto.setTimeStart(droneMaintenance.getTimeStart());
            droneStateDto.setTimeEnd(droneMaintenance.getTimeEnd());
            if (!droneMaintenance.isMaintenance()) {
                droneStateDto.setMessage(2);
                droneStateDto.setState(2);
                droneStateDto.setPercentBattery(100f - getPercent(droneMaintenance.getTimeStart(), droneMaintenance.getTimeEnd()));
            } else {
                droneStateDto.setMessage(3);
                droneStateDto.setState(3);
            }
            hashMapState.put(droneStateDto.getIdDrone(), droneStateDto);
        }

        List<DroneStateDto> list = new ArrayList<DroneStateDto>(hashMapState.values());


        return list;
    }



    public List<Drone> getAllDroneAvailable(Date timeStart, Date timeEnd){
        List<FlightItinerary> listFlightItinerary = flightItineraryService.getAll(timeStart, timeEnd);
        List<String> listIdDrone = new ArrayList<>();
        if (listFlightItinerary != null) {
            for (FlightItinerary flightItinerary : listFlightItinerary) {
                    listIdDrone.addAll(flightItinerary.getListIdDrone());
            }
        }

        List<DroneMaintenance> droneMaintenanceList = maintenanceRepository.findAll();
        for (DroneMaintenance droneMaintenance : droneMaintenanceList) {
            listIdDrone.add(droneMaintenance.getId());
        }

        List<Drone> drones = droneRepository.findByIdNotInAndIsUsed(listIdDrone, true);
        return drones;
    }

    public List<DroneStateDto> getAllDroneActive(Date timeStart, Date timeEnd){
        List<FlightItinerary> listFlightItinerary = flightItineraryService.getAll(timeStart, timeEnd);
        if (listFlightItinerary == null) {
            return null;
        }
        List<DroneStateDto> droneStateDtoList = new ArrayList<>();
        for (FlightItinerary flightItinerary : listFlightItinerary) {
            List<Drone> listDrone = droneRepository.findByIdInAndIsUsed(flightItinerary.getListIdDrone(), true);
            if (listDrone != null) {
                for (Drone drone : listDrone) {
                    DroneStateDto droneStateDto = new DroneStateDto(drone.getId(), drone.getName(), drone.isUsed());
                    droneStateDto.setState(1);
                    droneStateDto.setMessage(1);
                    droneStateDto.setTimeStart(flightItinerary.getTimeStart());
                    droneStateDto.setTimeEnd(flightItinerary.getTimeEnd());
                    droneStateDto.setContentProject(flightItinerary.getTask());
                    droneStateDto.setProject(flightItinerary.getTask());
                    droneStateDto.setNameTask(flightItinerary.getName());
                    droneStateDtoList.add(droneStateDto);
                }
            }

        }
        return droneStateDtoList;
    }

    public ServerResponseDto setDroneBroken(String id) {
        Optional<Drone> drone = droneRepository.findById(id);
        if (drone.isPresent()) {
            droneMaintenanceService.getBackDrone(id);
            drone.get().setUsed(false);
            droneService.save(drone.get());
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
        if (!drone.get().isUsed()) return new ServerResponseDto(ResponseCase.DRONE_BROKEN);
        FlightItinerary flightItinerary = flightItineraryService.getFlightPathRealTime(idDrone);
        if (flightItinerary == null) {
            return new ServerResponseDto(ResponseCase.DRONE_NOT_FLIGHT);
        }
        float locationLat = new Random().nextFloat();
        float locationLng = new Random().nextFloat();
        Date realTime = new Date();
        float percentBattery = ((float ) (flightItinerary.getTimeEnd().getTime() - realTime.getTime()))
                /((float) (flightItinerary.getTimeEnd().getTime() - flightItinerary.getTimeStart().getTime())) * 100f;
        String idPath = "0";
        for (LinkDronePath linkDronePath : flightItinerary.getLinkDronePaths()) {
            if (linkDronePath.getIdDrone().equals(idDrone)) {
                if (linkDronePath.getListIdFlightPath().size() > 0) {
                    idPath = linkDronePath.getListIdFlightPath().get(0);
                }
            }
        }
        Optional<FlightPath> flightPath = flightPathService.getById(idPath);
        FlightPath flightPath1 = null;
        List<String> listSupervisedObject = new ArrayList<>();
        if (!flightPath.isPresent()) {
            flightPath1 = flightPathService.getAll().get(0);
        } else {
            flightPath1 = flightPath.get();
        }

        for (FlightPoint flightPoint : flightPath1.getFlightPoints()) {
            listSupervisedObject.add(flightPoint.getIdSupervisedObject());
        }

        ParamFlightResponse paramFlightResponse = new ParamFlightResponse(locationLat, locationLng, idDrone, flightItinerary.getId(), flightItinerary.getIdSupervisedArea(),
                listSupervisedObject, flightItinerary.getIdCampaign(), 20.f, new Date(), 30.f, percentBattery, flightPath1);
        return new ServerResponseDto(ResponseCase.SUCCESS, paramFlightResponse);
    }

    public List<DroneStateDto> getAllDroneCharging(Date timeStart, Date timeEnd) {
        List<DroneMaintenance> droneMaintenanceList = droneMaintenanceService.getByDate(timeStart, timeEnd, false);
        List<DroneStateDto> droneList = new ArrayList<>();
        if (droneMaintenanceList.size() < 1) {
            return null;
        }
        for (DroneMaintenance droneMaintenance : droneMaintenanceList) {
            Optional<Drone> drone = droneService.getById(droneMaintenance.getId());
            if (drone.isPresent()) {
                if (drone.get().isUsed()) {
                    DroneStateDto droneStateDto = new DroneStateDto(drone.get().getId(), drone.get().getName(), true);
                    droneStateDto.setState(2);
                    droneStateDto.setMessage(2);
                    droneStateDto.setTimeEnd(droneMaintenance.getTimeEnd());
                    droneStateDto.setTimeStart(droneMaintenance.getTimeStart());
                    droneStateDto.setPercentBattery(100f - getPercent(droneMaintenance.getTimeStart(), droneMaintenance.getTimeEnd()));
                    droneList.add(droneStateDto);
                }
            }
        }
        return droneList;
    }


    public List<DroneStateDto> getAllDroneMaintenance(Date timeStart, Date timeEnd) {
        List<DroneMaintenance> droneMaintenanceList = droneMaintenanceService.getByDate(timeStart, timeEnd, true);
        List<DroneStateDto> droneList = new ArrayList<>();
        if (droneMaintenanceList.size() < 1) {
            return null;
        }
        for (DroneMaintenance droneMaintenance : droneMaintenanceList) {
            Optional<Drone> drone = droneService.getById(droneMaintenance.getId());
            if (drone.isPresent()) {
                if (drone.get().isUsed()) {
                    DroneStateDto droneStateDto = new DroneStateDto(drone.get().getId(), drone.get().getName(), true);
                    droneStateDto.setState(2);
                    droneStateDto.setMessage(2);
                    droneList.add(droneStateDto);
                    droneStateDto.setTimeEnd(droneMaintenance.getTimeEnd());
                    droneStateDto.setTimeStart(droneMaintenance.getTimeStart());
                }
            }
        }
        return droneList;
    }

    public ServerResponseDto getScheduleById(Date timeStart, Date timeEnd, String id) {
        Optional<Drone> droneOptional = droneService.getById(id);
        if (!droneOptional.isPresent()) {
            return new ServerResponseDto(ResponseCase.NOT_FOUND_DRONE);
        }
        List<DroneStateDto> droneStateDtoList = new ArrayList<>();
        Drone drone = droneOptional.get();
        DroneStateDto droneStateDto = new DroneStateDto(drone.getId(), drone.getName(), drone.isUsed());
        if (!drone.isUsed()) {
            droneStateDto.setState(4);
            droneStateDto.setMessage(4);
            droneStateDtoList.add(droneStateDto);
        }
        List<FlightItinerary> flightItineraries;
        if (timeStart != null && timeEnd != null) {
            flightItineraries = flightItineraryRepository.getPathByIdDroneDate(timeStart, timeEnd,  id);
        } else if (timeStart != null) {
            flightItineraries = flightItineraryRepository.getPathByIdDroneFrom(timeStart, id);
        } else if (timeEnd != null) {
            flightItineraries = flightItineraryRepository.getPathByIdDroneTo(timeEnd, id);
        } else {
            flightItineraries = flightItineraryRepository.findByIdDrone(id);
        }
        if (flightItineraries != null) {
            for (FlightItinerary flightItinerary : flightItineraries) {
                droneStateDto = new DroneStateDto(drone.getId(), drone.getName(), drone.isUsed());
                droneStateDto.setState(1);
                droneStateDto.setMessage(1);
                droneStateDto.setTimeStart(flightItinerary.getTimeStart());
                droneStateDto.setTimeEnd(flightItinerary.getTimeEnd());
                droneStateDto.setContentProject(flightItinerary.getTask());
                droneStateDto.setProject(flightItinerary.getTask());
                droneStateDto.setNameTask(flightItinerary.getName());
                droneStateDtoList.add(droneStateDto);
            }
        }
        Optional<DroneMaintenance> droneMaintenance = maintenanceRepository.findById(id);
        if (droneMaintenance.isPresent()) {
            droneStateDto = new DroneStateDto(drone.getId(), drone.getName(), drone.isUsed());
            droneStateDto.setTimeEnd(droneMaintenance.get().getTimeEnd());
            droneStateDto.setTimeStart(droneMaintenance.get().getTimeStart());
            if (droneMaintenance.get().isMaintenance()) {
                droneStateDto.setMessage(3);
                droneStateDto.setState(3);
            } else {
                droneStateDto.setMessage(2);
                droneStateDto.setState(2);
            }

            droneStateDtoList.add(droneStateDto);
        }
        if (droneStateDtoList.size() == 0) {
            return new ServerResponseDto(ResponseCase.DRONE_AVAILABLE);
        }
        return new ServerResponseDto(ResponseCase.SUCCESS, droneStateDtoList);
    }

    public List<DroneStateDto> getAllDroneActiveRealTime() {
        List<FlightItinerary> listFlightItinerary = flightItineraryService.getAllRealTime();
        if (listFlightItinerary == null) {
            return null;
        }
        List<DroneStateDto> droneStateDtoList = new ArrayList<>();
        for (FlightItinerary flightItinerary : listFlightItinerary) {
            List<Drone> listDrone = droneRepository.findByIdInAndIsUsed(flightItinerary.getListIdDrone(), true);
            if (listDrone != null) {
                for (Drone drone : listDrone) {
                    DroneStateDto droneStateDto = new DroneStateDto(drone.getId(), drone.getName(), drone.isUsed());
                    droneStateDto.setState(1);
                    droneStateDto.setMessage(1);
                    droneStateDto.setTimeStart(flightItinerary.getTimeStart());
                    droneStateDto.setTimeEnd(flightItinerary.getTimeEnd());
                    droneStateDto.setContentProject(flightItinerary.getTask());
                    droneStateDto.setProject(flightItinerary.getTask());
                    droneStateDto.setNameTask(flightItinerary.getName());
                    droneStateDtoList.add(droneStateDto);
                }
            }

        }
        return droneStateDtoList;
    }


    public ServerResponseDto setBrokenAll(List<String> listId) {
        for (String id : listId) {
            setDroneBroken(id);
        }
        return   new ServerResponseDto(ResponseCase.SUCCESS);
    }

    private float getPercent(Date startTime, Date endTime) {
        Date realTime = new Date();
        return  ((float ) (endTime.getTime() - realTime.getTime()))
                /((float) (endTime.getTime() - startTime.getTime())) * 100f;
    }
}
