package com.shopmanagement.masters.service;

import com.shopmanagement.masters.entity.*;
import com.shopmanagement.response.Response;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface MasterService {


    List<Country> getAllCountry();

    List<State> getAllStatesByCountry(Integer countryId);

    List<City>getCityByState(Integer stateId);


    ResponseEntity<?> createBranch(Branch branch,String token);

    ResponseEntity<?> getBranches();


    ResponseEntity<?> createItemCategory(ItemCategory itemCategory,String token);

    ResponseEntity<?> getItemCategory();


    ResponseEntity<?> createItemCompany(StockCompany stockCompany,String token);

    ResponseEntity<?> getItemCompany(Long itemId);


    ResponseEntity<?> createPrefix(Prefix prefix,String token);

    ResponseEntity<?> createGender(Gender gender,String token);

}
