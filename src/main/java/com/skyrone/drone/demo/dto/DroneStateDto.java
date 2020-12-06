package com.skyrone.drone.demo.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DroneStateDto {
    private String id;
    private String name;
    private int state;
    private String message;
    private boolean isUsed;

    public DroneStateDto() {
    }

    public void setMessage() {
        if (state == 0) {
            this.message = "Đang Bay";
        } else if (state == 1) {
            this.message = "Đang Rảnh";
        } else if (state == 2) {
            this.message = "Đang Sạc";
        } else if (state == 3) {
            this.message = "Đang Bảo trì";
        } else {
            this.message = "Hỏng";
        }
    }
}
