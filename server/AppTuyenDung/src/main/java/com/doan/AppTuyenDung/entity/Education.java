package com.doan.AppTuyenDung.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "Education")
public class Education {
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private Long education_id;
@OneToOne
@JoinColumn(name = "user_id", nullable = false)
private User user;

private String name_certificate_education;
private String major;
private String name_school;
private LocalDateTime date_start_education;
private LocalDateTime date_end_education;
private String description_other;
}
