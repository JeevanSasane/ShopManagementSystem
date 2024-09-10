package com.shopmanagement.user.controller;

import com.shopmanagement.user.dto.request.LoginRequestDto;
import com.shopmanagement.user.dto.request.UserRequestDto;
import com.shopmanagement.user.entity.Users;
import com.shopmanagement.user.service.UserServices;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserServices userServices;

    @PostMapping("/createUser")
    public ResponseEntity<?> createUser(@RequestBody UserRequestDto users, @RequestHeader(HttpHeaders.AUTHORIZATION) String token){
        System.out.println("in createUser controller");
        return userServices.createUser(users,token);
    }

    @PostMapping("/getUsersList")
    public ResponseEntity<?> getUsers(@RequestBody UserListRequestDto dto){
        return userServices.getUsers(dto);
    }

    @GetMapping("/getUserListSearch/{searchString:.+}")
    public ResponseEntity<?> getUserListSearch(@PathVariable String searchString){
        return userServices.getUserListSearch(searchString);
    }

    @GetMapping("/getUser/{userId}")
    public Optional<Users> getUser(@PathVariable Integer userId){
        return userServices.getUser(userId);
    }

    @PostMapping("/loginUser")
    public ResponseEntity<?> loginUser(@RequestBody LoginRequestDto dto){
        System.out.println("in login user");
        return userServices.loginUser(dto.getLoginName(),dto.getPassword());
    }

    @GetMapping("/getUserBranch/{loginName}")
    public ResponseEntity<?> getUserBranch(@PathVariable String loginName){
        return userServices.getUserBranch(loginName);
    }

    @GetMapping("/checkLoginNameExists/{loginName}")
    public ResponseEntity<?> checkLoginNameExists(@PathVariable String loginName){
        return userServices.checkLoginNameExists(loginName);
    }

    @Getter
    @Setter
    @ToString
    public static class UserListRequestDto {

        private Integer userId;
        private Integer page;
        private Integer size;

    }
}
