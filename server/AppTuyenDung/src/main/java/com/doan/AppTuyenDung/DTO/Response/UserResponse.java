package com.doan.AppTuyenDung.DTO.Response;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserResponse {
	private int id;
    private String firstName;
    private String lastName;
    private String email;
    private String address;
    private CodeResponse genderCode;
    private String image;
    private String dob;
    private Integer companyId;
    private UserSettingResponse userSettingData;
}