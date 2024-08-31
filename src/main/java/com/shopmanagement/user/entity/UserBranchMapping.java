package com.shopmanagement.user.entity;

import com.shopmanagement.masters.entity.Branch;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "user_branch_mapping")
public class UserBranchMapping {

    @EmbeddedId
    private UserBranchMappingId id;

    @MapsId("userId")
    @ManyToOne
    @JoinColumn(name = "user_id")
    private Users user;

    @MapsId("branchId")
    @ManyToOne
    @JoinColumn(name = "branch_id")
    private Branch branch;
}
