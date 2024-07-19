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
    public Integer company_id;

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

    

    // Getters and setters
}