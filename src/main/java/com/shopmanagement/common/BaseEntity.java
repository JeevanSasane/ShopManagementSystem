package com.shopmanagement.common;

import com.shopmanagement.user.entity.Users;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import java.time.LocalDateTime;

@Getter
@Setter
public class BaseEntity {

    @Column(nullable = false)
    private Boolean isActive = true;

    @Column(nullable = false)
    private Boolean isDelete = false;

    @CreatedBy
    @ManyToOne
    @JoinColumn(name = "created_by")
    private Users createBy;

    @CreatedDate
    @Column(nullable = false, updatable = false)
    private LocalDateTime createDateTime;

    @LastModifiedBy
    @ManyToOne
    @JoinColumn(name = "updated_by")
    private Users updatedBy;

    @LastModifiedDate
    @Column(nullable = false)
    private LocalDateTime lastUpdatedDateTime;
}
