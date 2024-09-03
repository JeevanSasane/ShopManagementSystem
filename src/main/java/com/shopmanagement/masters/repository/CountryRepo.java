package com.shopmanagement.masters.repository;

import com.shopmanagement.masters.entity.Country;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Map;
import java.util.Objects;

public interface CountryRepo extends JpaRepository<Country,Integer> {

    Country findByName(String name);

    @Query(value = "select * from retrievecountry()",nativeQuery = true)
    List<Map<String, Object>> getCountry();

}
