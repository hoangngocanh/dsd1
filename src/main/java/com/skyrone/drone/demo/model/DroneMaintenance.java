package com.skyrone.drone.demo.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Getter
@Setter
@Document(collection = "drone_maintenance")
public class DroneMaintenance {
    @Id
    private String id;
    private String name;
    private Date timeStart;
    private Date timeEnd;
    private boolean isMaintenance;

    public DroneMaintenance(String name, Date timeStart, Date timeEnd) {
        this.name = name;
        this.timeStart = timeStart;
        this.timeEnd = timeEnd;
    }

    public DroneMaintenance(String id, String name, Date timeStart, Date timeEnd, boolean isMaintenance) {
        this.id = id;
        this.name = name;
        this.timeStart = timeStart;
        this.timeEnd = timeEnd;
        this.isMaintenance = isMaintenance;
    }

    public DroneMaintenance() {
    }
}
