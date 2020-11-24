package com.skyrone.drone.demo.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@Document(collection = "info_drone")
public class InfoDrone {

    private String id;
    private String code;
    private String name;
    private String brand;
    private String color;
    private String dimensions;
    private float maxFlightRange; //m
    private float maxFlightSpeed;       // m/phút
    private float maxFlightTime; //phut'
    private float maxFlightHeight;    //m
    private float rangeBattery;
    private boolean isUsed;

    InfoDrone () {

    }

    public InfoDrone(String id, String code, String name, String brand, String color, String dimensions, float maxFlightRange,
                     float maxFlightSpeed, float maxFlightTime, float maxFlightHeight, float rangeBattery, boolean isUsed) {
        this.id = id;
        this.code = code;
        this.name = name;
        this.brand = brand;
        this.color = color;
        this.dimensions = dimensions;
        this.maxFlightRange = maxFlightRange;
        this.maxFlightSpeed = maxFlightSpeed;
        this.maxFlightTime = maxFlightTime;
        this.maxFlightHeight = maxFlightHeight;
        this.rangeBattery = rangeBattery;
        this.isUsed = isUsed;
    }
}
