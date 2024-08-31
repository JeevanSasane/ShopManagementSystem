package com.shopmanagement.dashboard.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class DrawerMenuRequestDto {

    private Long underMenuId;

    private String menuName;

    private String route;

    private String iconPath;

    private Boolean isActive;

    private Boolean isDrawerMenu;

    private Integer level;

    private Integer sequence;
}
