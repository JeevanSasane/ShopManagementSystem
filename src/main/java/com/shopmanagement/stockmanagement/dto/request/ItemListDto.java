package com.shopmanagement.stockmanagement.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ItemListDto {
    private Long itemId;
    private Integer page;
    private Integer size;
}
