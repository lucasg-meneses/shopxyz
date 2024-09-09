package com.lucasgmeneses.shopxyz.repository;

import com.lucasgmeneses.shopxyz.data.model.ProductModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface IProductRepository extends JpaRepository<ProductModel, UUID> {
}
