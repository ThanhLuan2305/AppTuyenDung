package com.doan.AppTuyenDung.entity;

import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;


@Entity
@Table(name = "accounts")
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String phonenumber;
    private String password;
    
    @ManyToOne
    @JoinColumn(name = "roleCode", referencedColumnName = "code")
    private CodeRule roleCode;
    
    @ManyToOne
    @JoinColumn(name = "statusCode", referencedColumnName = "code")
    private CodeStatus statusCode;

    @ManyToOne
    @JoinColumn(name = "userId")
    private User user;

    private java.util.Date createdAt;
    private java.util.Date updatedAt;
    
}
