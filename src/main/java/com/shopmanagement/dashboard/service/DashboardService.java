package com.shopmanagement.dashboard.service;

import com.shopmanagement.dashboard.dto.DrawerMenuRequestDto;
import org.springframework.http.ResponseEntity;

public interface DashboardService {

    ResponseEntity<?> createDrawerMenu(DrawerMenuRequestDto dto);

    ResponseEntity<?> getDrawerMenu();

}
