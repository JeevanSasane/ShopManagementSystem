package com.shopmanagement.user.repository;

import com.shopmanagement.user.dto.LoginResponseDto;
import com.shopmanagement.user.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Map;

public interface UserRepo extends JpaRepository<Users,Integer> {

    Users findByLoginName(String loginName);

    @Query(value = "select * from retrieveuserlogindetails(?1) ",nativeQuery = true)
    Map<String,Object> getUserLoginInfo(Integer userId);


}
