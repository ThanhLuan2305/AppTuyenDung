package com.doan.AppTuyenDung.DTO.Response;

import java.util.Set;

import com.doan.AppTuyenDung.entity.Account;
import com.doan.AppTuyenDung.entity.CodeGender;
import com.doan.AppTuyenDung.entity.Company;
import com.doan.AppTuyenDung.entity.Cv;
import com.doan.AppTuyenDung.entity.Note;
import com.doan.AppTuyenDung.entity.OrderPackage;
import com.doan.AppTuyenDung.entity.OrderPackageCv;
import com.doan.AppTuyenDung.entity.UserSetting;
import com.doan.AppTuyenDung.entity.UserSkill;

import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;

public class UserResponse {
	private Integer id;
    private String firstName;
    private String lastName;
    private String email;
    private String address;
    private String genderCode;
    private String image;
    private String dob;
    private Integer companyId;
    private String phoneNumber;
    private Company company;
    private Set<Cv> cvs;
    private Set<Note> notes;
    private Set<OrderPackageCv> orderPackageCvs;
    private Set<OrderPackage> orderPackages;
    private Set<UserSkill> userSkills;
    private UserSetting userSetting;
    
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

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

	public Integer getCompanyId() {
		return companyId;
	}

	public void setCompanyId(Integer companyId) {
		this.companyId = companyId;
	}

	public String getphoneNumber() {
		return phoneNumber;
	}

	public void setphoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public Company getCompany() {
		return company;
	}

	public void setCompany(Company company) {
		this.company = company;
	}

	public Set<Cv> getCvs() {
		return cvs;
	}

	public void setCvs(Set<Cv> cvs) {
		this.cvs = cvs;
	}

	public Set<Note> getNotes() {
		return notes;
	}

	public void setNotes(Set<Note> notes) {
		this.notes = notes;
	}

	public Set<OrderPackageCv> getOrderPackageCvs() {
		return orderPackageCvs;
	}

	public void setOrderPackageCvs(Set<OrderPackageCv> orderPackageCvs) {
		this.orderPackageCvs = orderPackageCvs;
	}

	public Set<OrderPackage> getOrderPackages() {
		return orderPackages;
	}

	public void setOrderPackages(Set<OrderPackage> orderPackages) {
		this.orderPackages = orderPackages;
	}

	public Set<UserSkill> getUserSkills() {
		return userSkills;
	}

	public void setUserSkills(Set<UserSkill> userSkills) {
		this.userSkills = userSkills;
	}

	public UserSetting getUserSetting() {
		return userSetting;
	}

	public void setUserSetting(UserSetting userSetting) {
		this.userSetting = userSetting;
	}
}
