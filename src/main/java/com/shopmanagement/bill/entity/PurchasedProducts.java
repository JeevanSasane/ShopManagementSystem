package com.shopmanagement.bill.entity;

import com.shopmanagement.common.BaseEntity;
import com.shopmanagement.stockmanagement.entity.ItemMaster;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity(name = "purchased_products")
public class PurchasedProducts extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "item_id")
    private ItemMaster itemMaster;

    private String item_batch;

    private Double sallyingPrice;

    @ManyToOne
    @JoinColumn(name = "bill_id")
    private Bill bill;


}
