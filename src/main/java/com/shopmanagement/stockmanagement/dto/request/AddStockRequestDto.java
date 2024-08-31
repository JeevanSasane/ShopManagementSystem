package com.shopmanagement.stockmanagement.dto.request;

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

    private Integer conversion;

    private String itemCategoryName;

    private String stockCompanyName;

}
