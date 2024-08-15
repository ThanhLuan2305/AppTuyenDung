package com.doan.AppTuyenDung.entity;
import java.util.Set;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "packagecvs")
public class PackageCv {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;
    private String value;
    private Double price;
    private Boolean isActive;

    @OneToMany(mappedBy = "packageCv")
    private Set<OrderPackageCv> orderPackageCvs;

    // Getters and Setters
}