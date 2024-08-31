package com.shopmanagement.user.repository;

import com.shopmanagement.user.entity.UserBranchMapping;
import com.shopmanagement.user.entity.UserBranchMappingId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Map;

public interface UserBranchRepo extends JpaRepository<UserBranchMapping,UserBranchMappingId> {

    @Query(value = "select * from RetrieveUserBranch(?1)",nativeQuery = true)
    List<Map<String,Object>> getUserBranch(String loginName);
}
