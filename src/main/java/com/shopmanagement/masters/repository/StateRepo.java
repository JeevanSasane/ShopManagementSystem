package com.shopmanagement.masters.repository;

import com.shopmanagement.masters.entity.Country;
import com.shopmanagement.masters.entity.State;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Map;
import java.util.Objects;

public interface StateRepo extends JpaRepository<State,Integer> {


    State findByName(String name);

    List<State> findByCountry(Country country);

    @Query(value = "select * from retrievestatebycountry(?1)",nativeQuery = true)
    List<Map<String, Object>> getStateByCountry(Long countryId);

}
