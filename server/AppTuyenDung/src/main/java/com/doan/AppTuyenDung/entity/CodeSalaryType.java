package com.doan.AppTuyenDung.entity;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
@Entity

@Table(name = "CodeSalaryType")
public class CodeSalaryType {
    @Id
    private String code;
    private String type;
    private String value;
    private String image;

    @OneToMany(mappedBy = "salaryJobCode")
    private List<DetailPost> detailPosts;


    @OneToMany(mappedBy = "salaryJobCode")
    private List<UserSetting> usersettings;
}
