package com.skyrone.drone.demo.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
public class FormStateAll {
    private List<String> listId;
    private Date timeStart;
    private Date timeEnd;
    private boolean isMaintenance;

    public FormStateAll(List<String> listId, Date timeStart, Date timeEnd, boolean isMaintenance) {
        this.listId = listId;
        this.timeStart = timeStart;
        this.timeEnd = timeEnd;
        this.isMaintenance = isMaintenance;
    }


    public FormStateAll() {
    }
}
