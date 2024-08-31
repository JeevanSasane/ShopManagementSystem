package com.shopmanagement.masters.repository;

import com.shopmanagement.masters.entity.ItemCategory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemCategoryRepo extends JpaRepository<ItemCategory,Long> {

    ItemCategory findByCategory(String category);
}
