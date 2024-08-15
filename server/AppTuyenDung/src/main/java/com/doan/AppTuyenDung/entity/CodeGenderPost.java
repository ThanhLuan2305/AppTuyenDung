package com.doan.AppTuyenDung.entity;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
@Entity

@Table(name = "CodeGenderPost")
public class CodeGenderPost {
    @Id
    private String code;
    private String type;
    private String value;
    private String image;

    @OneToMany(mappedBy = "genderPostCode")
    private List<DetailPost> detailPosts;

}
