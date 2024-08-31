package com.shopmanagement.stockmanagement.repository;

import com.shopmanagement.stockmanagement.entity.ItemMaster;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Map;

public interface ItemMasterRepo extends JpaRepository<ItemMaster,Long> {


    ItemMaster findByItemName(String itemName);

    @Query(value = "select * from get_new_item_code()",nativeQuery = true)
    String getItemCode();

    @Query(value = "select * from RetrieveItemStockList(?1,?2,?3)",nativeQuery = true)
    List<Map<String,Object>> getItemList(Long itemId,Integer page,Integer size);

    @Query(value = "select * from RetrieveItemStockListCount(?1)",nativeQuery = true)
    Integer getItemListCount(Long itemId);

    @Query(value = "SELECT * FROM RetrieveItemStockSearch(?1)", nativeQuery = true)
    List<Map<String, Object>> getItemStockSearch( String searchString);



}
