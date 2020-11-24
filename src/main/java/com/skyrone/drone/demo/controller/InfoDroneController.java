package com.skyrone.drone.demo.controller;

import com.skyrone.drone.demo.dto.ResponseCase;
import com.skyrone.drone.demo.dto.ServerResponseDto;
import com.skyrone.drone.demo.model.Drone;
import com.skyrone.drone.demo.service.DroneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.concurrent.ThreadLocalRandom;

public class InfoDroneController {
    @Autowired
    DroneService droneService;

    @GetMapping("/getAll")
    public ResponseEntity<ServerResponseDto> getAllDrone() {
        return ResponseEntity.ok().body(new ServerResponseDto(ResponseCase.SUCCESS, droneService.findAll()));
    }

    @GetMapping("/getInfo")
    public ResponseEntity getInfo() {
        return ResponseEntity.ok().body(droneService.getInfo());
    }

    @GetMapping("/getActive")
    public ResponseEntity<ServerResponseDto> getActive() {
        return ResponseEntity.ok().body(new ServerResponseDto(ResponseCase.SUCCESS, droneService.getActive()));
    }

    @PostMapping("/save")
    public ResponseEntity<ServerResponseDto> save(Drone drone) {
        droneService.save(drone);
        return ResponseEntity.ok().body(new ServerResponseDto(ResponseCase.SUCCESS));
    }

    @GetMapping("/makeRandomData")
    public void makeRandomData() {
        for (int i = 0; i < 5; i++) {
            int random = ThreadLocalRandom.current().nextInt(54, 99);
//            Drone drone = new Drone("phantom max 2020"+random, "phantom", "black", "1.2x0.95x0.7 m", 15000, 30, 80, 200, 95000);
            Drone drone = new Drone(false, 2, "41 24.2028, 2 10.4418", 93.3f, 2300, 15, 3, 100, "phantom max 2020"+random, "phantom", "white", "1.2x0.95x0.62 m", 13000, 30, 70, 220, 95000);

            droneService.save(drone);
        }
    }
}
