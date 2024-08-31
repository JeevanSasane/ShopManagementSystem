package com.shopmanagement.bill.repository;

import com.shopmanagement.bill.entity.PurchasedProducts;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PurchasedProductRepo extends JpaRepository<PurchasedProducts,Long> {
}
