package com.skyrone.drone.demo.dto;

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
    private String idDrone;
    private String name;
    private int task;
    private String idSupervisedArea;
    private Date timeStart;
    private Date timeEnd;
    private float heightFlight;

    private List<FlightPoint> flightPoints;

    public FlightPathDto(FlightPath flightPath, List<FlightPoint> flightPoints) {
        this.id = flightPath.getId();
        this. idDrone = flightPath.getIdDrone();
        this.name = flightPath.getName();
        this.task = flightPath.getTask();
        this.idSupervisedArea = flightPath.getIdSupervisedArea();
        this.timeStart = flightPath.getTimeStart();
        this.timeEnd = flightPath.getTimeEnd();
        this.heightFlight = flightPath.getHeightFlight();

        this.flightPoints = flightPoints;
    }
}
