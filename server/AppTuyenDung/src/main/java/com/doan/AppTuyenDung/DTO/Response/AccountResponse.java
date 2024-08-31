package com.doan.AppTuyenDung.DTO.Response;


import java.util.Date;
import java.util.List;

import lombok.Data;

@Data
public class AccountResponse {
	 private int id;
	    private String phonenumber;
	    private String statusCode;
	    private int userId;
	    private Date createdAt;
	    private Date updatedAt;
	    private CodeResponse roleData;
	    private UserResponse userAccountData;
	    private List<SkillIdRespones> listSkills;
}
