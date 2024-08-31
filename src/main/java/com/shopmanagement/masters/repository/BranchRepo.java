package com.shopmanagement.masters.repository;

import com.shopmanagement.masters.entity.Branch;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Map;

public interface BranchRepo extends JpaRepository<Branch,Long> {

    @Query(value = "select * from RetrieveBranch()",nativeQuery = true)
    List<Map<String,Object>> getBranch();
}
