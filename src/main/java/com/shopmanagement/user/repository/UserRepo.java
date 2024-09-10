package com.shopmanagement.user.repository;

import com.shopmanagement.user.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Map;

public interface UserRepo extends JpaRepository<Users,Integer> {

    Users findByLoginName(String loginName);

    @Query(value = "select * from retrieveuserlogindetails(?1) ",nativeQuery = true)
    Map<String,Object> getUserLoginInfo(Integer userId);

    @Query(value = "select * from retreveuserlist(?1,?2,?3)",nativeQuery = true)
    List<Map<String,Object>> getUserList(Integer userId,Integer page,Integer size);

    @Query(value = "select * from retreveuserlistcount(?1)",nativeQuery = true)
    Integer getUserListCount(Integer userId);

    @Query(value = "select * from retrieveuserlistsearch(?1) ",nativeQuery = true)
    Map<String,Object> getUserListSearch(String searchString);

}
