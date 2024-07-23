package com.doan.AppTuyenDung.entity;

import java.util.Collection;
import java.util.Date;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "User")
public class User implements UserDetails {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Integer user_id;
	public String username;
	public String password;
	public String email;
	public String phone_number;	
	public String full_name;
	public String user_type;
	public Date created_ad;
	public String avatar;
	public String martial_status;
	public String city;
	public String district;
	public String location;
	

	@JsonCreator
    public User(
        @JsonProperty("user_id") Integer user_id,
        @JsonProperty("username") String username,
        @JsonProperty("password") String password,
        @JsonProperty("email") String email,
        @JsonProperty("phone_number") String phone_number,
        @JsonProperty("full_name") String full_name,
        @JsonProperty("user_type") String user_type,
        @JsonProperty("created_ad") Date created_ad,
        @JsonProperty("avatar") String avatar,
        @JsonProperty("martial_status") String martial_status,
        @JsonProperty("city") String city,
        @JsonProperty("district") String district,
        @JsonProperty("location") String location
    ) {
        this.user_id = user_id;
        this.username = username;
        this.password = password;
        this.email = email;
        this.phone_number = phone_number;
        this.full_name = full_name;
        this.user_type = user_type;
        this.created_ad = created_ad;
        this.avatar = avatar;
        this.martial_status = martial_status;
        this.city = city;
        this.district = district;
        this.location = location;
    }
	public User() {
	}
	// public User(Integer user_id, String username, String password, String email, String phone_number, String full_name,
	// 		String user_type, Date created_ad, String avatar, String martial_status, String city, String district,
	// 		String location) {
	// 	this.user_id = user_id;
	// 	this.username = username;
	// 	this.password = password;
	// 	this.email = email;
	// 	this.phone_number = phone_number;
	// 	this.full_name = full_name;
	// 	this.user_type = user_type;
	// 	this.created_ad = created_ad;
	// 	this.avatar = avatar;
	// 	this.martial_status = martial_status;
	// 	this.city = city;
	// 	this.district = district;
	// 	this.location = location;
	// }
	public Integer getUser_id() {
		return user_id;
	}
	public void setUser_id(Integer user_id) {
		this.user_id = user_id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPhone_number() {
		return phone_number;
	}
	public void setPhone_number(String phone_number) {
		this.phone_number = phone_number;
	}
	public String getFull_name() {
		return full_name;
	}
	public void setFull_name(String full_name) {
		this.full_name = full_name;
	}
	public String getUser_type() {
		return user_type;
	}
	public void setUser_type(String user_type) {
		this.user_type = user_type;
	}
	public Date getCreated_ad() {
		return created_ad;
	}
	public void setCreated_ad(Date created_ad) {
		this.created_ad = created_ad;
	}
	public String getAvatar() {
		return avatar;
	}
	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}
	public String getMartial_status() {
		return martial_status;
	}
	public void setMartial_status(String martial_status) {
		this.martial_status = martial_status;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getDistrict() {
		return district;
	}
	public void setDistrict(String district) {
		this.district = district;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;

	}
	@JsonIgnore
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		 return List.of(new SimpleGrantedAuthority(user_type));
	}
	
}
