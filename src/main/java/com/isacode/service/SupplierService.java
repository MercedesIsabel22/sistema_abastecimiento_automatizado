package com.isacode.service;

import com.isacode.entity.Supplier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface SupplierService {

    public Page<Supplier> list(Pageable pageable);

    public Supplier searchSupplier(int code);

    public void deleteSupplier(Supplier obj);

    public void updateSupplier(Supplier obj);

    public void createSupplier(Supplier obj);
}
