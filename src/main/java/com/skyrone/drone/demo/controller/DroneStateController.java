package com.skyrone.drone.demo.controller;

import com.skyrone.drone.demo.dto.DroneStateDto;
import com.skyrone.drone.demo.dto.ServerResponseDto;
import com.skyrone.drone.demo.service.DroneStateService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/droneState")
public class DroneStateController {

    @Autowired
    DroneStateService droneStateService;

    @ApiOperation("Lấy danh sách tình trạng drone")
    @GetMapping("/getAllState")
    public ResponseEntity getAllState() {
        return ResponseEntity.ok().body(droneStateService.getAll());
    }

    @ApiOperation("Lấy thông tin tình trạng của 1 drone theo id")
    @GetMapping("/getById/{id}")
    public ResponseEntity<DroneStateDto> getById(@PathVariable String id) {
        return  ResponseEntity.ok().body(droneStateService.getById(id));
    }

    @ApiOperation(value = "Ngày giờ có dạng \"yyyy-MM-dd HH:mm:ss\" vd: 2020-11-30 11: 11:11 " +
            ">> Lấy danh sách drone đang bay trong khoảng thời gian. timeStart <= t <= timeEnd" +
            " *** if timeStart == null && timeEnd == null lọc tất cả danh sách dron hoạt động" +
            "*** if 1 trong 2 cái == null lọc sau ngày timeStart hoặc trước ngày timeEnd ", response = List.class)
    @GetMapping("/getAllDroneActive")
    public ResponseEntity getAllDroneActive(@RequestParam(value = "timeStart", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") Date timeStart,
                                            @RequestParam(value = "timeEnd", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")Date timeEnd) {
        return ResponseEntity.ok().body(droneStateService.getAllDroneActive(timeStart, timeEnd));
    }

    @ApiOperation(value = "Ngày giờ có dạng \"yyyy-MM-dd HH:mm:ss\" vd: 2020-11-30 11: 11:11 " +
            "Lấy danh sách drone có sẵn nhưng chưa hoạt động đang ở trạng thái chờ trong kho", response = List.class)
    @GetMapping("/getAllDroneAvailable")
    public ResponseEntity getAllDroneAvailable(@RequestParam(value = "timeStart", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")Date timeStart,
                                               @RequestParam(value = "timeEnd", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")Date timeEnd) {
        return ResponseEntity.ok().body(droneStateService.getAllDroneAvailable(timeStart, timeEnd));
    }

    @ApiOperation("Đánh giấu drone bị hỏng")
    @GetMapping("/setDroneBroken/{id}")
    public ResponseEntity<ServerResponseDto> setDroneBroken(@PathVariable("id") String id) {
        return ResponseEntity.ok().body(droneStateService.setDroneBroken(id));
    }

    @ApiOperation("Lấy danh sách drone bị hỏng")
    @GetMapping("/getAllDroneBroken")
    public ResponseEntity getAllDroneBroken() {
        return ResponseEntity.ok().body(droneStateService.getAllDroneBroken());
    }

    @ApiOperation(value = "Ngày giờ có dạng \"yyyy-MM-dd HH:mm:ss\" vd: 2020-11-30 11: 11:11 " +
            ">> Lấy danh sách drone đang sạc trong khoảng thời gian. timeStart <= t <= timeEnd" +
            " *** if timeStart == null && timeEnd == null lọc tất cả danh sách dron" +
            "*** if 1 trong 2 cái == null lọc sau ngày timeStart hoặc trước ngày timeEnd ", response = List.class)
    @GetMapping("/getAllDroneCharging")
    public ResponseEntity getAllDroneCharging(@RequestParam(value = "timeStart", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") Date timeStart,
                                            @RequestParam(value = "timeEnd", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")Date timeEnd) {
        return ResponseEntity.ok().body(droneStateService.getAllDroneCharging(timeStart, timeEnd));
    }

    @GetMapping("/getParameterFlightRealTime/{id}")
    public ResponseEntity<ServerResponseDto> getParameterFlightRealTime(@PathVariable("id") String id) {
        return ResponseEntity.ok().body(droneStateService.getParameterFlightRealTime(id));
    }

    @ApiOperation(value = "Lấy danh sách drone đang bảo trì trong khoảng thời gian", response = List.class)
    @GetMapping("/getAllDroneMaintenance")
    public ResponseEntity getAllDroneMaintenance(@RequestParam(value = "timeStart", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") Date timeStart,
                                              @RequestParam(value = "timeEnd", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")Date timeEnd) {
        return ResponseEntity.ok().body(droneStateService.getAllDroneMaintenance(timeStart, timeEnd));
    }
}
