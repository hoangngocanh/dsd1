package com.skyrone.drone.demo.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Getter
@Setter
@Document(collection = "drone_maintenance")
public class DroneMaintenance {
    @Id
    private String id;
    private String name;
    private Date startTime;
    private Date endTime;
    private boolean isMaintenance;

    public DroneMaintenance(String name, Date startTime, Date endTime) {
        this.name = name;
        this.startTime = startTime;
        this.endTime = endTime;
    }
}
