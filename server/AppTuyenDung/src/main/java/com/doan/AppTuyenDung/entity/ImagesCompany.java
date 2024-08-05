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

    public ImagesCompany(Long imagesCompany_id, Company company, String address_image) {
        this.imagesCompany_id = imagesCompany_id;
        this.company = company;
        this.address_image = address_image;
    }

    public ImagesCompany() {
    }

    public ImagesCompany(Long imagesCompany_id, Company company, String address_image, Date date_created) {
        this.imagesCompany_id = imagesCompany_id;
        this.company = company;
        this.address_image = address_image;
        this.date_created = date_created;
    }

    public Long getImagesCompany_id() {
        return this.imagesCompany_id;
    }

    public void setImagesCompany_id(Long imagesCompany_id) {
        this.imagesCompany_id = imagesCompany_id;
    }

    public Company getCompany() {
        return this.company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public String getAddress_image() {
        return this.address_image;
    }

    public void setAddress_image(String address_image) {
        this.address_image = address_image;
    }

    public Date getDate_created() {
        return this.date_created;
    }

    public void setDate_created(Date date_created) {
        this.date_created = date_created;
    }

}
