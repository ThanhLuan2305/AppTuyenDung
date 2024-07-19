package com.doan.AppTuyenDung.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;


@Data
@Entity
@Table(name = "SizeCompany")
public class SizeCompany {
    @Id
    @Column(name="sizeCompany_id",nullable = false)
    public String sizeCompany_id;

    @Column(name="name_sizeCompany",nullable = false)
    public String name_sizeCompany;
}
