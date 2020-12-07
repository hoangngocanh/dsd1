package com.skyrone.drone.demo.controller;

import com.skyrone.drone.demo.dto.ResponseCase;
import com.skyrone.drone.demo.dto.ServerResponseDto;
import com.skyrone.drone.demo.model.Drone;
import com.skyrone.drone.demo.service.DroneService;
import com.skyrone.drone.demo.service.FlightPathService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import java.util.concurrent.ThreadLocalRandom;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/drone")
@ApiOperation(value = "Thêm sửa xóa drone. Lấy danh sách tình trạng của drone")
public class DroneController {
    @Autowired
    DroneService droneService;

    @Autowired
    FlightPathService flightPathService;

    @GetMapping("/getByCode/{code}")
    public ResponseEntity<Drone> getByCode(@PathVariable("code")String code) {
        return ResponseEntity.ok().body(droneService.getByCode(code));
    }

    @GetMapping("/getById/{id}")
    public ResponseEntity<Drone> getById(@PathVariable("id")String id) {
        return ResponseEntity.ok().body(droneService.getById(id).get());
    }

    @GetMapping("/getAll")
    public ResponseEntity getAllDrone() {
        return ResponseEntity.ok().body(droneService.findAll());
    }

    @GetMapping("/delete/{id}")
    public ResponseEntity deleteDrone(@PathVariable("id")String id) {
        droneService.delete(id);
        return ResponseEntity.ok().body(new ServerResponseDto(ResponseCase.SUCCESS));
    }

    @PostMapping("/save")
    public ResponseEntity save(@RequestBody Drone drone) {
        drone.setUsed(true);
        return ResponseEntity.ok().body(droneService.save(drone));
    }

//    @GetMapping("/makeRandomData")
//    public void makeRandomData() {
//        for (int i = 0; i < 5; i++) {
//            int random = ThreadLocalRandom.current().nextInt(1154, 2399);
//            int code = 1000 + i * 21;
//            Drone drone = new Drone("mavic"+code,"Mavic pro 2020"+random, "Mavic", "white", "1x0.95x0.7 m", 16000, 35, 90, 230, 105000);
////            Drone drone = new Drone(2, "41 24.2028, 2 10.4418", 93.3f, 2300, 15, 3, 100, "phantom max 2020"+random, "phantom", "white", "1.2x0.95x0.62 m", 13000, 30, 70, 220, 95000);
//
//            droneService.save(drone);
//        }
//    }


}
