package com.isacode.service.impl;


import com.isacode.dto.Enumeration.Status;
import com.isacode.dto.ProductOperationDTO;
import com.isacode.entity.*;
import com.isacode.repository.SaleRepository;
import com.isacode.service.*;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SaleServiceImpl implements SaleService {

    private final SaleRepository saleRepository;
    @Override
    public void createSale(Sale obj) {
        saleRepository.save(obj);
    }

}
