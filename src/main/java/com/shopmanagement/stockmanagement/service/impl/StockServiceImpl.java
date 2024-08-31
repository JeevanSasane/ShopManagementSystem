package com.shopmanagement.stockmanagement.service.impl;

import com.shopmanagement.masters.entity.Country;
import com.shopmanagement.masters.entity.ItemCategory;
import com.shopmanagement.masters.entity.StockCompany;
import com.shopmanagement.masters.repository.ItemCategoryRepo;
import com.shopmanagement.masters.repository.StockCompanyRepo;
import com.shopmanagement.response.Response;
import com.shopmanagement.security.UserAuth;
import com.shopmanagement.stockmanagement.dto.request.AddStockRequestDto;
import com.shopmanagement.stockmanagement.dto.request.ItemListDto;
import com.shopmanagement.stockmanagement.entity.ItemMaster;
import com.shopmanagement.stockmanagement.entity.ItemStock;
import com.shopmanagement.stockmanagement.repository.ItemMasterRepo;
import com.shopmanagement.stockmanagement.repository.ItemStockRepo;
import com.shopmanagement.stockmanagement.service.StockServices;
import com.shopmanagement.user.entity.Users;
import com.shopmanagement.user.repository.UserRepo;
import io.swagger.v3.oas.models.responses.ApiResponse;
import jakarta.transaction.Transactional;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class StockServiceImpl implements StockServices {

    @Autowired
    private ItemMasterRepo itemMasterRepo;

    @Autowired
    private ItemStockRepo itemStockRepo;

    @Autowired
    private ItemCategoryRepo itemCategoryRepo;

    @Autowired
    private StockCompanyRepo stockCompanyRepo;

    @Autowired
    private UserAuth userAuth;

    @Autowired
    private UserRepo userRepo;

    @Override
    @Transactional
    public ResponseEntity<?> addStock(AddStockRequestDto dto,String token) {

        System.out.println("dto=="+dto);
        ItemMaster itemMaster=new ItemMaster();
        Optional<Users> user=userRepo.findById(Integer.parseInt(userAuth.getUserId(token).toString()));
        System.out.println("user=="+user);
        Optional<ItemMaster> itemOptional = Optional.ofNullable(itemMasterRepo.findByItemName(dto.getItemName()));
        if (itemOptional.isPresent()){
            itemMaster.setId(itemOptional.get().getId());
            itemMaster.setItemCode(itemOptional.get().getItemCode());
            itemMaster.setAvailableQuantity(itemOptional.get().getAvailableQuantity()+dto.getQuantity());
            itemMaster.setIsBatchApplicable(itemOptional.get().getIsBatchApplicable());
            itemMaster.setCreateDateTime(itemOptional.get().getCreateDateTime());
            itemMaster.setCreateBy(itemOptional.get().getCreateBy());
            itemMaster.setUpdatedBy(user.get());
            itemMaster.setLastUpdatedDateTime(LocalDateTime.now());
            itemMaster.setItemCategory(itemOptional.get().getItemCategory());
            itemMaster.setStockCompany(itemOptional.get().getStockCompany());
        }else {
            itemMaster.setItemCode(itemMasterRepo.getItemCode());
            itemMaster.setAvailableQuantity(dto.getQuantity());
            itemMaster.setIsBatchApplicable(dto.getIsBatchApplicable());
            itemMaster.setCreateBy(user.get());
            itemMaster.setCreateDateTime(LocalDateTime.now());
            itemMaster.setUpdatedBy(user.get());
            itemMaster.setLastUpdatedDateTime(LocalDateTime.now());

            ItemCategory itemCategory = Optional.ofNullable(itemCategoryRepo.findByCategory(dto.getItemCategoryName()))
                    .orElseGet(() -> {
                        ItemCategory newItemCategory = new ItemCategory();
                        newItemCategory.setCategory(dto.getItemCategoryName());
                        newItemCategory.setCreateDateTime(LocalDateTime.now());
                        newItemCategory.setCreateBy(user.get());
                        newItemCategory.setIsActive(true);
                        newItemCategory.setIsDelete(false);
                        return itemCategoryRepo.save(newItemCategory);
                    });
            itemMaster.setItemCategory(itemCategory);
            StockCompany stockCompany = Optional.ofNullable(stockCompanyRepo.findByCompanyName(dto.getStockCompanyName()))
                    .orElseGet(() -> {
                        StockCompany newStockCompany= new StockCompany();
                        newStockCompany.setCompanyName(dto.getStockCompanyName());
                        newStockCompany.setCreateDateTime(LocalDateTime.now());
                        newStockCompany.setCreateBy(user.get());
                        newStockCompany.setIsActive(true);
                        newStockCompany.setIsDelete(false);
                        return stockCompanyRepo.save(newStockCompany);
                    });
            itemMaster.setStockCompany(stockCompany);
        }
        itemMaster.setItemName(dto.getItemName());
        itemMaster.setDate(LocalDateTime.now());
        ItemMaster savedItem = itemMasterRepo.save(itemMaster);
        ItemStock itemStock=new ItemStock();
        Optional<ItemStock> itemStockOptional=Optional.ofNullable(itemStockRepo.findByItemMasterAndItemBatch(savedItem, dto.getItemBatch()));
        if (itemStockOptional.isPresent()){
            itemStock.setId(itemStockOptional.get().getId());
            itemStock.setItemMaster(savedItem);
            itemStock.setItemQuantity(itemStockOptional.get().getItemQuantity()+dto.getQuantity());
            itemStock.setBatchExpiryDate(itemStockOptional.get().getBatchExpiryDate());
            itemStock.setCreateDateTime(itemStockOptional.get().getCreateDateTime());
            itemStock.setCreateBy(itemStockOptional.get().getCreateBy());
            itemStock.setUpdatedBy(user.get());
            itemStock.setLastUpdatedDateTime(LocalDateTime.now());
            itemStock.setItemBatch(itemStockOptional.get().getItemBatch());
            itemStock.setLandedRate(itemStockOptional.get().getLandedRate());
            itemStock.setGstPercentage(itemStockOptional.get().getGstPercentage());
        }else {
            itemStock.setItemMaster(savedItem);
            itemStock.setItemBatch(dto.getItemBatch());
            itemStock.setBatchExpiryDate(dto.getBatchExpiryDate());
            itemStock.setCreateDateTime(LocalDateTime.now());
            itemStock.setCreateBy(user.get());
            itemStock.setUpdatedBy(user.get());
            itemStock.setLastUpdatedDateTime(LocalDateTime.now());
            itemStock.setItemQuantity(dto.getQuantity());
            itemStock.setLandedRate(((dto.getPurchasePrice()*dto.getGstPercentage())-dto.getDiscount())+itemStock.getPurchasePrice());
            itemStock.setGstPercentage(dto.getGstPercentage());

        }
        itemStock.setPurchasePrice(dto.getPurchasePrice());
        itemStock.setItemMrp(dto.getItemMrp());
        itemStockRepo.save(itemStock);
        var response=new Response<>();
        response.setMessage("Item Added Successfully.");
        response.setStatus(HttpStatus.OK.value());
        return ResponseEntity.ok(response);
    }

    @Override
    @Transactional
    public ResponseEntity<?>  addStockList(List<AddStockRequestDto> dtoList,String token) {
        dtoList.stream().forEach(addStockRequestDto -> addStock(addStockRequestDto, token));
        var response=new Response<>();
        response.setStatus(HttpStatus.OK.value());
        response.setMessage("Stock Added Successfully..");
        return ResponseEntity.ok(response);
    }

    @Override
    public ResponseEntity<?> getAllItemStockList(ItemListDto itemListDto) {
        var response=new Response<>();
        response.setResult(itemMasterRepo.getItemList(itemListDto.getItemId(),itemListDto.getPage(),itemListDto.getSize()));
        response.setCount(itemMasterRepo.getItemListCount(itemListDto.getItemId()));
        response.setStatus(HttpStatus.OK.value());
        response.setMessage("Item List Retrieved Successfully..");
        return ResponseEntity.ok(response);
    }


    @Override
    public ResponseEntity<?> getItemStock(String searchString) {
        var response=new Response<>();
        response.setResult(itemMasterRepo.getItemStockSearch(searchString));
        response.setStatus(HttpStatus.OK.value());
        response.setMessage("Item Searched Successfully..");
        return ResponseEntity.ok(response);
    }

    @Override
    public ResponseEntity<?> getBatchesFromItemId(Long itemId) {
        var response=new Response<>();
        response.setResult(itemStockRepo.getItemBatches(itemId));
        response.setStatus(HttpStatus.OK.value());
        response.setMessage("Item Batched Retrieved Successfully.");
        return ResponseEntity.ok(response);
    }
}
