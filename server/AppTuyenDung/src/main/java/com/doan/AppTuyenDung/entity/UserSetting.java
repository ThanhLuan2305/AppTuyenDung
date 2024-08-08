package com.doan.AppTuyenDung.Entity;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;

import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
@Entity
@Table(name = "usersettings")
public class UserSetting {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String categoryJobCode;
    private String salaryJobCode;
    private String addressCode;
    private String experienceJobCode;
    private Boolean isFindJob;
    private Boolean isTakeMail;
    private byte[] file;

    @OneToOne
    @JoinColumn(name = "userId")
    private User user;

    // Getters and Setters
}