package com.shopmanagement.stockmanagement.entity;

import com.shopmanagement.common.BaseEntity;
import com.shopmanagement.masters.entity.ItemCategory;
import com.shopmanagement.masters.entity.StockCompany;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDateTime;

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

    @NotNull
    private String itemName;

    @Column(nullable = false,unique = true)
    private String itemCode;

    @NotNull
    private LocalDateTime date;

    @NonNull
    private Long availableQuantity;

    @NonNull
    private Boolean isBatchApplicable=false;

    @ManyToOne
    @JoinColumn(name = "stock_company_id")
    private StockCompany stockCompany;

    @ManyToOne
    @JoinColumn(name = "item_category_id")
    private ItemCategory itemCategory;

}
