package com.shopmanagement.user.entity;


import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Embeddable
@Getter
@Setter
public class UserBranchMappingId implements Serializable {

        private int userId;
        private Long branchId;

}
