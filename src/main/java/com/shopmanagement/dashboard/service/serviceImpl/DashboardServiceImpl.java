package com.shopmanagement.dashboard.service.serviceImpl;

import com.shopmanagement.dashboard.dto.DrawerMenuRequestDto;
import com.shopmanagement.dashboard.repository.SystemMenuRepo;
import com.shopmanagement.dashboard.service.DashboardService;
import com.shopmanagement.response.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class DashboardServiceImpl implements DashboardService {

    @Autowired
    private SystemMenuRepo systemMenuRepo;

    @Override
    public ResponseEntity<?> createDrawerMenu(DrawerMenuRequestDto dto) {
        return null;
    }

    @Override
    public ResponseEntity<?> getDrawerMenu() {
        var response=new Response<>();
        response.setResult(systemMenuRepo.getSystemMenu());
        response.setMessage("System Menu Retrieved Successfully");
        response.setStatus(HttpStatus.OK.value());
        return ResponseEntity.ok(response);
    }
}
