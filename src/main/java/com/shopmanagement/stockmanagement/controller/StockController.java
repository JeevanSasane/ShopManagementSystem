package com.shopmanagement.stockmanagement.controller;

import com.shopmanagement.stockmanagement.dto.request.AddStockRequestDto;
import com.shopmanagement.stockmanagement.dto.request.ItemListDto;
import com.shopmanagement.stockmanagement.service.StockServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/stock")
public class StockController {

    @Autowired
    private StockServices stockServices;

    @PostMapping("/addStock")
    public ResponseEntity<?> addStock(@RequestBody AddStockRequestDto dto,
                                      @RequestHeader(HttpHeaders.AUTHORIZATION) String token){
        return stockServices.addStock(dto,token);
    }

    @PostMapping("/addStockList")
    public ResponseEntity<?> addStockList(@RequestBody List<AddStockRequestDto> dto,
                                          @RequestHeader(HttpHeaders.AUTHORIZATION) String token){
        return stockServices.addStockList(dto,token);
    }

    @PostMapping("/getAllItemStockList")
    public ResponseEntity<?> getAllItemStockList(@RequestBody ItemListDto dto){
        return stockServices.getAllItemStockList(dto);
    }

    @GetMapping("/getItemStock/{searchString:.+}")
    public ResponseEntity<?> getItemStock(@PathVariable String searchString){
        return stockServices.getItemStock(searchString);
    }

    @GetMapping("/getBatchesFromItemId/{itemId}")
    public ResponseEntity<?> getBatchesFromItemId(@PathVariable Long itemId){
        return stockServices.getBatchesFromItemId(itemId);
    }
}
