package com.isacode.repository;

import com.isacode.dto.Enumeration.Status;
import com.isacode.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {
    List<Product> findByStatus(Status status);
}
