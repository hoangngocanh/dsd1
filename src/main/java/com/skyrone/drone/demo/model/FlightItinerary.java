package com.skyrone.drone.demo.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@Document(collection = "flight_itinerary")
public class FlightItinerary {
    @Id
    private String id;
    private String idCampaign;
    private List<String> idDroneList;
    private String name;
    private int task;
    private String idSupervisedArea;
    private Date timeStart;
    private Date timeEnd;
    private List<String> idLightPath;

    public FlightItinerary(List<String> idDroneList, String name, int task, String idSupervisedArea, Date timeStart, Date timeEnd) {
        this.idDroneList = idDroneList;
        this.name = name;
        this.task = task;
        this.idSupervisedArea = idSupervisedArea;
        this.timeStart = timeStart;
        this.timeEnd = timeEnd;
    }

    public FlightItinerary() {
    }
}
