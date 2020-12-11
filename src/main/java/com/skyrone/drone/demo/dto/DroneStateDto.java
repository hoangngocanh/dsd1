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
    private int task = 0;
    private String contentTask = "Chưa được phân công";

    public DroneStateDto() {
    }

    public DroneStateDto(String idDrone, String name, boolean isUsed) {
        this.idDrone = idDrone;
        this.name = name;
        this.isUsed = isUsed;
    }

    public void setContentTask(int task) {
         if (task == 1) {
            this.contentTask = "Giám sát cháy rừng";
        } else if (task == 2) {
            this.contentTask = "Giám sát đê điều ";
        } else if (task == 3) {
            this.contentTask = "Giám sát lưới điẹn";
         } else if (task == 4) {
             this.contentTask = "Giám sát cây trồng";
         } else {
             this.contentTask = "Chưa được phân công";
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
