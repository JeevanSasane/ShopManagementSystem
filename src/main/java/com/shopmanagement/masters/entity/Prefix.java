package com.shopmanagement.masters.entity;

import com.shopmanagement.common.BaseEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "mt_prefix")
public class Prefix extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String prefix;

    @ManyToOne
    @JoinColumn(name = "gender_id",nullable = false)
    private Gender gender;
}
