package com.shopmanagement.dashboard.repository;

import com.shopmanagement.dashboard.entity.DrawerMenus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Map;

public interface SystemMenuRepo extends JpaRepository<DrawerMenus,Long> {

    @Query(value = "select * from retrievesystemmenus()",nativeQuery = true)
    List<Map<String,Object>> getSystemMenu();
}
