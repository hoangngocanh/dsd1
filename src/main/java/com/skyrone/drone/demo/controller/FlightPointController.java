package com.skyrone.drone.demo.controller;

import com.skyrone.drone.demo.dto.ResponseCase;
import com.skyrone.drone.demo.dto.ServerResponseDto;
import com.skyrone.drone.demo.model.FlightPoint;
import com.skyrone.drone.demo.service.FlightPointService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/flightPoint")
public class FlightPointController {
    @Autowired
    FlightPointService flightPointService;

    @PostMapping("save")
    ResponseEntity<ServerResponseDto> saveFlightPoint(@RequestBody FlightPoint flightPoint) {
        flightPointService.save(flightPoint);
        return ResponseEntity.ok().body(new ServerResponseDto(ResponseCase.SUCCESS));
    }

    @GetMapping("getPointOfPath/{id}")
    ResponseEntity getPointOfPath(@PathVariable("id")String id) {
        return ResponseEntity.ok().body(flightPointService.getByIdPath(id));
    }

}
