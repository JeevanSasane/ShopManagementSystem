package com.shopmanagement.user.dto;

import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class LoginResponseDto {

    private Boolean isActive;
    private String userName;
    private Integer uId;
    private String token;

}
