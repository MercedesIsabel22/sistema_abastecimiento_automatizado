package com.isacode.entity;

import com.isacode.dto.Enumeration.Status;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="sale")
public class Sale {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idSale;
    @ManyToOne
    @JoinColumn(name = "customer")
    private Customer customer;
    private Double price;
    private Date date;
    @Enumerated(EnumType.STRING)
    private Status status;
}
