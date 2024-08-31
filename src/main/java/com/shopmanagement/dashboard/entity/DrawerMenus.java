package com.shopmanagement.dashboard.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "system_menus")
public class DrawerMenus {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long underMenuId;

    private String menuName;

    private String route;

    private String iconPath;

    private Boolean isActive;

    private Boolean isDrawerMenu;

    private Integer level;

    private Integer sequence;
}
