package com.shopmanagement.masters.service.impl;

import com.shopmanagement.masters.entity.*;
import com.shopmanagement.masters.repository.*;
import com.shopmanagement.masters.service.MasterService;
import com.shopmanagement.response.Response;
import com.shopmanagement.security.UserAuth;
import com.shopmanagement.user.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class MasterServiceImpl implements MasterService {


    @Autowired
    private CountryRepo countryRepo;

    @Autowired
    private CityRepo cityRepo;

    @Autowired
    private StateRepo stateRepo;

    @Autowired
    private BranchRepo branchRepo;

    @Autowired
    private ItemCategoryRepo itemCategoryRepo;

    @Autowired
    private StockCompanyRepo stockCompanyRepo;

    @Autowired
    private UserAuth userAuth;

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private PrefixRepo prefixRepo;

    @Autowired
    private GenderRepo genderRepo;

    @Override
    public List<Country> getAllCountry() {
        return countryRepo.findAll();
    }

    @Override
    public List<State> getAllStatesByCountry(Integer countryId) {
        Country country = new Country();
        country.setId(countryId);
        return stateRepo.findByCountry(country);
    }

    @Override
    public List<City> getCityByState(Integer stateId) {
        State state = new State();
        state.setId(stateId);
        return cityRepo.findByState(state);
    }

    @Override
    public ResponseEntity<?> createBranch(Branch branch,String token) {
        var response = new Response<>();
        branch.setCreateBy(userRepo.findById(Integer.parseInt(userAuth.getUserId(token).toString())).get());
        branch.setCreateDateTime(LocalDateTime.now());
        branch.setIsActive(true);
        branch.setIsDelete(false);
        branchRepo.save(branch);
        response.setStatus(HttpStatus.OK.value());
        response.setMessage("Branch Created Successfully..");
        return ResponseEntity.ok(response);
    }

    @Override
    public ResponseEntity<?> getBranches() {
        var response = new Response<>();
        response.setResult(branchRepo.getBranch());
        response.setStatus(HttpStatus.OK.value());
        response.setMessage("Branches Retrieved Successfully..");
        return ResponseEntity.ok(response);
    }

    @Override
    public ResponseEntity<?> createItemCategory(ItemCategory itemCategory, String token) {
        var response = new Response<>();
        ItemCategory newItemCategory = new ItemCategory();
        newItemCategory.setCategory(itemCategory.getCategory());
        newItemCategory.setCreateDateTime(LocalDateTime.now());
        newItemCategory.setCreateBy(userRepo.findById(Integer.parseInt(userAuth.getUserId(token).toString())).get());
        newItemCategory.setIsActive(true);
        newItemCategory.setIsDelete(false);
        itemCategoryRepo.save(newItemCategory);
        response.setStatus(HttpStatus.OK.value());
        response.setMessage("Item Category Created Successfully..");
        return ResponseEntity.ok(response);
    }

    @Override
    public ResponseEntity<?> getItemCategory() {
        var response=new Response<>();
        response.setResult(itemCategoryRepo.findAll());
        response.setStatus(HttpStatus.OK.value());
        response.setMessage("Item Categories Retrieved Successfully..");
        return ResponseEntity.ok(response);
    }

    @Override
    public ResponseEntity<?> createItemCompany(StockCompany stockCompany, String token) {
        var response = new Response<>();
        StockCompany newStockCompany = new StockCompany();
        newStockCompany.setCompanyName(stockCompany.getCompanyName());
        newStockCompany.setCreateDateTime(LocalDateTime.now());
        newStockCompany.setCreateBy(userRepo.findById(Integer.parseInt(userAuth.getUserId(token).toString())).get());
        newStockCompany.setIsActive(true);
        newStockCompany.setIsDelete(false);
        stockCompanyRepo.save(newStockCompany);
        response.setStatus(HttpStatus.OK.value());
        response.setMessage("Item Company created Successfully..");
        return ResponseEntity.ok(response);
    }

    @Override
    public ResponseEntity<?> getItemCompany(Long itemId) {
        var response=new Response<>();
        response.setResult(stockCompanyRepo.findAll());
        response.setStatus(HttpStatus.OK.value());
        response.setMessage("Item Companies Retrieved Successfully..");
        return ResponseEntity.ok(response);
    }


    @Override
    public ResponseEntity<?> createPrefix(Prefix prefix, String token) {
        var response=new Response<>();
        prefix.setCreateDateTime(LocalDateTime.now());
        prefix.setCreateBy(userRepo.findById(Integer.parseInt(userAuth.getUserId(token).toString())).get());
        prefix.setIsActive(true);
        prefix.setIsDelete(false);
        prefixRepo.save(prefix);
        response.setStatus(HttpStatus.OK.value());
        response.setMessage("Prefix Retrieved Successfully..");
        return ResponseEntity.ok(response);
    }

    @Override
    public ResponseEntity<?> createGender(Gender gender, String token) {
        var response=new Response<>();
        gender.setCreateDateTime(LocalDateTime.now());
        gender.setCreateBy(userRepo.findById(Integer.parseInt(userAuth.getUserId(token).toString())).get());
        gender.setIsActive(true);
        gender.setIsDelete(false);
        genderRepo.save(gender);
        response.setStatus(HttpStatus.OK.value());
        response.setMessage("Gender Retrieved Successfully..");
        return ResponseEntity.ok(response);
    }

}
