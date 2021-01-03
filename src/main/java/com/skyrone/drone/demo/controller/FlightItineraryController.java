package com.skyrone.drone.demo.controller;

import com.skyrone.drone.demo.dto.ResponseCase;
import com.skyrone.drone.demo.dto.ServerResponseDto;
import com.skyrone.drone.demo.model.FlightItinerary;
import com.skyrone.drone.demo.service.FlightItineraryService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/flightItinerary")
public class FlightItineraryController {
    @Autowired
    FlightItineraryService flightItineraryService;


    @PostMapping("/save")
    public ResponseEntity<ServerResponseDto> savePath(@RequestBody FlightItinerary flightItinerary) {

        return ResponseEntity.ok().body(flightItineraryService.save(flightItinerary));
    }

    @GetMapping("/getAll")
    @ApiOperation(value = "Ngày giờ có dạng \"yyyy-MM-dd HH:mm:ss\" vd: 2020-11-30 11: 11:11 __ Lấy tất cả danh sách hành trình bay theo ngày giờ")
    public ResponseEntity getAll(@RequestParam(value = "timeStart", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") Date timeStart,
                                     @RequestParam(value = "timeEnd", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd")Date timeEnd) {
        return ResponseEntity.ok().body(flightItineraryService.getAll(timeStart, timeEnd));
    }


    @GetMapping("/delete/{id}")
    @ApiOperation(value = "Xoá đường bay")
    public ResponseEntity delete(@PathVariable String id) {
        flightItineraryService.delete(id);
        return ResponseEntity.ok().body(new ServerResponseDto(ResponseCase.SUCCESS));
    }

    @GetMapping("/delteByCampain/{id}")
    @ApiOperation(value = "Xoa hanh trinh bay theo dot giam sat")
    public ResponseEntity deleteByCampaign(@PathVariable String id) {
        flightItineraryService.deleteByCampaign(id);
        return ResponseEntity.ok().body(new ServerResponseDto(ResponseCase.SUCCESS));
    }

}
