package com.skyrone.drone.demo.dto;

import com.skyrone.drone.demo.model.FlightItinerary;
import com.skyrone.drone.demo.model.FlightPath;
import com.skyrone.drone.demo.model.FlightPoint;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
public class FlightPathDto {
    private String id;
    private String name;
    private String idSupervisedArea;

    private List<FlightPoint> flightPoints;

    public FlightPathDto(FlightPath flightPath, List<FlightPoint> flightPoints) {
        this.id = flightPath.getId();
        this.name = flightPath.getName();
        this.idSupervisedArea = flightPath.getIdSupervisedArea();
        this.flightPoints = flightPoints;
    }
}
