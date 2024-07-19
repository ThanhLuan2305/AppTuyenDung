package com.doan.AppTuyenDung.entity;

import java.sql.Date;


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
@Table(name = "Job_Model")
public class Job_Model {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long job_id;
    public String job_title;
    public String career;
    public String job_description;
    public String location;
    public int quanlity;
    public Long salary_min;
    public Long salary_max;
    public String city;
    public String district;
    public Date posted_date;
    public Date expiry_date;
    public Boolean urgent;

    @ManyToOne
    @JoinColumn(name = "company_id")
    public Company company;

    @ManyToOne
    @JoinColumn(name = "user_id")
    public User user;

    @ManyToOne
    @JoinColumn(name = "position_id")
    public Position_In_Job position;

    @ManyToOne
    @JoinColumn(name = "workingForm_id", nullable = false)
    public Working_Form workingForm;

    @ManyToOne
    @JoinColumn(name = "employment_id")
    public Employment_Type employmentType;

}

