package com.skyrone.drone.demo.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
public class FlightPoint {

    private Float locationLat;
    private Float locationLng;
    private String idFlightPath;
    private Float timeStop;
    private Float timeCome;
    private String note;
    private String idSupervisedObject;
    private Float flightHeight;

    public FlightPoint() {
    }
}
