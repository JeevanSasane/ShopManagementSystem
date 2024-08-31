package com.shopmanagement.bill.controller;

import com.shopmanagement.bill.dto.CreateBillRequestDto;
import com.shopmanagement.bill.service.BillServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/bill")
public class BillController {

    @Autowired
    private BillServices billServices;

    @PostMapping("/createBill")
    public ResponseEntity<?> createBill(@RequestBody CreateBillRequestDto dto){
        return billServices.createBill(dto);
    }
}
