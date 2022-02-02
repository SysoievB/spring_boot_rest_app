package com.app.spring_boot_rest_app.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@Table(name = "accounts")
public class Account extends BaseEntity {

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private AccountStatus status;

    public Account(AccountStatus status) {
        this.status = status;
    }

    public Account(Long id, AccountStatus status) {
        this.id = id;
        this.status = status;
    }
}
