package com.isacode.repository;

import com.isacode.entity.Operation;
import com.isacode.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OperationRepository extends JpaRepository<Operation, Integer> {
    List<Operation> findByProduct(Product product);
}
