package com.doan.AppTuyenDung.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "cvs")
public class Cv {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private byte[] file;
    private Boolean isChecked;
    private String status;
    private java.util.Date interviewTime;
    private String description;

    @ManyToOne
    @JoinColumn(name = "userId")
    private User user;

    @ManyToOne
    @JoinColumn(name = "postId")
    private Post post;

    private java.util.Date createdAt;
    private java.util.Date updatedAt;

    // Getters and Setters
}