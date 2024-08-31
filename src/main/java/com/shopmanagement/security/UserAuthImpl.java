package com.shopmanagement.security;

import com.shopmanagement.security.UserAuth;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.sql.Types;
import java.util.List;

@Service
public class UserAuthImpl implements UserAuth {

    @Autowired
    private JwtProvider jwtProvider;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public Long getUserId(String token) {
        return jwtProvider.getUserIdFromToken(token);
    }

    @Override
    public Long getEmployeeId(String token) {
        return jwtProvider.getEmployeeFromToken(token);
    }

    @Override
    public Boolean getValidateUserPrivilege(Long menuId, String privilege, Long userId) {
        return jdbcTemplate.queryForObject("exec ValidateUserPrivilegeWeb ?,?,?",new Object[]{
                menuId,privilege,userId
        },new int[]{Types.BIGINT,Types.VARCHAR,Types.BIGINT},Boolean.class);
    }

//    @Override
//    public List<Actions> getUserActions(Long menuId, Long userId) {
//        return jdbcTemplate.query("exec Sp_RetrieveUserPrivilegeWeb ?,?",new Object[]{
//                menuId,userId
//        },new int[]{Types.BIGINT,Types.BIGINT},new BeanPropertyRowMapper<Actions>(Actions.class));
//    }
//
//    @Override
//    public Boolean isRadarUser(Long userId) {
//        String sqlIsRadarUser="SELECT mt_role.[role] from  [User] INNER JOIN  EmployeeMaster on (EmployeeMaster.Id=[User].Emp_Id)\n" +
//                "       INNER JOIN employee_roles on (employee_roles.employee_id=EmployeeMaster.Id) INNER JOIN mt_role on \n" +
//                "       (mt_role.id=employee_roles.role_id)\n" +
//                "where [User].UserId=?";
//        List<String> isRadarUser=jdbcTemplate.queryForList(sqlIsRadarUser,new Object[]{
//                userId
//        },new int[]{Types.BIGINT},String.class);
//        return isRadarUser.contains("Radar");
//    }
}
