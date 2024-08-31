package com.shopmanagement.bill.entity;

import com.shopmanagement.common.BaseEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity(name = "bill")
public class Bill extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String customerFirstName;

    private String customerLastName;

    private String customerMobile;

    private Double totalBillAmount;

    private Double totalDiscount;

    @OneToMany(mappedBy = "bill", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<PurchasedProducts> purchasedProducts;


}


