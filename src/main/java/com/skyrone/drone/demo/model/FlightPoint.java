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
    private String location;
    private String idDrone;
    private String idFlightPath;
    private String timeStop;
    private Date timeCome;
}
