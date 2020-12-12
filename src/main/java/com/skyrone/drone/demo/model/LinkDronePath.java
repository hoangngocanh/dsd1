package com.skyrone.drone.demo.model;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class LinkDronePath {
    private String idDrone;
    private List<String> listIdFlightPath;

    public LinkDronePath(String idDrone, List<String> listIdFlightPath) {
        this.idDrone = idDrone;
        this.listIdFlightPath = listIdFlightPath;
    }

    public LinkDronePath() {
    }
}
