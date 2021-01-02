package com.skyrone.drone.demo.service;

import com.skyrone.drone.demo.controller.FlightItineraryController;
import com.skyrone.drone.demo.dto.DroneStateDto;
import com.skyrone.drone.demo.dto.ResponseCase;
import com.skyrone.drone.demo.dto.ServerResponseDto;
import com.skyrone.drone.demo.model.Drone;
import com.skyrone.drone.demo.model.FlightItinerary;
import com.skyrone.drone.demo.model.FlightPath;
import com.skyrone.drone.demo.model.LinkDronePath;
import com.skyrone.drone.demo.repository.DroneRepository;
import com.skyrone.drone.demo.repository.FlightItineraryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class FlightItineraryService {
    @Autowired
    FlightItineraryRepository flightItineraryRepository;


    @Autowired
    DroneRepository droneRepository;


    @Autowired
    DroneStateService droneStateService;

    @Autowired
    FlightPathService flightPathService;



    public ServerResponseDto save(FlightItinerary flightItinerary) {
        for (LinkDronePath linkDronePath : flightItinerary.getLinkDronePaths()) {
            String idDrone = linkDronePath.getIdDrone();
            Optional<Drone> drone = droneRepository.findById(idDrone);

            if (!drone.isPresent()) {
                return new ServerResponseDto(ResponseCase.NOT_FOUND_DRONE, idDrone);
            }

            if (linkDronePath.getListIdFlightPath() == null) {
                return new ServerResponseDto(ResponseCase.NOT_FOUND_PATH, "null");
            } else {
                for (String idPath : linkDronePath.getListIdFlightPath()) {
                    Optional<FlightPath> flightPath = flightPathService.getById(idPath);
                    if (!flightPath.isPresent()) {
                        return new ServerResponseDto(ResponseCase.NOT_FOUND_PATH, "idPath = "+ idPath);
                    }
                }
            }
        }
        return new ServerResponseDto(ResponseCase.SUCCESS, flightItineraryRepository.save(flightItinerary));
    }

    public List<FlightItinerary> getAll(Date timeStart, Date timeEnd) {
        if (timeStart != null && timeEnd != null) {
            return flightItineraryRepository.getAllPathActive(timeStart, timeEnd);
        } else if (timeStart != null) {
            return flightItineraryRepository.getAllPathActiveFrom(timeStart);
        } else if (timeEnd != null) {
            return flightItineraryRepository.getAllPathActiveTo(timeEnd);
        } else {
            return flightItineraryRepository.findAll();
        }
    }


    public List<FlightItinerary> getAllOfDrone(String idDrone, Date timeStart, Date timeEnd) {
        Optional<Drone> drone = droneRepository.findById(idDrone);
        if (!drone.isPresent()) {
            return null;
        }
        if (!drone.get().isUsed()) {
            return null;
        }
        List<FlightItinerary> flightItineraries;
        if (timeStart != null && timeEnd != null) {
            flightItineraries = flightItineraryRepository.getPathByIdDroneDate(timeStart, timeEnd, idDrone);
        } else if (timeStart != null) {
            flightItineraries = flightItineraryRepository.getPathByIdDroneFrom(timeStart, idDrone);
        } else if (timeEnd != null) {
            flightItineraries = flightItineraryRepository.getPathByIdDroneTo(timeEnd, idDrone);
        } else {
            flightItineraries = flightItineraryRepository.findByIdDrone(idDrone);
        }

        if (flightItineraries == null) {
            return null;
        }
        return flightItineraries;
    }


    public FlightItinerary getFlightPathRealTime(String idDrone) {
        List<FlightItinerary> flightItineraryList =  flightItineraryRepository.getByIdDroneRealTime(new Date(), idDrone);
        if (flightItineraryList.size() < 1) {
            return null;
        }
        return flightItineraryList.get(0);
    }

    public void delete(String id) {
        flightItineraryRepository.deleteById(id);
    }

    public List<FlightItinerary> getAllRealTime() {
        return flightItineraryRepository.getAllPathActiveRealTime(new Date());
    }
}
