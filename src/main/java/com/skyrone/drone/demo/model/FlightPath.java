package com.skyrone.drone.demo.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@Document(collection = "flight_path")
public class FlightPath {
    @Id
    private String id;
    private String name;
    private String idSupervisedArea;
    private Float averageHeight;
    private Float distance;

    private List<FlightPoint> flightPoints;

    public FlightPath() {
    }
}
