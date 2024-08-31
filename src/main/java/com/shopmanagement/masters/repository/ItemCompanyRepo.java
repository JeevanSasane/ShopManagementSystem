package com.shopmanagement.masters.repository;

import com.shopmanagement.masters.entity.ItemCompany;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemCompanyRepo extends JpaRepository<ItemCompany,Long> {

    ItemCompany findByCompanyName(String companyName);
}
