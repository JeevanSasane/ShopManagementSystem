package com.shopmanagement.stockmanagement.entity;

import com.shopmanagement.common.BaseEntity;
import com.shopmanagement.masters.entity.ItemCategory;
import com.shopmanagement.masters.entity.ItemCompany;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Table(name = "mt_item_master")
public class ItemMaster extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true,nullable = false)
    private String itemName;

    @Column(nullable = false,unique = true)
    private String itemCode;

    @NonNull
    private Long availableQuantity;

    @NonNull
    private Boolean isBatchApplicable=false;

    @ManyToOne
    @JoinColumn(name = "stock_company_id")
    private ItemCompany stockCompany;

    @ManyToOne
    @JoinColumn(name = "item_category_id")
    private ItemCategory itemCategory;

}
