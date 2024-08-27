package com.doan.AppTuyenDung.DTO.Response;

import java.util.List;

public class SkillResponse {
	private Integer id;

    private String name;
    private String categoryJobCode;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCategoryJobCode() {
		return categoryJobCode;
	}
	public void setCategoryJobCode(String categoryJobCode) {
		this.categoryJobCode = categoryJobCode;
	}
}
