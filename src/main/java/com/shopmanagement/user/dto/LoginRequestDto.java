package com.shopmanagement.user.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginRequestDto {

    private String loginName;
    private String password;
    private Long branchId;
}
