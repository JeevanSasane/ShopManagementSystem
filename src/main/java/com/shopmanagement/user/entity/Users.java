package com.shopmanagement.user.entity;

import com.shopmanagement.masters.entity.*;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@Entity
@Getter
@Setter
@Table(name = "users")
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Users {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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

    @ManyToOne
    @JoinColumn(name = "country_id")
    private Country country;

    @ManyToOne
    @JoinColumn(name = "state_id")
    private State state;

    @ManyToOne
    @JoinColumn(name = "city_id")
    private City city;

    @ManyToOne
    @JoinColumn(name = "gender_id")
    private Gender gender;

    @ManyToOne
    @JoinColumn(name = "prefix_id")
    private Prefix prefix;

    private String landmark;

    private String profile_path;

    private LocalDateTime userCreateDateTime;

    @NonNull
    private String loginName;

    @NonNull
    private String password;

}
