package com.isacode.service.impl;

import com.isacode.dto.Enumeration.Status;
import com.isacode.entity.Supplier;
import com.isacode.repository.SupplierRepository;
import com.isacode.service.SupplierService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SupplierServiceImpl implements SupplierService {

    private final SupplierRepository supplierRepository;

    @Override
    public Page<Supplier> list(Pageable pageable) {
        return supplierRepository.findByStatus(Status.ACTIVE, pageable);
    }

    @Override
    public Supplier searchSupplier(int code) {
        return supplierRepository.findById(code).orElse(null);
    }

    @Override
    public void createSupplier(Supplier obj) {
        supplierRepository.save(obj);
    }

    @Override
    public void updateSupplier(Supplier obj) {
        Supplier existing = supplierRepository.findById(obj.getIdSupplier())
                .orElseThrow(() -> new RuntimeException("Supplier con ID " + obj.getIdSupplier()+ " no encontrado"));

        existing.setIdSupplier(obj.getIdSupplier());
        existing.setPhone(obj.getPhone());
        existing.setEmail(obj.getEmail());
        existing.setCompanyName(obj.getCompanyName());
        existing.setRucDni(obj.getRucDni());
        existing.setStatus(Status.ACTIVE);
        supplierRepository.save(existing);
    }

    @Override
    public void deleteSupplier(Supplier obj) {
       Supplier existing = supplierRepository.findById(obj.getIdSupplier())
               .orElseThrow(() -> new EntityNotFoundException("Supplier con ID " + obj.getIdSupplier() + " no encontrado"));
        existing.setStatus(Status.INACTIVE);
        supplierRepository.save(existing);
    }
}
