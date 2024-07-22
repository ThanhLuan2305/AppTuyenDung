package com.doan.AppTuyenDung.entity;

import java.sql.Date;


import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "Applications")
public class Applications {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer applications_id;
    @ManyToOne
    @JoinColumn(name = "job_id")
    public Job_Model job;
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    public User user;
    public Date application_date;
    public String status;
    public String act;
}
