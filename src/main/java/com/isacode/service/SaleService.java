package com.isacode.service;

import com.isacode.dto.ProductOperationDTO;
import com.isacode.entity.Customer;
import com.isacode.entity.Sale;
import jakarta.servlet.http.HttpSession;

import java.util.List;

public interface SaleService {

    public void createSale(Sale obj);

}
