package com.shopmanagement.masters.repository;

import com.shopmanagement.masters.entity.Country;
import com.shopmanagement.masters.entity.State;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StateRepo extends JpaRepository<State,Integer> {


    State findByName(String name);

    List<State> findByCountry(Country country);

}
