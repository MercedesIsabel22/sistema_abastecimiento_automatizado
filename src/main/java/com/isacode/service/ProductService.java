package com.isacode.service;

import com.isacode.entity.Product;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperPrint;

import java.io.FileNotFoundException;
import java.util.List;

public interface ProductService {
    public List<Product> list();
    public Product searchProduct(int code);
    public void createProduct(Product obj);
    public void updateProduct(Product obj);
    public void deleteProduct(Product obj);
    public JasperPrint exportReport(String report)throws FileNotFoundException, JRException;
}
