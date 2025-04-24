package com.isacode.entity;

import com.isacode.dto.Enumeration.Status;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="supplier")
public class Supplier {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idSupplier;
    private String rucDni;
    private String companyName;
    private String phone;
    private String email;
    @Enumerated(EnumType.STRING)
    private Status status;

}
