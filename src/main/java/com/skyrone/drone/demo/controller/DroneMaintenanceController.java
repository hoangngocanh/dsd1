package com.skyrone.drone.demo.controller;

import com.skyrone.drone.demo.dto.FormStateAll;
import com.skyrone.drone.demo.dto.ResponseCase;
import com.skyrone.drone.demo.dto.ServerResponseDto;
import com.skyrone.drone.demo.model.Drone;
import com.skyrone.drone.demo.model.DroneMaintenance;
import com.skyrone.drone.demo.service.DroneMaintenanceService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/droneMaintenance")
public class DroneMaintenanceController {
    @Autowired
    DroneMaintenanceService droneMaintenanceService;

    @GetMapping("/delete/{id}")
    public ResponseEntity deleteDrone(@PathVariable("id")String id) {
        droneMaintenanceService.delete(id);
        return ResponseEntity.ok().body(new ServerResponseDto(ResponseCase.SUCCESS));
    }

    @ApiOperation("Tạo lịc bảo trì hoặc sạc drone, id là id drone isMaintance == true để đặt bảo trì , isMaintance == false để đặt lịch sạc,")
    @PostMapping("/save")
    public ResponseEntity<ServerResponseDto> save(@RequestBody DroneMaintenance droneMaintenance) {
        return ResponseEntity.ok().body(droneMaintenanceService.save(droneMaintenance));
    }

    @ApiOperation("Chuyển trạng thái sẵn sàng cho drone")
    @GetMapping("/getBackDrone/{id}")
    public ResponseEntity<ServerResponseDto> getBackDrone(@PathVariable("id")String id) {
        return ResponseEntity.ok().body(droneMaintenanceService.getBackDrone(id));
    }

    @ApiOperation("Đặt lịch sạc, bảo trì cho nhiều drone")
    @PostMapping("/setStateAll")
    public ResponseEntity<ServerResponseDto> setStateAll(@RequestBody FormStateAll formStateAll) {
        return ResponseEntity.ok().body(droneMaintenanceService.setStateAll(formStateAll));
    }

    @ApiOperation("Chuyển trạng thái sẵn sàng cho nhiều drone")
    @PostMapping("/getBackDroneAll")
    public ResponseEntity<ServerResponseDto> getBackDroneAll(@RequestBody FormStateAll formStateAll) {
        return ResponseEntity.ok().body(droneMaintenanceService.getBackDroneAll(formStateAll));
    }
}
