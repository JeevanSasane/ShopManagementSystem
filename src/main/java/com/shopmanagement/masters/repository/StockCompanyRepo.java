package com.shopmanagement.masters.repository;

import com.shopmanagement.masters.entity.StockCompany;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StockCompanyRepo extends JpaRepository<StockCompany,Long> {

    StockCompany findByCompanyName(String companyName);
}
