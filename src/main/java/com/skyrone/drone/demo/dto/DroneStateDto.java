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
    private int project = 0;
    private String contentProject = "Chưa được phân công";
    private String nameTask;
    private float percentBattery;

    public DroneStateDto() {
    }

    public DroneStateDto(String idDrone, String name, boolean isUsed) {
        this.idDrone = idDrone;
        this.name = name;
        this.isUsed = isUsed;
    }

    public void setContentProject(int task) {
         if (task == 1) {
            this.contentProject = "Giám sát cháy rừng";
        } else if (task == 2) {
            this.contentProject = "Giám sát đê điều ";
        } else if (task == 3) {
            this.contentProject = "Giám sát lưới điện";
         } else if (task == 4) {
             this.contentProject = "Giám sát cây trồng";
         } else {
             this.contentProject = "Chưa được phân công";
         }
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
