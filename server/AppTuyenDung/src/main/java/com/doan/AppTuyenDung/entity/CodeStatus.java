package com.doan.AppTuyenDung.entity;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
@Entity

@Table(name = "CodeStatus")
public class CodeStatus {
    @Id
    private String code;
    private String type;
    private String value;
    private String image;

    @OneToMany(mappedBy = "statusCode")
    private List<Company> companies;
    
    
    @OneToMany(mappedBy = "statusCode")
    private List<Account> accounts;
}
