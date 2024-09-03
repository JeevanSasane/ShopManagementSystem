package com.shopmanagement.user.service.impl;

import com.shopmanagement.masters.entity.Branch;
import com.shopmanagement.masters.entity.City;
import com.shopmanagement.masters.entity.Country;
import com.shopmanagement.masters.entity.State;
import com.shopmanagement.masters.repository.*;
import com.shopmanagement.response.Response;
import com.shopmanagement.security.JwtProvider;
import com.shopmanagement.security.UserAuth;
import com.shopmanagement.storage.Constants;
import com.shopmanagement.storage.StorageServices;
import com.shopmanagement.user.dto.LoginResponseDto;
import com.shopmanagement.user.dto.UserListRequestDto;
import com.shopmanagement.user.dto.UserRequestDto;
import com.shopmanagement.user.entity.UserBranchMapping;
import com.shopmanagement.user.entity.UserBranchMappingId;
import com.shopmanagement.user.entity.Users;
import com.shopmanagement.user.repository.UserBranchRepo;
import com.shopmanagement.user.repository.UserRepo;
import com.shopmanagement.user.service.UserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserServices {

    private static final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
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
    @Autowired
    private StorageServices storageServices;

    @Override
    public ResponseEntity<?> createUser(UserRequestDto user, String token) {
        var response = new Response<>();
        try {
            Users newUser = new Users();

            Country country = Optional.ofNullable(countryRepo.findByName(user.getCountryName()))
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

            State state = Optional.ofNullable(stateRepo.findByName(user.getStateName()))
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

            City city = Optional.ofNullable(cityRepo.findByName(user.getCityName()))
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
            newUser.setLandmark(user.getLandmark());
            newUser.setFirstName(user.getFirstName());
            newUser.setDateOfBirth(user.getDateOfBirth());
            newUser.setLastName(user.getLastName());
            newUser.setLoginName(user.getLoginName());
            newUser.setMobileNo(user.getMobileNo());
            newUser.setAlternateMobileNo(user.getAlternateMobileNo());
            newUser.setCreateDateTime(LocalDateTime.now());
            newUser.setCreateBy(userRepo.findById(Integer.parseInt(userAuth.getUserId(token).toString())).get());
            newUser.setLastUpdatedDateTime(LocalDateTime.now());
            newUser.setUpdatedBy(userRepo.findById(Integer.parseInt(userAuth.getUserId(token).toString())).get());
            newUser.setAge(Period.between(user.getDateOfBirth(), LocalDate.now()).getYears());
            newUser.setPassword(passwordEncoder.encode(user.getPassword()));
            newUser.setPrefix(prefixRepo.findById(user.getPrefixId()).get());
            newUser.setGender(genderRepo.findById(user.getGenderId()).get());

            if (user.getProfile_base64() != null) {
                String updateName = Constants.USER_PROFILE + "/" + user.getProfile_name();
                try {
                    storageServices.storeBaseImage(user.getProfile_base64(), user.getProfile_name(), Constants.USER_PROFILE);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                newUser.setProfile_path(updateName);
            }

            newUser.setProfile_path(user.getProfile_path());
            Users savedUser = userRepo.save(newUser);
            if (user.getBranchIds() != null) {
                user.getBranchIds().forEach(branchId -> {
                    UserBranchMapping userBranchMapping = new UserBranchMapping();
                    UserBranchMappingId id = new UserBranchMappingId(savedUser.getId(), branchId);
                    userBranchMapping.setId(id);

                    Branch branch = new Branch();
                    branch.setId(branchId);
                    userBranchMapping.setBranch(branch);

                    userBranchMapping.setUser(savedUser);

                    userBranchRepo.save(userBranchMapping);
                });
            }
            response.setMessage("User Created Successfully..");
            response.setStatus(HttpStatus.OK.value());
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            response.setMessage("Failed to Create User !!");
            response.setStatus(HttpStatus.BAD_REQUEST.value());
            return ResponseEntity.badRequest().body(response);
        }
    }

    @Override
    public ResponseEntity<?> getUsers(UserListRequestDto dto) {
        var response=new Response<>();
        response.setResult(userRepo.getUserList(dto.getUserId(),dto.getPage(),dto.getSize()));
        response.setStatus(HttpStatus.OK.value());
        response.setMessage("User List Retrieved Successfully..");
        return ResponseEntity.ok(response);
    }

    @Override
    public ResponseEntity<?> getUserListSearch(String searchString) {

        return null;
    }


    @Override
    public Optional<Users> getUser(Integer userId) {
        return userRepo.findById(userId);
    }

    @Override
    public ResponseEntity<?> loginUser(String userName, String password) {

        System.out.println("in loginUser");
        var response = new Response<>();
        Optional<Users> getUser = Optional.ofNullable(userRepo.findByLoginName(userName));
        if (getUser.isPresent()) {
            boolean isPasswordMatch = passwordEncoder.matches(password, getUser.get().getPassword());
            if (isPasswordMatch) {

                Map<String, Object> map = userRepo.getUserLoginInfo(getUser.get().getId());
                LoginResponseDto dto = new LoginResponseDto();
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
        var response = new Response<>();

        try {
            response.setResult(userBranchRepo.getUserBranch(loginName));
            response.setMessage("User Branch Retrieved..");
            response.setStatus(HttpStatus.OK.value());
        } catch (Exception e) {
            response.setMessage("No Branch Mapped..");
            response.setStatus(HttpStatus.NO_CONTENT.value());
        }
        return ResponseEntity.ok(response);
    }

    @Override
    public ResponseEntity<?> checkLoginNameExists(String loginName) {
        var response = new Response<>();
        Optional<Users> getUser = Optional.ofNullable(userRepo.findByLoginName(loginName));
        if (getUser.isPresent()) {
            response.setMessage("Login name already Exists !!");
            response.setStatus(HttpStatus.ALREADY_REPORTED.value());
            return ResponseEntity.badRequest().body(response);
        }
        response.setStatus(HttpStatus.OK.value());
        return ResponseEntity.ok(response);
    }

}
