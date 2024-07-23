package com.doan.AppTuyenDung.entity;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

import jakarta.persistence.Table;
import lombok.Data;


@Data
@Entity
@Table(name = "jobfavourites")
public class jobfavourites {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long jobFavorite_id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    public User user;

    public Integer job_id;
}
