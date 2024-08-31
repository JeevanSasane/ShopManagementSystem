package com.shopmanagement.dashboard.controller;

import com.shopmanagement.dashboard.dto.DrawerMenuRequestDto;
import com.shopmanagement.dashboard.service.DashboardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/dashboard")
public class DashboardController {


    @Autowired
    private DashboardService dashboardService;

    @PostMapping("/createDrawerMenu")
    public ResponseEntity<?> createDrawerMenu(@RequestBody DrawerMenuRequestDto dto){
        return dashboardService.createDrawerMenu(dto);
    }

    @GetMapping("/getDrawerMenu")
    public ResponseEntity<?> getDrawerMenu(){
        return dashboardService.getDrawerMenu();
    }
}
