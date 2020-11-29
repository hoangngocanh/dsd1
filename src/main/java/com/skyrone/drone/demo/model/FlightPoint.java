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
@Document(collection = "flight_point")
public class FlightPoint {
    @Id
    private String id;
    private Float locationLat;
    private Float locationLng;
    private String idDrone;
    private String idFlightPath;
    private Float timeStop;
    private Date timeCome;
    private String note;
    private String idSupervisedObject;
    public FlightPoint() {
    }
}
