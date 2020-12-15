package com.skyrone.drone.demo.controller;

import com.skyrone.drone.demo.dto.ResponseCase;
import com.skyrone.drone.demo.dto.ServerResponseDto;
import com.skyrone.drone.demo.model.FlightItinerary;
import com.skyrone.drone.demo.model.FlightPath;
import com.skyrone.drone.demo.service.FlightPathService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

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

        return ResponseEntity.ok().body(new ServerResponseDto(ResponseCase.ADD_SUCCESS, flightPathService.save(flightPath)));
    }

    @PostMapping("/saveList")
    public ResponseEntity<ServerResponseDto> savePath(@RequestBody List<FlightPath> flightPathList) {
        flightPathService.saveList(flightPathList);
        return ResponseEntity.ok().body(new ServerResponseDto(ResponseCase.ADD_SUCCESS));
    }

    @GetMapping("/getAllPath")
    @ApiOperation(value = "Lấy tất cả danh sách đường bay")
    public ResponseEntity getAllPath() {
        return ResponseEntity.ok().body(flightPathService.getAll());
    }


    @GetMapping("/delete/{id}")
    @ApiOperation(value = "Xoá đường bay")
    public ResponseEntity delete(@PathVariable String id) {
        flightPathService.delete(id);
        return ResponseEntity.ok().body(new ServerResponseDto(ResponseCase.SUCCESS));
    }

    @GetMapping("/getAllBySupervisedArea/{id}")
    @ApiOperation(value = "Lấy tất cả hành trình bay từ id zone miên giám sát")
    public ResponseEntity getAllBySupervisedArea(@PathVariable String id) {
        return ResponseEntity.ok().body(flightPathService.getAllBySupervisedArea(id));
    }
}
