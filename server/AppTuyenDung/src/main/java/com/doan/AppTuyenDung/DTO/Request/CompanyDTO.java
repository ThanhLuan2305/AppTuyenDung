package com.doan.AppTuyenDung.DTO.Request;

import java.util.Date;

import com.doan.AppTuyenDung.entity.CodeCensorstatus;
import com.doan.AppTuyenDung.entity.CodeStatus;
import com.doan.AppTuyenDung.entity.User;

public class CompanyDTO {
	 private String name;
	 private String thumbnail;
	 private String coverImage;
	 private String descriptionHTML;
	 private String descriptionMarkdown;
	 private String website;
	 private String address;
	 private String phonenumber;
	 private Integer amountEmployer;
	 private String taxnumber;
	 private String statusCode;
	 private String file;
	 private Integer allowPost;
	 private Integer allowHotPost;
	 private Integer allowCvFree;
	 private Integer allowCV;
	 private String censorCode;
	 private int idUser;
	 private Date createdAt;
	 private Date updatedAt;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getThumbnail() {
		return thumbnail;
	}
	public void setThumbnail(String thumbnail) {
		this.thumbnail = thumbnail;
	}
	
	public String getCoverImage() {
		return coverImage;
	}
	public void setCoverImage(String coverImage) {
		this.coverImage = coverImage;
	}
	public String getDescriptionHTML() {
		return descriptionHTML;
	}
	public void setDescriptionHTML(String descriptionHTML) {
		this.descriptionHTML = descriptionHTML;
	}
	public String getDescriptionMarkdown() {
		return descriptionMarkdown;
	}
	public void setDescriptionMarkdown(String descriptionMarkdown) {
		this.descriptionMarkdown = descriptionMarkdown;
	}
	public String getWebsite() {
		return website;
	}
	public void setWebsite(String website) {
		this.website = website;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getPhonenumber() {
		return phonenumber;
	}
	public void setPhonenumber(String phonenumber) {
		this.phonenumber = phonenumber;
	}
	public Integer getAmountEmployer() {
		return amountEmployer;
	}
	public void setAmountEmployer(Integer amountEmployer) {
		this.amountEmployer = amountEmployer;
	}
	public String getTaxnumber() {
		return taxnumber;
	}
	public void setTaxnumber(String taxnumber) {
		this.taxnumber = taxnumber;
	}
	public String getStatusCode() {
		return statusCode;
	}
	public void setStatusCode(String statusCode) {
		this.statusCode = statusCode;
	}
	public String getFile() {
		return file;
	}
	public void setFile(String file) {
		this.file = file;
	}
	public Integer getAllowPost() {
		return allowPost;
	}
	public void setAllowPost(Integer allowPost) {
		this.allowPost = allowPost;
	}
	public Integer getAllowHotPost() {
		return allowHotPost;
	}
	public void setAllowHotPost(Integer allowHotPost) {
		this.allowHotPost = allowHotPost;
	}
	public Integer getAllowCvFree() {
		return allowCvFree;
	}
	public void setAllowCvFree(Integer allowCvFree) {
		this.allowCvFree = allowCvFree;
	}
	public Integer getAllowCV() {
		return allowCV;
	}
	public void setAllowCV(Integer allowCV) {
		this.allowCV = allowCV;
	}
	public String getCensorCode() {
		return censorCode;
	}
	public void setCensorCode(String censorCode) {
		this.censorCode = censorCode;
	}
	public int getIdUser() {
		return idUser;
	}
	public void setIdUser(int idUser) {
		this.idUser = idUser;
	}
	public Date getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}
	public Date getUpdatedAt() {
		return updatedAt;
	}
	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}
	 
}
