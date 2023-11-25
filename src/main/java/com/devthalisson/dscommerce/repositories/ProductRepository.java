package com.devthalisson.dscommerce.repositories;

import com.devthalisson.dscommerce.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
