package com.doan.AppTuyenDung.DTO.Response;


import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@Builder
public class postDetailResponse implements Serializable  {
    private Integer id;
    private String name;
    private String descriptionHTML;
    private String descriptionMarkdown;
    private String categoryJobCode;
    private String addressCode;
    private String salaryJobCode;
    private Integer amount;
    private String categoryJoblevelCode;
    private String categoryWorktypeCode;
    private String experienceJobCode;
    private String genderPostCode;
    private String isHot;

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescriptionHTML() {
        return this.descriptionHTML;
    }

    public void setDescriptionHTML(String descriptionHTML) {
        this.descriptionHTML = descriptionHTML;
    }

    public String getDescriptionMarkdown() {
        return this.descriptionMarkdown;
    }

    public void setDescriptionMarkdown(String descriptionMarkdown) {
        this.descriptionMarkdown = descriptionMarkdown;
    }

    public String getCategoryJobCode() {
        return this.categoryJobCode;
    }

    public void setCategoryJobCode(String categoryJobCode) {
        this.categoryJobCode = categoryJobCode;
    }

    public String getAddressCode() {
        return this.addressCode;
    }

    public void setAddressCode(String addressCode) {
        this.addressCode = addressCode;
    }

    public String getSalaryJobCode() {
        return this.salaryJobCode;
    }

    public void setSalaryJobCode(String salaryJobCode) {
        this.salaryJobCode = salaryJobCode;
    }

    public Integer getAmount() {
        return this.amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public String getCategoryJoblevelCode() {
        return this.categoryJoblevelCode;
    }

    public void setCategoryJoblevelCode(String categoryJoblevelCode) {
        this.categoryJoblevelCode = categoryJoblevelCode;
    }

    public String getCategoryWorktypeCode() {
        return this.categoryWorktypeCode;
    }

    public void setCategoryWorktypeCode(String categoryWorktypeCode) {
        this.categoryWorktypeCode = categoryWorktypeCode;
    }

    public String getExperienceJobCode() {
        return this.experienceJobCode;
    }

    public void setExperienceJobCode(String experienceJobCode) {
        this.experienceJobCode = experienceJobCode;
    }

    public String getGenderPostCode() {
        return this.genderPostCode;
    }

    public void setGenderPostCode(String genderPostCode) {
        this.genderPostCode = genderPostCode;
    }

    public String getIsHot() {
        return this.isHot;
    }

    public void setIsHot(String isHot) {
        this.isHot = isHot;
    }
    public postDetailResponse(Integer id, String name, String descriptionHTML, String descriptionMarkdown,
            String categoryJobCode, String addressCode, String salaryJobCode, Integer amount,
            String categoryJoblevelCode, String categoryWorktypeCode, String experienceJobCode, String genderPostCode,
            String isHot) {
        this.id = id;
        this.name = name;
        this.descriptionHTML = descriptionHTML;
        this.descriptionMarkdown = descriptionMarkdown;
        this.categoryJobCode = categoryJobCode;
        this.addressCode = addressCode;
        this.salaryJobCode = salaryJobCode;
        this.amount = amount;
        this.categoryJoblevelCode = categoryJoblevelCode;
        this.categoryWorktypeCode = categoryWorktypeCode;
        this.experienceJobCode = experienceJobCode;
        this.genderPostCode = genderPostCode;
        this.isHot = isHot;
    }
}
