package com.skyrone.drone.demo.service;

import com.skyrone.drone.demo.dto.DroneStateDto;
import com.skyrone.drone.demo.dto.ResponseCase;
import com.skyrone.drone.demo.dto.ServerResponseDto;
import com.skyrone.drone.demo.model.Drone;
import com.skyrone.drone.demo.model.FlightItinerary;
import com.skyrone.drone.demo.model.LinkDronePath;
import com.skyrone.drone.demo.repository.DroneRepository;
import com.skyrone.drone.demo.repository.FlightPathItinerary;
import com.skyrone.drone.demo.repository.FlightPointRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class FlightItineraryService {
    @Autowired
    FlightPathItinerary flightPathItinerary;

    @Autowired
    FlightPointRepository flightPointRepository;

    @Autowired
    DroneRepository droneRepository;

    @Autowired
    FlightPointService flightPointService;

    @Autowired
    DroneStateService droneStateService;



    public ServerResponseDto save(FlightItinerary flightItinerary) {
        for (LinkDronePath idDrones : flightItinerary.getLinkDronePaths()) {
            String idDrone = idDrones.getIdDrone();
            DroneStateDto droneStateDto = droneStateService.getById(idDrone);
            if (droneStateDto == null) {
                return new ServerResponseDto(ResponseCase.NOT_FOUND_DRONE, idDrone);
            }
            if (droneStateDto.getState() != 0) {
                return new ServerResponseDto(ResponseCase.DRONE_BUSY, idDrone);
            }
        }
        return new ServerResponseDto(ResponseCase.SUCCESS, flightPathItinerary.save(flightItinerary));
    }

    public List<FlightItinerary> getAll(Date timeStart, Date timeEnd) {
        if (timeStart != null && timeEnd != null) {
            return flightPathItinerary.getAllPathActive(timeStart, timeEnd);
        } else if (timeStart != null) {
            return flightPathItinerary.getAllPathActiveFrom(timeStart);
        } else if (timeEnd != null) {
            return flightPathItinerary.getAllPathActiveTo(timeEnd);
        } else {
            return flightPathItinerary.findAll();
        }
    }


    public List<FlightItinerary> getAllhOfDrone(String idDrone, Date timeStart, Date timeEnd) {
        Optional<Drone> drone = droneRepository.findById(idDrone);
        if (!drone.isPresent()) {
            return null;
        }
        if (!drone.get().isUsed()) {
            return null;
        }
        List<FlightItinerary> flightItineraries;
        if (timeStart != null && timeEnd != null) {
            flightItineraries = flightPathItinerary.getPathByIdDroneDate(timeStart, timeEnd, idDrone);
        } else if (timeStart != null) {
            flightItineraries = flightPathItinerary.getPathByIdDroneFrom(timeStart, idDrone);
        } else if (timeEnd != null) {
            flightItineraries = flightPathItinerary.getPathByIdDroneTo(timeEnd, idDrone);
        } else {
            flightItineraries = flightPathItinerary.findByIdDrone(idDrone);
        }

        if (flightItineraries == null) {
            return null;
        }
        return flightItineraries;
    }


    public FlightItinerary getFlightPathRealTime(String idDrone) {
        List<FlightItinerary> flightItineraryList =  flightPathItinerary.getByIdDroneRealTime(new Date(), idDrone);
        if (flightItineraryList.size() < 1) {
            return null;
        }
        return flightItineraryList.get(0);
    }

    public void delete(String id) {
        flightPathItinerary.deleteById(id);
    }

}
