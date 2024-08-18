package com.doan.AppTuyenDung.entity;

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
import jakarta.persistence.Transient;

@Entity
@Table(name = "detailposts")
public class DetailPost {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;
    @Column(columnDefinition = "LONGTEXT")
    private String descriptionHTML;
    @Column(columnDefinition = "LONGTEXT")
    private String descriptionMarkdown;
    @ManyToOne
    @JoinColumn(name = "CodeJobType", referencedColumnName = "code")
    private CodeJobType categoryJobCode;
    @ManyToOne
    @JoinColumn(name = "CodeAdressCode", referencedColumnName = "code")
    private CodeProvince addressCode;
    @ManyToOne
    @JoinColumn(name = "CodeSalaryType", referencedColumnName = "code")
    private CodeSalaryType salaryJobCode;

    private Integer amount;

    @ManyToOne
    @JoinColumn(name = "CodeJobLevel", referencedColumnName = "code")
    private CodeJobLevel categoryJoblevelCode;
    
    @ManyToOne
    @JoinColumn(name = "CodeWorkType", referencedColumnName = "code")
    private CodeWorkType categoryWorktypeCode;

    @ManyToOne
    @JoinColumn(name = "CodeExpType", referencedColumnName = "code")
    private CodeExpType experienceJobCode;

    @ManyToOne
    @JoinColumn(name = "CodeGenderPost", referencedColumnName = "code")
    private CodeGenderPost genderPostCode;
   

    // Getters and Setters
}