package com.lucasgmeneses.shopxyz.data.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.*;

@Entity
@Table(name = "tb_order")
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class OrderModel implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;


    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private Set<OrderProductModel> orderProducts = new HashSet<>();

    @Column
    @Enumerated(EnumType.STRING)
    private EOrderStatus status;

    @Column
    private Date orderDate;

    public void addProduct(OrderProductModel orderProduct){
        orderProducts.add(orderProduct);
    }

    public void removeProduct(OrderProductModel orderProduct){
        orderProducts.remove(orderProduct);
    }

}
