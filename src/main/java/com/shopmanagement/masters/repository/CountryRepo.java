package com.shopmanagement.masters.repository;

import com.shopmanagement.masters.entity.Country;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CountryRepo extends JpaRepository<Country,Integer> {

    Country findByName(String name);

}
