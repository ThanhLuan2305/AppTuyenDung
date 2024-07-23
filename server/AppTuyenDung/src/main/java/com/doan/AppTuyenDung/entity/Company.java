package com.doan.AppTuyenDung.entity;


import java.util.Date;

import jakarta.persistence.*;
import lombok.Data;



@Data
@Entity
@Table(name = "Company")
public class Company {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long company_id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    public User user;

    @ManyToOne
    @JoinColumn(name = "sizeCompany_id")
    public SizeCompany size_Company;

    public String company_name;

    public String email_company;

    public String industry;

    public String description;

    public Date date_founding;

    public String city;
    public String district;
    public String location;
    public String logo;
    public String cover_image;
    public String website;

    // @JsonCreator
    // public Company(
    //     @JsonProperty("company_id") Integer company_id,
    //     @JsonProperty("user") User user,
    //     @JsonProperty("size_Company") SizeCompany size_Company,
    //     @JsonProperty("company_name") String company_name,
    //     @JsonProperty("email_company") String email_company,
    //     @JsonProperty("industry") String industry,
    //     @JsonProperty("description") String description,
    //     @JsonProperty("date_founding") Date date_founding,
    //     @JsonProperty("city") String city,
    //     @JsonProperty("district") String district,
    //     @JsonProperty("location") String location,
    //     @JsonProperty("logo") String logo,
    //     @JsonProperty("cover_image") String cover_image,
    //     @JsonProperty("website") String website
    // ) {
    //     this.company_id = company_id;
    //     this.user = user;
    //     this.size_Company = size_Company;
    //     this.company_name = company_name;
    //     this.email_company = email_company;
    //     this.industry = industry;
    //     this.description = description;
    //     this.date_founding = date_founding;
    //     this.city = city;
    //     this.district = district;
    //     this.location = location;
    //     this.logo = logo;
    //     this.cover_image = cover_image;
    //     this.website = website;
    // }
    

    public Company() {
    }

    public Company(Long company_id, User user, SizeCompany size_Company, String company_name, String email_company,
            String industry, String description, Date date_founding, String city, String district, String location,
            String logo, String cover_image, String website) {
        this.company_id = company_id;
        this.user = user;
        this.size_Company = size_Company;
        this.company_name = company_name;
        this.email_company = email_company;
        this.industry = industry;
        this.description = description;
        this.date_founding = date_founding;
        this.city = city;
        this.district = district;
        this.location = location;
        this.logo = logo;
        this.cover_image = cover_image;
        this.website = website;
    }

    public Long getCompany_id() {
        return this.company_id;
    }

    public void setCompany_id(Long company_id) {
        this.company_id = company_id;
    }

    public User getUser() {
        return this.user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public SizeCompany getSize_Company() {
        return this.size_Company;
    }

    public void setSize_Company(SizeCompany size_Company) {
        this.size_Company = size_Company;
    }

    public String getCompany_name() {
        return this.company_name;
    }

    public void setCompany_name(String company_name) {
        this.company_name = company_name;
    }

    public String getEmail_company() {
        return this.email_company;
    }

    public void setEmail_company(String email_company) {
        this.email_company = email_company;
    }

    public String getIndustry() {
        return this.industry;
    }

    public void setIndustry(String industry) {
        this.industry = industry;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getDate_founding() {
        return this.date_founding;
    }

    public void setDate_founding(Date date_founding) {
        this.date_founding = date_founding;
    }

    public String getCity() {
        return this.city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getDistrict() {
        return this.district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getLocation() {
        return this.location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getLogo() {
        return this.logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public String getCover_image() {
        return this.cover_image;
    }

    public void setCover_image(String cover_image) {
        this.cover_image = cover_image;
    }

    public String getWebsite() {
        return this.website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    // Getters and setters
}