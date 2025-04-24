package com.isacode.service.impl;

import com.isacode.dto.Enumeration.Status;
import com.isacode.entity.Product;
import com.isacode.repository.ProductRepository;
import com.isacode.service.ProductService;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    @Override
    public List<Product> list() {
        return productRepository.findByStatus(Status.ACTIVE);
    }

    @Override
    public Product searchProduct(int code) {
        return productRepository.findById(code).orElse(null);
    }

    @Override
    public void createProduct(Product obj) {
        productRepository.save(obj);
    }

    @Override
    public void updateProduct(Product obj) {
        Product existing = productRepository.findById(obj.getIdProduct())
                .orElseThrow(() -> new EntityNotFoundException("Producto con ID " + obj.getIdProduct() + " no encontrado"));

        existing.setDescription(obj.getDescription());
        existing.setPrice(obj.getPrice());
        existing.setSupplier(obj.getSupplier());
        existing.setStockMax(obj.getStockMax());
        existing.setStockMin(obj.getStockMin());
        existing.setProductType(obj.getProductType());
        existing.setStatus(Status.ACTIVE);

        productRepository.save(existing);
    }

    @Override
    public void deleteProduct(Product obj) {
        Product existing = productRepository.findById(obj.getIdProduct())
                .orElseThrow(() -> new EntityNotFoundException("Producto con ID " + obj.getIdProduct() + " no encontrado"));

        existing.setStatus(Status.INACTIVE);
        productRepository.save(existing);
    }

    @Override
    public JasperPrint exportReport(String repor) throws FileNotFoundException, JRException {
        List<Product>listadoP = productRepository.findByStatus(Status.ACTIVE);
        File archivo = ResourceUtils.getFile(repor);
        JasperReport report = JasperCompileManager.compileReport(archivo.getAbsolutePath());
        JRBeanCollectionDataSource ds = new JRBeanCollectionDataSource(listadoP);
        Map<String,Object> parametros = new HashMap<>();
        JasperPrint print = JasperFillManager.fillReport(report, parametros,ds);

        return print;
    }
}
