package com.doan.AppTuyenDung.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "Certificate")
public class Certificate {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long certificate_id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    private String name_certificate;
    private String training_center;
    private String forms_of_training;
    private LocalDateTime date_start_certificate;
    private LocalDateTime date_end_certificate;
    private String description_other;
}
