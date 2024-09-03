package com.shopmanagement.user.entity;

import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Objects;

@Setter
@Getter
@Embeddable
public class UserBranchMappingId implements Serializable {

    private Integer userId;
    private Long branchId;

    public UserBranchMappingId() {
    }

    public UserBranchMappingId(Integer userId, Long branchId) {
        this.userId = userId;
        this.branchId = branchId;
    }

    // Getters and Setters
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserBranchMappingId that = (UserBranchMappingId) o;
        return Objects.equals(userId, that.userId) &&
                Objects.equals(branchId, that.branchId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, branchId);
    }
}
