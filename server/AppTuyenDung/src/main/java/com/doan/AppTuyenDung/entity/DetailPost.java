package com.doan.AppTuyenDung.Entity;

import java.util.Set;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "detailposts")
public class DetailPost {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;
    private String descriptionHTML;
    private String descriptionMarkdown;
    private String categoryJobCode;
    private String addressCode;
    private String salaryJobCode;
    private Integer amount;
    private String categoryJoblevelCode;
    private String categoryWorktypeCode;
    private String experienceJobCode;
    private String genderPostCode;

    @OneToMany(mappedBy = "detailPost")
    private Set<Post> posts;

    // Getters and Setters
}