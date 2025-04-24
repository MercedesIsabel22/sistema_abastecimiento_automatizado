package com.isacode.entity;

import com.isacode.dto.Enumeration.Status;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "product")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idProduct;

    @ManyToOne
    @JoinColumn(name = "productType")
    private TypeProduct productType;

    private String description;
    private Double price;
    private int stockMin;
    private int stockMax;

    @ManyToOne
    @JoinColumn(name = "supplier")
    private Supplier supplier;
    @Enumerated(EnumType.STRING)
    private Status status;

    @OneToMany(mappedBy = "product")
    List<Operation> listOperations;

    public int getStock() {
        return listOperations.stream().mapToInt(o -> o.getAmount()).sum();
    }

    public boolean getReponer() {
        int stk = getStock();
        boolean men = false;
        if (stk < getStockMin()) {
            men = true;
        }else {
            men = false;
        }
        return men;
    }
    public String getNameSupplier() {
        return supplier.getCompanyName();
    }

    public TypeProduct getType() {
        return productType;
    }
    public String getNameTypeProduct() {
        return getProductType().getDescription();
    }


}
