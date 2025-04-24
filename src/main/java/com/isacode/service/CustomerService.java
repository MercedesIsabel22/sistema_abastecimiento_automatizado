package com.isacode.service;

import com.isacode.entity.Customer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CustomerService {

    public Page<Customer> list(Pageable pageable);
    public Customer searchCustomer(int code);
    public void createCustomer(Customer obj);
    public void updateCustomer(Customer obj);
    public void deleteCustomer(Customer obj);
}
