package com.shopmanagement.user.service;

import com.shopmanagement.user.controller.UserController;
import com.shopmanagement.user.dto.request.UserRequestDto;
import com.shopmanagement.user.entity.Users;
import org.springframework.http.ResponseEntity;

import java.util.Optional;

public interface UserServices {

    ResponseEntity<?> createUser(UserRequestDto users,String token);

    ResponseEntity<?> getUsers(UserController.UserListRequestDto dto);

    ResponseEntity<?> getUserListSearch(String searchString);

    Optional<Users> getUser(Integer userId);

    ResponseEntity<?> loginUser(String userName, String password );

    ResponseEntity<?> getUserBranch(String loginName);

    ResponseEntity<?> checkLoginNameExists(String loginName);

}
