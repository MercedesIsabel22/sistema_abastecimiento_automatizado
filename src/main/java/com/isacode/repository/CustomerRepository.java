package com.isacode.repository;

import com.isacode.dto.Enumeration.Status;
import com.isacode.entity.Customer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Integer> {
    Page<Customer> findByStatus(Status status, Pageable pageable);
}
