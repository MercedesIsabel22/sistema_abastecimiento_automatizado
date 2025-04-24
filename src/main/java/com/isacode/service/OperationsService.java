package com.isacode.service;

import com.isacode.entity.Operation;
import com.isacode.entity.Product;
import jakarta.servlet.http.HttpSession;

import java.util.List;

public interface OperationsService {

    public void createOperation(Product obj);
    public void createMultipleOperations();
}
