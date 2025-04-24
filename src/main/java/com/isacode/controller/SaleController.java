package com.isacode.controller;

import com.isacode.dto.Enumeration.Status;
import com.isacode.dto.ProductOperationDTO;
import com.isacode.entity.*;
import com.isacode.service.*;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

@Controller
@RequestMapping("/")
@RequiredArgsConstructor
public class SaleController {

    private final ProductService productService;
    private final  OperationsService operationsService;
    private final CustomerService customerService;
    private final SaleService saleService;
    private final SequentialCodeService codeService;
    List<ProductOperationDTO> sale  = new ArrayList<>();

    @GetMapping("/SaleProducts")
    public ModelAndView SellProduct(Pageable pageable, HttpSession session) {
        ModelAndView mav=new ModelAndView("SaleProducts");
        mav.addObject("productlist", productService.list());
        mav.addObject("product",new ProductOperationDTO());
        mav.addObject("customerlist", customerService.list(pageable));
        mav.addObject("sales",sale);
        mav.addObject("customerC",new Customer());
        return mav;
    }

    @PostMapping("/SaleProduct/store")
    public String storeProduct(@ModelAttribute(name = "product") ProductOperationDTO obj) {
        obj.setName(productService.searchProduct(obj.getId()).getDescription());
        sale.add(obj);
        return "redirect:/SaleProducts";
    }

    @GetMapping("/presaveDelete")
    public String presaveDelete(@RequestParam(name = "idp")int obj) {
        Iterator<ProductOperationDTO> it = sale.iterator();
        while (it.hasNext()) {
            if (it.next().getId()== obj) {
                it.remove();
            }
        }
        return "redirect:/SaleProducts";
    }

    @PostMapping("saleProducts/save")
    public String saveProduct(@ModelAttribute(name = "orden") Customer obj) {
        System.out.println(obj.getIdCustomer());
        Date date = new Date(System.currentTimeMillis());
        Double costosum =sale.stream().mapToDouble(o -> o.getPrice()).sum();
        String codigoVTA = (codeService.searchNumber(1).generateCode());
        Sale venta = Sale.builder()
                .customer(customerService.searchCustomer(obj.getIdCustomer()))
                .date(date)
                .price(costosum)
                .status(Status.ACTIVE)
                .build();

        List<Operation> paquete = new ArrayList<Operation>();
        Operation ope;
        for (ProductOperationDTO cap : sale) {
            Product p = productService.searchProduct(cap.getId());
            int cant = cap.getAmount();
            ope = Operation.builder()
                    .process(codigoVTA)
                    .type(2)
                    .product(p)
                    .amount(-cant)
                    .purchasePrice(cap.getPrice())
                    .purchaseDate(date)
                    .status(Status.ACTIVE)
                    .build();
            paquete.add(ope);
            }
        sale = new ArrayList<ProductOperationDTO>();
        operationsService.createMultipleOperations();
        saleService.createSale(venta);
        return "redirect:/SaleProducts";
    }

}
