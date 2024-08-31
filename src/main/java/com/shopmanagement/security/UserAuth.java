package com.shopmanagement.security;


public interface UserAuth {
    
    Long getUserId(String token);

    Long getEmployeeId(String token);

    Boolean getValidateUserPrivilege(Long menuId,String privilege,Long userId);

//    List<Actions> getUserActions(Long menuId,Long userId);

//    Boolean isRadarUser(Long userId);
}
