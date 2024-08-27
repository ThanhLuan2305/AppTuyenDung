package com.doan.AppTuyenDung.DTO.Request;

import java.util.List;

public class UserSettingDTO {
	private Integer idUser;
    private String categoryJobCode;
    private String salaryJobCode;
    private String addressCode;
    private String experienceJobCode;
    private Boolean isFindJob;
    private Boolean isTakeMail;
    private byte[] file;
    private List<Integer> skills;
	public Integer getIdUser() {
		return idUser;
	}
	public void setIdUser(Integer idUser) {
		this.idUser = idUser;
	}
	public String getCategoryJobCode() {
		return categoryJobCode;
	}
	public void setCategoryJobCode(String categoryJobCode) {
		this.categoryJobCode = categoryJobCode;
	}
	public String getSalaryJobCode() {
		return salaryJobCode;
	}
	public void setSalaryJobCode(String salaryJobCode) {
		this.salaryJobCode = salaryJobCode;
	}
	public String getAddressCode() {
		return addressCode;
	}
	public void setAddressCode(String addressCode) {
		this.addressCode = addressCode;
	}
	public String getExperienceJobCode() {
		return experienceJobCode;
	}
	public void setExperienceJobCode(String experienceJobCode) {
		this.experienceJobCode = experienceJobCode;
	}
	public Boolean getIsFindJob() {
		return isFindJob;
	}
	public void setIsFindJob(Boolean isFindJob) {
		this.isFindJob = isFindJob;
	}
	public Boolean getIsTakeMail() {
		return isTakeMail;
	}
	public void setIsTakeMail(Boolean isTakeMail) {
		this.isTakeMail = isTakeMail;
	}
	public byte[] getFile() {
		return file;
	}
	public void setFile(byte[] file) {
		this.file = file;
	}
	public List<Integer> getSkill() {
		return skills;
	}
	public void setSkill(List<Integer> skills) {
		this.skills = skills;
	}
    
    
    
}
