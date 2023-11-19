package com.example.backend.repositories;

import com.example.backend.models.ProductImage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface ProductImageRepository extends JpaRepository<ProductImage,Long> , JpaSpecificationExecutor<ProductImage> {
}
