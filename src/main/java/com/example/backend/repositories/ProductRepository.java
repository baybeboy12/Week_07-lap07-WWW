package com.example.backend.repositories;

import com.example.backend.models.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product,Long> , JpaSpecificationExecutor<Product> {
    @Query("select p from Product p where p.status = 1 or p.status = 0")
    public Page<Product> findAll(Pageable pageable);

}
