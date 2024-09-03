package com.shopmanagement.masters.repository;

import com.shopmanagement.masters.entity.City;
import com.shopmanagement.masters.entity.State;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Map;

public interface CityRepo extends JpaRepository<City,Integer> {


    City findByName(String name);

    List<City> findByState(State state);

    @Query(value = "select * from retrievecitybystate(?1)",nativeQuery = true)
    List<Map<String,Object>> getCityByState(Long stateId);
}
