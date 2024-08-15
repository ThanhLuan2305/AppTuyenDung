package com.doan.AppTuyenDung.entity;
import java.util.Date;
import java.util.Set;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "posts")
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "statusCode", referencedColumnName = "code")
    private CodePostStatus statusCode;

    private String timeEnd;
    private String timePost;
    private Integer isHot;
    private java.util.Date createdAt;
    private java.util.Date updatedAt;

    @ManyToOne
    @JoinColumn(name = "userId")
    private User user;

    @ManyToOne
    @JoinColumn(name = "detailPostId")
    private DetailPost detailPost;

    @OneToMany(mappedBy = "post")
    private Set<Cv> cvs;

    @OneToMany(mappedBy = "post")
    private Set<Note> notes;

   
    public Post() {
    }



    // Getters and Setters
}