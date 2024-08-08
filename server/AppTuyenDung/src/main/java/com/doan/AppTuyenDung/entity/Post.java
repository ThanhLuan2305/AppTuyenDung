package com.doan.AppTuyenDung.Entity;
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

    private String statusCode;
    private String timeEnd;
    private String timePost;
    private Boolean isHot;

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

    private java.util.Date createdAt;
    private java.util.Date updatedAt;

    // Getters and Setters
}