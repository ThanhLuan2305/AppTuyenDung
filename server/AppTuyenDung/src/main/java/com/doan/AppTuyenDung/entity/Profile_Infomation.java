package com.doan.AppTuyenDung.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "Profile_Infomation")
public class Profile_Infomation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long profileInfomation_id;

    @OneToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    private String level_wish;
    private String literacy;
    private String work_experience;
    private String occupation;
    private String city;
    private Long salary_Wish_min;
    private Long salary_Wish_max;
    private String location_work;
    private String working_form;
    private String career_goals;
    private String resume;
}
