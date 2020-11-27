package com.skyrone.drone.demo.controller;

import com.skyrone.drone.demo.dto.FlightPathDto;
import com.skyrone.drone.demo.dto.ResponseCase;
import com.skyrone.drone.demo.dto.ServerResponseDto;
import com.skyrone.drone.demo.model.FlightPath;
import com.skyrone.drone.demo.service.FlightPathService;
import com.skyrone.drone.demo.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/flightPath")
public class FlightPathController {
    @Autowired
    FlightPathService flightPathService;

//    @GetMapping("/makeData")
//    public void makeData() {
//
//        FlightPath flightPath = new FlightPath("5fbc93a4b113564759f81c1f", "Duong bay 2", 1,
//                "idSupervisedArea", DateUtils.parseDate("2020-12-20"), DateUtils.parseDate("2020-12-31"), 100);
//        flightPathService.save(flightPath);
//    }

    @PostMapping("/save")
    public ResponseEntity<ServerResponseDto> savePath(@RequestBody FlightPath flightPath) {
        flightPathService.saveFlightPath(flightPath);
        return ResponseEntity.ok().body(new ServerResponseDto(ResponseCase.SUCCESS));
    }

    @GetMapping("getAllPathOfDrone")
    public ResponseEntity getAllPathOfDrone(@RequestParam("idDrone") String idDrone,
                                            @RequestParam(value = "timeStart", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") Date timeStart,
                                            @RequestParam(value = "timeEnd", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd")Date timeEnd) {
        return ResponseEntity.ok().body(flightPathService.getAllPathOfDrone(idDrone, timeStart, timeEnd));
    }


}