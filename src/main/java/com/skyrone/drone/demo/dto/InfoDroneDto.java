package com.skyrone.drone.demo.dto;

import com.skyrone.drone.demo.model.Drone;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class InfoDroneDto {

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

    public InfoDroneDto() {

    }

    public InfoDroneDto(String id, String code, String name, String brand, String color, String dimensions, float maxFlightRange,
                        float maxFlightSpeed, float maxFlightTime, float maxFlightHeight, float rangeBattery) {
        this.code = code;
        this.id = id;
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

    public InfoDroneDto(Drone drone) {
        code = drone.getCode();
        id = drone.getId();
        name = drone.getName();
        brand = drone.getBrand();
        color = drone.getColor();
        dimensions = drone.getDimensions();
        maxFlightRange = drone.getMaxFlightRange();
        maxFlightSpeed = drone.getMaxFlightSpeed();
        maxFlightTime = drone.getMaxFlightTime();
        maxFlightHeight = drone.getMaxFlightHeight();
        rangeBattery = drone.getRangeBattery();
    }

}
