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
@Document(collection = "flight_path")
public class FlightPath {
    @Id
    private String id;
    private String idDrone;
    private String name;
    private int task;
    private String idSupervisedArea;
    private Date timeStart;
    private Date timeEnd;
    private float heightFlight;

    public FlightPath(String idDrone, String name, int task, String idSupervisedArea, Date timeStart, Date timeEnd, float heightFlight) {
        this.idDrone = idDrone;
        this.name = name;
        this.task = task;
        this.idSupervisedArea = idSupervisedArea;
        this.timeStart = timeStart;
        this.timeEnd = timeEnd;
        this.heightFlight = heightFlight;
    }

    public FlightPath() {
    }
}
