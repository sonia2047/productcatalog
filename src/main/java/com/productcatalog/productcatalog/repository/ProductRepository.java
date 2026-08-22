package com.productcatalog.productcatalog.repository;

import com.productcatalog.productcatalog.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {

    List<Product> findBySoftDeletedFalse();
}