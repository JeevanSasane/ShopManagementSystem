package com.shopmanagement.bill.repository;

import com.shopmanagement.bill.entity.Bill;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BillRepo extends JpaRepository<Bill,Long> {
}
