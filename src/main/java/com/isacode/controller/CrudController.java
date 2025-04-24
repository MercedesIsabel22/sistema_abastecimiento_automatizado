package com.isacode.controller;

import com.isacode.entity.Customer;
import com.isacode.entity.Product;
import com.isacode.entity.Supplier;
import com.isacode.service.CustomerService;
import com.isacode.service.ProductService;
import com.isacode.service.ProductTypeService;
import com.isacode.service.SupplierService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;


import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
@Controller
@RequestMapping("/")
@RequiredArgsConstructor
public class CrudController {

    private final ProductService productService;

    private final ProductTypeService typeService;

    private final SupplierService supplierService;

    private final CustomerService customerService;

    @GetMapping("crud_Products")
    public ModelAndView showProducts() {
        ModelAndView mav = new ModelAndView("crudProducts");
        mav.addObject("paqueteP",productService.list());
        return mav;
    }

    @GetMapping("crud_Customer")
    public ModelAndView showCustomers(Pageable pageable, @RequestParam(name="page",defaultValue = "0")int page){
        ModelAndView mav = new ModelAndView("crudCustomers");
        mav.addObject("paqueteC",customerService.list(pageable));
        mav.addObject("page", page);
        return mav;
    }

    @GetMapping("crud_Suppliers")
    public ModelAndView showSuppliers(Pageable pageable, @RequestParam(name="page",defaultValue = "0")int page) {
        ModelAndView mav = new ModelAndView("crudSuppliers");
        mav.addObject("paquetePv",supplierService.list(pageable));
        mav.addObject("page", page);
        return mav;
    }

    @GetMapping("crud_delete_Product")
    public ModelAndView deleteProduct(@RequestParam(name="idprod")int idprod) {
        ModelAndView mav = new ModelAndView("crudProducts");
        Product p = productService.searchProduct(idprod);
        productService.deleteProduct(p);
        mav.addObject("paqueteP",productService.list());
        return mav;
    }

    @GetMapping("crud_delete_Customer")
    public ModelAndView deleteCustomer(@RequestParam(name="idcli")int idcust, Pageable pageable){
        ModelAndView mav=new ModelAndView("crudCustomers");
        Customer c = customerService.searchCustomer(idcust);
        customerService.deleteCustomer(c);
        mav.addObject("paqueteC",customerService.list(pageable));
        return mav;
    }

    @GetMapping("crud_delete_Supplier")
    public ModelAndView deleteSupplier(@RequestParam(name="idpro")int idpro,Pageable pageable) {
        ModelAndView mav=new ModelAndView("crudProveedores");
        Supplier pv = supplierService.searchSupplier(idpro);
        supplierService.deleteSupplier(pv);
        mav.addObject("paquetePv",supplierService.list(pageable));
        return mav;
    }


    @GetMapping("crud_invocar_edit_Product")
    public ModelAndView  editProductForm(@RequestParam(name = "idprod")int idprod,Pageable pageable) {
        ModelAndView mav = new ModelAndView("crudEditProduct");
        Product p = productService.searchProduct(idprod);

        mav.addObject("PaqueteP",p);
        mav.addObject("paquetetp",typeService.list());
        mav.addObject("PaquetePv",supplierService.list(pageable));
        return mav;
    }

    @GetMapping("crud_invocar_edit_Customer")
    public ModelAndView editCustomerForm(@RequestParam(name = "idcli")int idCustomer) {
        ModelAndView mav = new ModelAndView("crudEditCustomer");
        mav.addObject("PaqueteC",customerService.searchCustomer(idCustomer));
        return mav;
    }

    @GetMapping("crud_invocar_edit_Supplier")
    public ModelAndView editSupplierForm(@RequestParam(name = "idpro")int idpro) {
        ModelAndView mav = new ModelAndView("crudEditSupplier");
        mav.addObject("supplier",supplierService.searchSupplier(idpro));
        return mav;
    }

    @PostMapping("/crud_ejecutar_edit_Product")
    public String updateProduct(@ModelAttribute(name = "product") Product obj) {
        productService.updateProduct(obj);
        return "redirect:/crud_Products";
    }

    @PostMapping("/crud_ejecutar_edit_Customer")
    public String updateCustomer(@ModelAttribute(name = "paqueteC") Customer obj2) {
        customerService.updateCustomer(obj2);
        return "redirect:/crud_Customers";
    }

    @PostMapping("/crud_ejecutar_edit_Supplier")
    public String  updateSupplier(@ModelAttribute(name = "paquetePv") Supplier obj) {
        supplierService.updateSupplier(obj);
        return "redirect:/crud_Suppliers";
    }


    @GetMapping("crud_Create_Product")
    public ModelAndView createProductForm(Pageable pageable) {
        ModelAndView mav=new ModelAndView("crudNewProduct");
        mav.addObject("PaqueteP",new Product());
        mav.addObject("paquetetp",typeService.list());
        mav.addObject("PaquetePv",supplierService.list(pageable));
        return mav;
    }

    @GetMapping("crud_Create_Customer")
    public ModelAndView createCustomerForm() {
        ModelAndView mav=new ModelAndView("crudNewCustomer");
        mav.addObject("PaqueteC",new Customer());
        return mav;
    }

    @GetMapping("crud_Create_Supplier")
    public ModelAndView createSupplierForm() {
        ModelAndView mav=new ModelAndView("crudNewSupplier");
        mav.addObject("PaquetePv",new Supplier());
        return mav;
    }


    @PostMapping("/crud_ejecutar_create_product")
    public String saveNewProduct(@ModelAttribute (name = "PaqueteP") Product obj) {
        productService.createProduct(obj);

        return "redirect:/crud_Products";
    }

    @PostMapping("/crud_ejecutar_create_customer")
    public String saveNewCustomer(@Valid @ModelAttribute (name = "PaqueteC") Customer obj, BindingResult result) {
        if (result.hasErrors()) {
            return "crudNewCustomer";
        }
        customerService.createCustomer(obj);

        return "redirect:/crud_Customers?page=0&size=10";
    }

    @PostMapping("/crud_ejecutar_create_supplier")
    public String saveNewSupplier(@ModelAttribute (name = "supplier") Supplier obj) {
        supplierService.createSupplier(obj);

        return "redirect:/crud_Suppliers?page=0&size=10";
    }
}
