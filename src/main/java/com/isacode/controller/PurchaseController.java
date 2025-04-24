package com.isacode.controller;


import com.isacode.dto.ProductOperationDTO;

import com.isacode.entity.Product;
import com.isacode.service.OperationsService;
import com.isacode.service.ProductService;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletResponse;

import lombok.RequiredArgsConstructor;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
import java.util.ArrayList;


import java.util.Iterator;
import java.util.List;

@Controller
@RequestMapping("/")
@RequiredArgsConstructor
public class PurchaseController {

    private final ProductService productService;
    private final OperationsService operationsService;
    List<ProductOperationDTO> shoppingCart  = new ArrayList<>();

    /**
     * Listar productos en control de reposicion
     *
     */

    @GetMapping("/repositions")
    public ModelAndView listRestocks() {
        ModelAndView mav = new ModelAndView("repositions");
        List<Product> products = productService.list();
        mav.addObject("paqueteP",products);
        return mav;
    }

    @GetMapping("/replenish-product")
    public ModelAndView getProductForReplenishment(@RequestParam(name = "idprod")int idprod) {
        ModelAndView mav=new ModelAndView("replenishProduct");
        mav.addObject("productP",productService.searchProduct(idprod));
        return mav;

    }

    @PostMapping("/replenish-product/update")
    public String updateProductReplenishment(@ModelAttribute(name = "product") Product obj ) {
        operationsService.createOperation(obj);
        return "redirect:/repositions";
    }

    @GetMapping("/purchase-products")
    public ModelAndView viewProductsForPurchase() {
        ModelAndView mav=new ModelAndView("buyProducts");
        mav.addObject("productList",productService.list());
        mav.addObject("product",new ProductOperationDTO());
        mav.addObject("shopping",shoppingCart);
        return mav;
    }

    @PostMapping("/purchase-products/store")
    public String storeProductInCart(@ModelAttribute(name = "productDTO") ProductOperationDTO obj ) {
        obj.setName(productService.searchProduct(obj.getId()).getDescription());
        shoppingCart.add(obj);

        return "redirect:/purchase-products";
    }

    @GetMapping("/remove-product-from-cart")
    public String removeProductFromCart(@ModelAttribute(name = "idp")int obj ) {
        Iterator<ProductOperationDTO> it = shoppingCart.iterator();
        while (it.hasNext()) {
            if (it.next().getId()== obj) {
                it.remove();
            }
        }
        return "redirect:/purchase-products";
    }

    @PostMapping("/purchase-products/save")
    public String savePurchasedProducts() {
        operationsService.createMultipleOperations();
        return "redirect:/repositions";
    }

    @GetMapping("/restockReport")
    public void exportPDF(HttpServletResponse response)throws JRException, IOException {
        response.addHeader("Content-disposition", "inline: filename" + "vendedor.pdf");
        response.setContentType("application/pdf");
        ServletOutputStream outputStream = response.getOutputStream();
        JasperExportManager.exportReportToPdfStream(productService.exportReport("classpath:report_reponer.jrxml"),outputStream);
        outputStream.flush();
        outputStream.close();
    }
}
