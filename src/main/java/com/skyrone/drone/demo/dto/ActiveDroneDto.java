package com.skyrone.drone.demo.dto;

import com.skyrone.drone.demo.model.Drone;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ActiveDroneDto {
    private double idLog;
    private boolean isUsed;
    private int task;
    private String location;
    private float percentBattery;
    private float flightRange; //m
    private float flightSpeed;       // m/phút
    private float flightTime; //phut'
    private float flightHeight;    //m

    public ActiveDroneDto() {
    }

    public ActiveDroneDto(double idLog, boolean isUsed, int task, String location, float percentBattery,
                          float flightRange, float flightSpeed, float flightTime, float flightHeight) {
        this.idLog = idLog;
        this.isUsed = isUsed;
        this.task = task;
        this.location = location;
        this.percentBattery = percentBattery;
        this.flightRange = flightRange;
        this.flightSpeed = flightSpeed;
        this.flightTime = flightTime;
        this.flightHeight = flightHeight;
    }

    public ActiveDroneDto(Drone drone) {
        idLog = drone.getIdLog();
        isUsed = drone.isUsed();
        task = drone.getTask();
        location = drone.getLocation();
        percentBattery = drone.getPercentBattery();
        flightHeight = drone.getFlightHeight();
        flightRange = drone.getFlightRange();
        flightSpeed = drone.getFlightSpeed();
        flightTime = drone.getFlightTime();
    }
}
