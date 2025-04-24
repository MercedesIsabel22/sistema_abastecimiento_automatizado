package com.isacode.entity;

import com.isacode.dto.Enumeration.Status;
import jakarta.persistence.*;
import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "customer")
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idCustomer;
    private String  rucDni;
    private String companyName;
    private String address;
    private String phone;
    @Enumerated(EnumType.STRING)
    private Status status;

}
