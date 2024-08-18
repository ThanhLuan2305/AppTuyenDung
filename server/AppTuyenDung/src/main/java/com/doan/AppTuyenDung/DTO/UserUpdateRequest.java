package com.doan.AppTuyenDung.DTO;

import com.doan.AppTuyenDung.entity.CodeGender;

import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

public class UserUpdateRequest {
	private String firstName;
    private String lastName;
    private String email;
    private String address;
    private String genderCode;
    private String image;
    private String dob;
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getGenderCode() {
		return genderCode;
	}
	public void setGenderCode(String genderCode) {
		this.genderCode = genderCode;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public String getDob() {
		return dob;
	}
	public void setDob(String dob) {
		this.dob = dob;
	}
    
}
