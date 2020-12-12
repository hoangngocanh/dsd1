package com.skyrone.drone.demo.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Document(collection = "flight_itinerary")
public class FlightItinerary {
    @Id
    private String id;
    private String idCampaign;
    private List<LinkDronePath> linkDronePaths;
    private String name;
    private int task = 0;
    private String idSupervisedArea;
    private Date timeStart;
    private Date timeEnd;



    public FlightItinerary(List<LinkDronePath> linkDronePaths, String name, int task, String idSupervisedArea, Date timeStart, Date timeEnd) {
        this.linkDronePaths = linkDronePaths;
        this.name = name;
        this.task = task;
        this.idSupervisedArea = idSupervisedArea;
        this.timeStart = timeStart;
        this.timeEnd = timeEnd;
    }

    public FlightItinerary() {
    }

    @JsonIgnore
    public List<String> getListIdDrone() {
        List<String> listIdDrone = new ArrayList<>();
        if (this.linkDronePaths == null) {
            return listIdDrone;
        }
        for (LinkDronePath linkDronePath : this.linkDronePaths) {
            listIdDrone.add(linkDronePath.getIdDrone());
        }
        return listIdDrone;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIdCampaign() {
        return idCampaign;
    }

    public void setIdCampaign(String idCampaign) {
        this.idCampaign = idCampaign;
    }

    public List<LinkDronePath> getLinkDronePaths() {
        return linkDronePaths;
    }

    public void setLinkDronePaths(List<LinkDronePath> linkDronePaths) {
        this.linkDronePaths = linkDronePaths;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getTask() {
        return task;
    }

    public void setTask(int task) {
        this.task = task;
    }

    public String getIdSupervisedArea() {
        return idSupervisedArea;
    }

    public void setIdSupervisedArea(String idSupervisedArea) {
        this.idSupervisedArea = idSupervisedArea;
    }

    public Date getTimeStart() {
        return timeStart;
    }

    public void setTimeStart(Date timeStart) {
        this.timeStart = timeStart;
    }

    public Date getTimeEnd() {
        return timeEnd;
    }

    public void setTimeEnd(Date timeEnd) {
        this.timeEnd = timeEnd;
    }
}
