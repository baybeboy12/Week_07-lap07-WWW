package com.example.backend.repositories;

import com.example.backend.models.ProductPrice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface ProductPriceRepository extends JpaRepository<ProductPrice,Long>, JpaSpecificationExecutor<ProductPrice> {
}
