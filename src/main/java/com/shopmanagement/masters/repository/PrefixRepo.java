package com.shopmanagement.masters.repository;

import com.shopmanagement.masters.entity.Prefix;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Map;

public interface PrefixRepo extends JpaRepository<Prefix,Integer> {

    @Query(value = "select * from retrieveprefix()",nativeQuery = true)
    List<Map<String,Object>> getPrefix();
}
