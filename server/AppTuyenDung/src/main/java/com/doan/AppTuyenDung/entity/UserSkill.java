package com.doan.AppTuyenDung.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

import jakarta.persistence.Table;

@Entity
@Table(name = "userskills")
@IdClass(UserSkillId.class)
public class UserSkill {
    @Id
    private Integer userId;

    @Id
    private Integer skillId;

    @ManyToOne
    @JoinColumn(name = "userId", insertable = false, updatable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "skillId", insertable = false, updatable = false)
    private Skill skill;

    // Getters and Setters
}