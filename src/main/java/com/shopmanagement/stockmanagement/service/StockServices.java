package com.shopmanagement.stockmanagement.service;


import com.shopmanagement.stockmanagement.dto.request.AddStockRequestDto;
import com.shopmanagement.stockmanagement.dto.request.ItemListDto;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface StockServices {

    ResponseEntity<?> addStock(AddStockRequestDto dto,String token);

    ResponseEntity<?> addStockList(List<AddStockRequestDto> dtoList,String token);

    ResponseEntity<?> getAllItemStockList(ItemListDto itemListDto);

    ResponseEntity<?> getItemStock(String searchString);

    ResponseEntity<?> getBatchesFromItemId(Long itemId);

}
