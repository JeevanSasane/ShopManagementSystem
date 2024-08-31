package com.shopmanagement.masters.repository;

import com.shopmanagement.masters.entity.Gender;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GenderRepo extends JpaRepository<Gender,Integer> {
}
