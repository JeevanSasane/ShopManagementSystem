package com.shopmanagement.user.service.impl;

import com.shopmanagement.masters.entity.*;
import com.shopmanagement.masters.repository.*;
import com.shopmanagement.response.Response;
import com.shopmanagement.security.JwtProvider;
import com.shopmanagement.security.UserAuth;
import com.shopmanagement.user.dto.LoginResponseDto;
import com.shopmanagement.user.dto.UserRequestDto;
import com.shopmanagement.user.entity.UserBranchMapping;
import com.shopmanagement.user.entity.Users;
import com.shopmanagement.user.repository.UserBranchRepo;
import com.shopmanagement.user.repository.UserRepo;
import com.shopmanagement.user.service.UserServices;
import io.swagger.v3.oas.models.responses.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.util.*;

@Service
public class UserServiceImpl implements UserServices {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private CountryRepo countryRepo;

    @Autowired
    private StateRepo stateRepo;

    @Autowired
    private CityRepo cityRepo;

    @Autowired
    private UserBranchRepo userBranchRepo;

    @Autowired
    private JwtProvider jwtProvider;

    @Autowired
    private UserAuth userAuth;

    @Autowired
    private PrefixRepo prefixRepo;

    @Autowired
    private GenderRepo genderRepo;


    private static final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Override
    public Users createUser(UserRequestDto user,String token) {
        try {
            System.out.println("users=" + user);
            Users newUser=new Users();
            Country country= Optional.ofNullable(countryRepo.findByName(user.getCountryName()))
                    .orElseGet(() -> {
                        Country newCountry = new Country();
                        newCountry.setName(user.getCountryName());
                        newCountry.setCreateDateTime(LocalDateTime.now());
                        newCountry.setCreateBy(userRepo.findById(Integer.parseInt(userAuth.getUserId(token).toString())).get());
                        newCountry.setIsActive(true);
                        newCountry.setIsDelete(false);
                        return countryRepo.save(newCountry);
                    });
            newUser.setCountry(country);

            State state= Optional.ofNullable(stateRepo.findByName(user.getStateName()))
                    .orElseGet(() -> {
                        State newState = new State();
                        newState.setName(user.getStateName());
                        newState.setCreateDateTime(LocalDateTime.now());
                        newState.setCreateBy(userRepo.findById(Integer.parseInt(userAuth.getUserId(token).toString())).get());
                        newState.setCountry(country);
                        newState.setIsActive(true);
                        newState.setIsDelete(false);
                        return stateRepo.save(newState);
                    });
            newUser.setState(state);

            City city= Optional.ofNullable(cityRepo.findByName(user.getCityName()))
                    .orElseGet(() -> {
                        City newCity = new City();
                        newCity.setName(user.getStateName());
                        newCity.setCreateDateTime(LocalDateTime.now());
                        newCity.setCreateBy(userRepo.findById(Integer.parseInt(userAuth.getUserId(token).toString())).get());
                        newCity.setIsActive(true);
                        newCity.setIsDelete(false);
                        return cityRepo.save(newCity);
                    });
            newUser.setCity(city);
            newUser.setAge(Period.between(user.getDateOfBirth(), LocalDate.now()).getYears());
            newUser.setUserCreateDateTime(LocalDateTime.now());
            newUser.setPassword(passwordEncoder.encode(user.getPassword()));
            newUser.setPrefix(prefixRepo.findById(user.getPrefixId()).get());
            newUser.setGender(genderRepo.findById(user.getGenderId()).get());

            if (user.getProfile_base64()!=null){

            }

            Users savedUser = userRepo.save(newUser);
            if (user.getBranchIds()!=null) {
                user.getBranchIds().forEach(branckId -> {
                    UserBranchMapping userBranchMapping = new UserBranchMapping();
                    Branch branch = new Branch();
                    branch.setId(branckId);
                    userBranchMapping.setBranch(branch);
                    userBranchMapping.setUser(savedUser);
                    userBranchRepo.save(userBranchMapping);
                });
            }
            return savedUser;
        } catch (Exception e) {
            System.err.println("Error creating user: " + e.getMessage());
            e.printStackTrace();
            throw new RuntimeException("Failed to create user", e);
        }
    }

    @Override
    public List<Users> getUsers() {
        return userRepo.findAll();
    }

    @Override
    public Optional<Users> getUser(Integer userId) {
        return userRepo.findById(userId);
    }

    @Override
    public ResponseEntity<?> loginUser(String userName, String password) {

        System.out.println("in loginUser");
        var response=new Response<>();
        Optional<Users> getUser= Optional.ofNullable(userRepo.findByLoginName(userName));
        if (getUser.isPresent()){
            boolean isPasswordMatch = passwordEncoder.matches(password, getUser.get().getPassword());
            if (isPasswordMatch){

                Map<String, Object> map=userRepo.getUserLoginInfo(getUser.get().getId());
                LoginResponseDto dto=new LoginResponseDto();
                dto.setUserName((String) map.get("userName"));
                dto.setIsActive((Boolean) map.get("isActive"));
                dto.setUId((Integer) map.get("uId"));
                dto.setToken(jwtProvider.generateToken(dto));

                response.setResult(dto);
                response.setStatus(HttpStatus.OK.value());
                response.setMessage("User Login Successfully.");
                return ResponseEntity.ok(response);
            }
            response.setStatus(HttpStatus.BAD_REQUEST.value());
            response.setMessage("Invalid Password !!");
            return ResponseEntity.badRequest().body(response);
        }
        response.setMessage("Invalid Login name !!");
        response.setStatus(HttpStatus.BAD_REQUEST.value());
        return ResponseEntity.badRequest().body(response);
    }

    @Override
    public ResponseEntity<?> getUserBranch(String loginName) {
        var response=new Response<>();

        try {
            response.setResult(userBranchRepo.getUserBranch(loginName));
            response.setMessage("User Branch Retrieved..");
            response.setStatus(HttpStatus.OK.value());
        } catch (Exception e){
            response.setMessage("No Branch Mapped..");
            response.setStatus(HttpStatus.NO_CONTENT.value());
        }

        return ResponseEntity.ok(response);
    }


}
