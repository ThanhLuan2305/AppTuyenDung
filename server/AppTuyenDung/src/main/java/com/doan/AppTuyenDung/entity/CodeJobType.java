package com.doan.AppTuyenDung.entity;

import java.util.List;

import jakarta.persistence.OneToMany;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
@Entity

@Table(name = "CodeJobType")
public class CodeJobType {
    @Id
    private String code;
    private String type;
    private String value;
    private String image;

    @OneToMany(mappedBy = "categoryJobCode")
    private List<DetailPost> detailPosts;

    @OneToMany(mappedBy = "categoryJobCode")
    private List<UserSetting> userSettings;

    
}
