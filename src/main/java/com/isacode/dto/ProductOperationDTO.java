package com.isacode.dto;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductOperationDTO {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String idCustomer;
    @NotNull
    private int amount;
    @NotNull
    private Double price;
}
