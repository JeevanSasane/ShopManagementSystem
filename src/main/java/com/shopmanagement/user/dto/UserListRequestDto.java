package com.shopmanagement.user.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class UserListRequestDto {

    private Integer userId;
    private Integer page;
    private Integer size;

}
