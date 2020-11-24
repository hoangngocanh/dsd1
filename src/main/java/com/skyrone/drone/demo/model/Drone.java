package com.skyrone.drone.demo.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@Document(collection = "drone")
public class Drone {
    @Id
    private String id;
    private String code;
    private double idLog;
    private boolean isUsed;
    private int task;
    private String location;
    private float percentBattery;
    private float flightRange; //m
    private float flightSpeed;       // m/phút
    private float flightTime; //phut'
    private float flightHeight;    //m

    //Static Data
    private String name;
    private String brand;
    private String color;
    private String dimensions;
    private float maxFlightRange; //m
    private float maxFlightSpeed;       // m/phút
    private float maxFlightTime; //phut'
    private float maxFlightHeight;    //m
    private float rangeBattery;

    public Drone() {
    }

    public Drone(double idLog, boolean isUsed, int task, String location, float percentBattery, float flightRange,
                 float flightSpeed, float flightTime, float flightHeight) {
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

    public Drone(String code, String name, String brand, String color, String dimensions,
                 float maxFlightRange, float maxFlightSpeed, float maxFlightTime, float maxFlightHeight, float rangeBattery) {
        this.code = code;
        this.name = name;
        this.brand = brand;
        this.color = color;
        this.dimensions = dimensions;
        this.maxFlightRange = maxFlightRange;
        this.maxFlightSpeed = maxFlightSpeed;
        this.maxFlightTime = maxFlightTime;
        this.maxFlightHeight = maxFlightHeight;
        this.rangeBattery = rangeBattery;  //mAh
    }

    public Drone(boolean isUsed, int task, String location, float percentBattery, float flightRange, float flightSpeed,
                 float flightTime, float flightHeight, String name, String brand, String color, String dimensions, float maxFlightRange, float maxFlightSpeed, float maxFlightTime, float maxFlightHeight, float rangeBattery) {
        this.isUsed = isUsed;
        this.task = task;
        this.location = location;
        this.percentBattery = percentBattery;
        this.flightRange = flightRange;
        this.flightSpeed = flightSpeed;
        this.flightTime = flightTime;
        this.flightHeight = flightHeight;
        this.name = name;
        this.brand = brand;
        this.color = color;
        this.dimensions = dimensions;
        this.maxFlightRange = maxFlightRange;
        this.maxFlightSpeed = maxFlightSpeed;
        this.maxFlightTime = maxFlightTime;
        this.maxFlightHeight = maxFlightHeight;
        this.rangeBattery = rangeBattery;
    }

}
