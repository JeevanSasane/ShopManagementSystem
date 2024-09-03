package com.shopmanagement.masters.repository;

import com.shopmanagement.masters.entity.Gender;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Map;

public interface GenderRepo extends JpaRepository<Gender,Integer> {

    @Query(value = "select * from retrievegender()",nativeQuery = true)
    List<Map<String,Object>> getGender();
}
