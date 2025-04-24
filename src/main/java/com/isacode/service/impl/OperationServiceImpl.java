package com.isacode.service.impl;

import com.isacode.dto.Enumeration.Status;
import com.isacode.dto.ProductOperationDTO;
import com.isacode.entity.Operation;
import com.isacode.entity.Product;
import com.isacode.repository.OperationRepository;
import com.isacode.service.OperationsService;
import com.isacode.service.ProductService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
@Service
@RequiredArgsConstructor
public class OperationServiceImpl implements OperationsService {
    private final ProductService productService;
    private final OperationRepository operationsRepository;
    List<ProductOperationDTO> shoppingCart  = new ArrayList<>();

    @Override
    public void createOperation(Product obj) {
        Date date = new Date(System.currentTimeMillis());
        Operation opr = Operation.builder()
                .process("REPO")
                .type(1)
                .product(obj)
                .amount(obj.getStockMin())
                .purchasePrice(obj.getPrice())
                .purchaseDate(date)
                .status(Status.ACTIVE)
                .build();
        operationsRepository.save(opr);
    }

    @Override
    public void createMultipleOperations() {
        Date date = new Date(System.currentTimeMillis());
        List<Operation> paquete = new ArrayList<Operation>();
        Operation ope;
        for (ProductOperationDTO cap : shoppingCart) {
            Product p = productService.searchProduct(cap.getId());
            int cant = cap.getAmount();
            ope = Operation.builder()
                    .process("COM")
                    .type(2)
                    .product(p)
                    .amount(cant)
                    .purchasePrice(cap.getPrice())
                    .purchaseDate(date)
                    .status(Status.ACTIVE) // asumiendo que `1` representa `Status.ACTIVE`
                    .build();
            paquete.add(ope);
        }
        shoppingCart = new ArrayList<ProductOperationDTO>();
        operationsRepository.saveAll(paquete);
    }
}
