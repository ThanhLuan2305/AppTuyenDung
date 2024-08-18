package com.doan.AppTuyenDung.entity;

import java.util.Date;
import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "companies")
public class Company {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;
    private String thumbnail;
    private String coverImage;
    @Column(columnDefinition = "LONGTEXT")
    private String descriptionHTML;
    @Column(columnDefinition = "LONGTEXT")
    private String descriptionMarkdown;
    private String website;
    private String address;
    private String phonenumber;
    private Integer amountEmployer;
    private String taxnumber;
    @ManyToOne
    @JoinColumn(name = "CodeStatus", referencedColumnName = "code")
    private CodeStatus statusCode;
    @ManyToOne
    @JoinColumn(name = "CodeCensorStatus", referencedColumnName = "code")
    private CodeCensorstatus censorCode;
    @Column(columnDefinition = "LONGBLOB")
    private byte[] file; // BYTES
    private Integer allowPost;
    private Integer allowHotPost;
    private Integer allowCvFree;
    private Integer allowCV;
    @ManyToOne
    @JoinColumn(name = "userId")
    private User user;
    private Date createdAt;
    private Date updatedAt;

    // Getters and Setters
}