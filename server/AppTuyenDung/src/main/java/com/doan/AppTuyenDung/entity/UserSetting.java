package com.doan.AppTuyenDung.entity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
@Entity
@Table(name = "usersettings")
public class UserSetting {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "CodeJobType", referencedColumnName = "code")
    private CodeJobType categoryJobCode;
    @ManyToOne
    @JoinColumn(name = "CodeSalaryType", referencedColumnName = "code")
    private CodeSalaryType salaryJobCode;

    @ManyToOne
    @JoinColumn(name = "CodeAdressCode", referencedColumnName = "code")
    private CodeProvince addressCode;
    
    @ManyToOne
    @JoinColumn(name = "CodeExpType", referencedColumnName = "code")
    private CodeExpType experienceJobCode;

    private Boolean isFindJob;
    private Boolean isTakeMail;
    @Column(columnDefinition = "LONGBLOB")
    private byte[] file;

    @OneToOne
    @JoinColumn(name = "userId")
    private User user;

    // Getters and Setters
}