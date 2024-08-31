package com.shopmanagement.bill.service.impl;

import com.shopmanagement.bill.dto.CreateBillRequestDto;
import com.shopmanagement.bill.entity.Bill;
import com.shopmanagement.bill.entity.PurchasedProducts;
import com.shopmanagement.bill.repository.BillRepo;
import com.shopmanagement.bill.repository.PurchasedProductRepo;
import com.shopmanagement.bill.service.BillServices;
import com.shopmanagement.response.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class BillServiceImpl implements BillServices {


    @Autowired
    private BillRepo billRepo;

    @Autowired
    private PurchasedProductRepo purchasedProductRepo;

    @Override
    public ResponseEntity<?> createBill(CreateBillRequestDto dto) {

        Bill bill=new Bill();
        bill.setTotalBillAmount(dto.getTotalBillAmount());
        bill.setCustomerFirstName(dto.getCustomerFirstName());
        bill.setCustomerLastName(dto.getCustomerLastName());
        bill.setCustomerMobile(dto.getCustomerMobile());
        bill.setTotalDiscount(dto.getTotalDiscount());

        Bill savedBill = billRepo.save(bill);
        System.out.println("dto=="+dto);
        dto.getProductList().forEach(product->{
            PurchasedProducts purchasedProducts=new PurchasedProducts();
            purchasedProducts.setBill(savedBill);
            purchasedProducts.setItem_batch(product.getItem_batch());
            purchasedProducts.setSallyingPrice(product.getSallyingPrice());
            purchasedProductRepo.save(purchasedProducts);
        });
        System.out.println("000000000000000000000000000");

        var response=new Response<>();
        response.setStatus(HttpStatus.OK.value());
        response.setMessage("Bill Saved Successfully..");
        return ResponseEntity.ok(response);
    }
}
