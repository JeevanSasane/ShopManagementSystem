package com.shopmanagement.stockmanagement.dto.request;

import com.shopmanagement.masters.entity.ItemCategory;
import com.shopmanagement.masters.entity.StockCompany;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
public class AddStockRequestDto {

    private String itemName;

//    private String itemCode;

    private Long quantity;

    private String itemBatch;

    private Boolean isBatchApplicable;

    private LocalDateTime batchExpiryDate;

    private Double purchasePrice;

    private Double itemMrp;

    private Double gstPercentage;

    private Double discount;

    private Long companyId;

    private Integer conversion;

//    private ItemCategory itemCategory;

    private String itemCategoryName;

    private String stockCompanyName;

//    private StockCompany stockCompany;


}
