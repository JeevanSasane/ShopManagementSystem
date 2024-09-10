package com.shopmanagement.user.dto.response;

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
