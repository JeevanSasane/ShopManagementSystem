package com.shopmanagement.bill.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
public class CreateBillRequestDto {

    private String customerFirstName;

    private String customerLastName;

    private String customerMobile;

    private Double totalBillAmount;

    private Double totalDiscount;

    private List<PurchasedProductRequestDto>  productList;

}
