package com.isacode.entity;

import com.isacode.dto.Enumeration.Status;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;


@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Entity
@Table(name = "operation")
public class Operation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idOperation;
    private String process;
    private int type;

    @ManyToOne
    @JoinColumn(name = "product")
    private Product product;

    private int amount;
    private Double purchasePrice;
    private Date purchaseDate;
    @Enumerated(EnumType.STRING)
    private Status status;

}
