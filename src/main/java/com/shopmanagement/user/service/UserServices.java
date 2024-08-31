package com.shopmanagement.user.service;

import com.shopmanagement.user.dto.UserRequestDto;
import com.shopmanagement.user.entity.Users;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

public interface UserServices {

    Users createUser(UserRequestDto users,String token);

    List<Users> getUsers();

    Optional<Users> getUser(Integer userId);

    ResponseEntity<?> loginUser(String userName, String password );

    ResponseEntity<?> getUserBranch(String loginName);
}
