package com.lucasgmeneses.shopxyz.data.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.UUID;
@Entity
@Table(name = "tb_order_product")
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class OrderProductModel implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "order_id")
    private OrderModel order;

    @OneToOne
    @JoinColumn(name = "product_id")
    private ProductModel product;

    @Column
    private BigDecimal quantity;
}
