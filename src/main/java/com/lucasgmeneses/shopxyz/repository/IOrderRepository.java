package com.lucasgmeneses.shopxyz.repository;

import com.lucasgmeneses.shopxyz.data.model.OrderModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface IOrderRepository extends JpaRepository<OrderModel, UUID> {
}
