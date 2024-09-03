package com.shopmanagement.masters.controller;

import com.shopmanagement.masters.entity.*;
import com.shopmanagement.masters.service.MasterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/master")
public class MasterController {

    @Autowired
    private MasterService masterService;

    @GetMapping("/getAllCountry")
    public List<Country> getAllCountry(){
        return masterService.getAllCountry();
    }

    @GetMapping("/getCountry")
    public ResponseEntity<?>  getCountry(){
        return masterService.getCountry();
    }

    @GetMapping("/getAllStates/{countryId}")
    public List<State> getAllStates(@PathVariable Integer countryId){
        return masterService.getAllStatesByCountry(countryId);
    }

    @GetMapping("/getState/{countryId}")
    public ResponseEntity<?>  getState(@PathVariable Long countryId){
        return masterService.getState(countryId);
    }

    @GetMapping("/getCity/{stateId}")
    public ResponseEntity<?>  getCity(@PathVariable Long stateId){
        return masterService.getCity(stateId);
    }


    @GetMapping("/getAllCity/{cityId}")
    public List<City> getAllCity(@PathVariable Integer cityId){
        return masterService.getCityByState(cityId);
    }


    @PostMapping("/createBranch")
    public ResponseEntity<?> createBranch(@RequestBody Branch branch,
                                          @RequestHeader(HttpHeaders.AUTHORIZATION) String token){
        return masterService.createBranch(branch,token);
    }

    @GetMapping("/getBranches")
    public ResponseEntity<?>  getBranches(){
        return masterService.getBranches();
    }


    @PostMapping("/createItemCategory")
    public ResponseEntity<?> createItemCategory(@RequestBody ItemCategory itemCategory,
                                                @RequestHeader(HttpHeaders.AUTHORIZATION) String token){
        return masterService.createItemCategory(itemCategory,token);
    }

    @GetMapping("/getItemCategory")
    public ResponseEntity<?>  getItemCategory(){
        return masterService.getItemCategory();
    }


    @PostMapping("/createItemCompany")
    public ResponseEntity<?> createItemCompany(@RequestBody ItemCompany stockCompany,
                                                @RequestHeader(HttpHeaders.AUTHORIZATION) String token){
        return masterService.createItemCompany(stockCompany,token);
    }

    @GetMapping("/getItemCompany/{itemId}")
    public ResponseEntity<?>  getItemCompany(@PathVariable Long itemId){
        return masterService.getItemCompany(itemId);
    }

    @PostMapping("/createPrefix")
    public ResponseEntity<?> createPrefix(@RequestBody Prefix prefix,
                                               @RequestHeader(HttpHeaders.AUTHORIZATION) String token){
        return masterService.createPrefix(prefix,token);
    }

    @GetMapping("/getPrefix")
    public ResponseEntity<?>  getPrefix(){
        return masterService.getPrefix();
    }

    @PostMapping("/createGender")
    public ResponseEntity<?> createGender(@RequestBody Gender gender,
                                               @RequestHeader(HttpHeaders.AUTHORIZATION) String token){
        return masterService.createGender(gender,token);
    }

    @GetMapping("/getGender")
    public ResponseEntity<?>  getGender(){
        return masterService.getGender();
    }

}
