package com.doan.AppTuyenDung.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "WorkExperience")
public class WorkExperience {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long workExperience_id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    private String job_title;
    private String company_name;
    private LocalDateTime date_start_work;
    private LocalDateTime date_end_work;
    private String description;
}
