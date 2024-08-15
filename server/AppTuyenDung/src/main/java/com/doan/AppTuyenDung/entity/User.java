package com.doan.AppTuyenDung.entity;

import java.util.Set;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String firstName;
    private String lastName;
    private String email;
    private String address;
    @ManyToOne
    @JoinColumn(name = "CodeGender", referencedColumnName = "code")
    private CodeGender genderCode;
    private String image;
    private String dob;
    private Integer companyId;

    @OneToMany(mappedBy = "user")
    private Set<Account> accounts;

    @OneToOne
    @JoinColumn(name = "companyId", insertable = false, updatable = false)
    private Company company;


    @OneToMany(mappedBy = "user")
    private Set<Cv> cvs;

    @OneToMany(mappedBy = "user")
    private Set<Note> notes;

    @OneToMany(mappedBy = "user")
    private Set<OrderPackageCv> orderPackageCvs;

    @OneToMany(mappedBy = "user")
    private Set<OrderPackage> orderPackages;

    @OneToMany(mappedBy = "user")
    private Set<UserSkill> userSkills;

    @OneToOne(mappedBy = "user")
    private UserSetting userSetting;

    

    // Getters and Setters
}