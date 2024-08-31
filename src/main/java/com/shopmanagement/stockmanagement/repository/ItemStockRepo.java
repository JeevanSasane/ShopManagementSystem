package com.shopmanagement.stockmanagement.repository;

import com.shopmanagement.stockmanagement.entity.ItemMaster;
import com.shopmanagement.stockmanagement.entity.ItemStock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Map;

public interface ItemStockRepo extends JpaRepository<ItemStock,Long> {

    ItemStock findByItemMasterAndItemBatch(ItemMaster itemMaster, String itemBatch);

    @Query(value = "select * from retieveitembatchedbyitemid(?1)",nativeQuery = true)
    List<Map<String,Object>> getItemBatches(Long itemId);

}
