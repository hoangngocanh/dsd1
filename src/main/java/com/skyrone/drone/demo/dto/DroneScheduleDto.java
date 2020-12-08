package com.skyrone.drone.demo.dto;


import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class DroneScheduleDto {
    private String idDrone;
    private String name;
    private int state;
    private String message;
    private Date timeStart;
    private Date timeEnd;
}
