package com.skyrone.drone.demo.controller;

import com.skyrone.drone.demo.dto.DroneStateDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/droneState")
public class DroneStateController {

    @GetMapping("/getAllState")
    public ResponseEntity<DroneStateDto> getAllState() {
return null;
    }
}
