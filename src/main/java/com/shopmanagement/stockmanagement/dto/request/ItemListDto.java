package com.shopmanagement.stockmanagement.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ItemListDto {
    Long itemId;
    Integer page;
    Integer size;
}
