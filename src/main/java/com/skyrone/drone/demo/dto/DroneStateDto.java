package com.skyrone.drone.demo.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class DroneStateDto {
    private String idDrone;
    private String name;
    private int state;
    private String message;
    private boolean isUsed;
    private Date timeStart;
    private Date timeEnd;
    public DroneStateDto() {
    }

    public DroneStateDto(String idDrone, String name, boolean isUsed) {
        this.idDrone = idDrone;
        this.name = name;
        this.isUsed = isUsed;
    }

    public void setMessage(int state) {
        if (state == 0) {
            this.message = "Đang Rảnh";
        } else if (state == 1) {
            this.message = "Đang Bay";
        } else if (state == 2) {
            this.message = "Đang Sạc";
        } else if (state == 3) {
            this.message = "Đang Bảo trì";
        } else {
            this.message = "Hỏng";
        }
    }
}
