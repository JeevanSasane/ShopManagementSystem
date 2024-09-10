package com.shopmanagement.stockmanagement.entity;
import com.shopmanagement.common.BaseEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "repurchase_items")
public class RepurchaseItems extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String ItemName;

    private Integer quantity;

    private Boolean isPurchased=false;


}
