package com.shopmanagement.bill.dto;

import jakarta.persistence.Column;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

@Getter
@Setter
public class PurchasedProductRequestDto {

    @Column(nullable = false)
    private Long itemId;

    private String item_batch;

    @Column(nullable = false)
    private Double sallyingPrice;

}
