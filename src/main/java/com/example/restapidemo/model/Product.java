package com.example.restapidemo.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Entity
public class Product {
    @Id
    private long id;
    private String name;
    private double price;
    private LocalDate productionDate;
    private LocalDate expiryDate;
}
