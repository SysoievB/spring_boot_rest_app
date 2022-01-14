package com.app.spring_boot_rest_app.entity;

import lombok.AllArgsConstructor;
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

    public Order(String orderName) {
        this.orderName = orderName;
    }

    public Order(Long id, String orderName) {
        this.id = id;
        this.orderName = orderName;
    }
}
