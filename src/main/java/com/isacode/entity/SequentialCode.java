package com.isacode.entity;

import jakarta.persistence.*;
import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="number")
public class SequentialCode {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idSequential;
    private String prefix;
    private int numeration;
    public String generateCode() {
        String code = getPrefix() + getNumeration();
        return code;
    }
}
