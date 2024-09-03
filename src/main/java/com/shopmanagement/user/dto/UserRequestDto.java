package com.shopmanagement.user.dto;


import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class UserRequestDto {

    private int id;

    @NonNull
    private String firstName;

    @NonNull
    private String lastName;

    @NonNull
    private LocalDate dateOfBirth;

    private Integer age;

    private Boolean isActive=true;

    @NonNull
    private String mobileNo;

    private String alternateMobileNo;

    private String countryName;

    private String stateName;

    private String cityName;

    private Integer genderId;

    private Integer prefixId;

    private String landmark;

    private String profile_base64;

    private String profile_path;

    private String profile_name;

    private LocalDateTime userCreateDateTime;

    @NonNull
    private String loginName;

    @NonNull
    private String password;

    private List<Long> branchIds;
}
