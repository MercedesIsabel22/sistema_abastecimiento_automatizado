package com.isacode.service.impl;

import com.isacode.dto.Enumeration.Status;
import com.isacode.entity.Customer;
import com.isacode.repository.CustomerRepository;
import com.isacode.service.CustomerService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {
    private final CustomerRepository customerRepository;

    @Override
    public Page<Customer> list(Pageable pageable) {
        return customerRepository.findByStatus(Status.ACTIVE, pageable);
    }

    @Override
    public Customer searchCustomer(int code) {
        return customerRepository.findById(code).orElse(null);
    }

    @Override
    public void createCustomer(Customer obj) {
        customerRepository.save(obj);
    }

    @Override
    public void updateCustomer(Customer obj) {
        Customer existing = customerRepository.findById(obj.getIdCustomer())
                .orElseThrow(() -> new EntityNotFoundException("Producto con ID " + obj.getIdCustomer()+ " no encontrado"));

        existing.setPhone(obj.getPhone());
        existing.setAddress(obj.getAddress());
        existing.setRucDni(obj.getRucDni());
        existing.setStatus(Status.ACTIVE);
        customerRepository.save(existing);
    }

    @Override
    public void deleteCustomer(Customer obj) {
        Customer existing = customerRepository.findById(obj.getIdCustomer())
        .orElseThrow(() -> new EntityNotFoundException("Producto con ID " + obj.getIdCustomer() + " no encontrado"));
        existing.setStatus(Status.INACTIVE);
        customerRepository.save(existing);
    }
}
