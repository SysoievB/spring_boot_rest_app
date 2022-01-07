package com.app.spring_boot_rest_app.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Data
@NoArgsConstructor
@Table(name = "orders")
public class Order extends BaseEntity {

    @Column(name = "order_name")
    private String orderName;

    public Order(Long id) {
        this.id = id;
    }
}
