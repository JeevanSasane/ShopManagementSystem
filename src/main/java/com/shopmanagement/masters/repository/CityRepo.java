package com.shopmanagement.masters.repository;

import com.shopmanagement.masters.entity.City;
import com.shopmanagement.masters.entity.State;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CityRepo extends JpaRepository<City,Integer> {


    City findByName(String name);

    List<City> findByState(State state);
}
