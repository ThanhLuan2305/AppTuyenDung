package com.doan.AppTuyenDung.DTO.Request;

import com.doan.AppTuyenDung.entity.CodeGender;

import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Data
public class UserUpdateRequest {
	private String firstName;
    private String lastName;
    private String email;
    private String address;
    private String genderCode;
    private String image;
    private String dob;
}
