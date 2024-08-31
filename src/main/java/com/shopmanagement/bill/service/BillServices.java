package com.shopmanagement.bill.service;

import com.shopmanagement.bill.dto.CreateBillRequestDto;
import org.springframework.http.ResponseEntity;

public interface BillServices {

    ResponseEntity<?> createBill(CreateBillRequestDto dto);
}
