package com.doan.AppTuyenDung.entity;

import java.util.Date;


import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;


@Data
@Entity
@Table(name = "ImagesCompany")
public class ImagesCompany {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long imagesCompany_id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "company_id")
    public Company company;
    public String address_image;
    public Date date_created;

}
