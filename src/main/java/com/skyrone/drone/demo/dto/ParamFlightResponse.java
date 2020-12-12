package com.skyrone.drone.demo.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
public class ParamFlightResponse {
    private Float locationLat;
    private Float locationLng;
    private String idDrone;
    private String idFlightPath;
    private String idSupervisedArea;
    private List<String> idSupervisedObject;
    private String idCampaign;
    private Float heightFlight;
    private Date time;
    private Float speed;
    private Float percentBattery;

    public ParamFlightResponse() {
    }

    public ParamFlightResponse(Float locationLat, Float locationLng, String idDrone, String idFlightPath, String idSupervisedArea,
                               List<String> idSupervisedObject, String idCampaign, Float heightFlight, Date time, Float speed,Float percentBattery) {
        this.locationLat = locationLat;
        this.locationLng = locationLng;
        this.idDrone = idDrone;
        this.idFlightPath = idFlightPath;
        this.idSupervisedObject = idSupervisedObject;
        this.idCampaign = idCampaign;
        this.heightFlight = heightFlight;
        this.time = time;
        this.speed = speed;
        this.idSupervisedArea = idSupervisedArea;
        this.percentBattery = percentBattery;
    }
}
