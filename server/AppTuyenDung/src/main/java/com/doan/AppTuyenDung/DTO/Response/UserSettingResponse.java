package com.doan.AppTuyenDung.DTO.Response;

import lombok.Data;


public class UserSettingResponse {
	private int id;
    private String categoryJobCode;
    private String addressCode;
    private String salaryJobCode;
    private String experienceJobCode;
    private Boolean isTakeMail;
    private Boolean isFindJob;
    private String file;
    private int userId;
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getCategoryJobCode() {
        return categoryJobCode;
    }
    public void setCategoryJobCode(String categoryJobCode) {
        this.categoryJobCode = categoryJobCode;
    }
    public String getAddressCode() {
        return addressCode;
    }
    public void setAddressCode(String addressCode) {
        this.addressCode = addressCode;
    }
    public String getSalaryJobCode() {
        return salaryJobCode;
    }
    public void setSalaryJobCode(String salaryJobCode) {
        this.salaryJobCode = salaryJobCode;
    }
    public String getExperienceJobCode() {
        return experienceJobCode;
    }
    public void setExperienceJobCode(String experienceJobCode) {
        this.experienceJobCode = experienceJobCode;
    }
    public Boolean getIsTakeMail() {
        return isTakeMail;
    }
    public void setIsTakeMail(Boolean isTakeMail) {
        this.isTakeMail = isTakeMail;
    }
    public Boolean getIsFindJob() {
        return isFindJob;
    }
    public void setIsFindJob(Boolean isFindJob) {
        this.isFindJob = isFindJob;
    }
    public String getFile() {
        return file;
    }
    public void setFile(String file) {
        this.file = file;
    }
    public int getUserId() {
        return userId;
    }
    public void setUserId(int userId) {
        this.userId = userId;
    }
}