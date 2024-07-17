package com.doan.AppTuyenDung.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "LanguagesSkill")
public class LanguagesSkill {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long LanguagesSkill_id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    private String name_Languages;
    private int rating;
}