package com.doan.AppTuyenDung.entity;

import java.sql.Date;


import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "Job_Model")
public class Job_Model {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long job_id;
    public String job_title;
    public String career;
    public String job_description;
    public String location;
    public int quanlity;
    public Long salary_min;
    public Long salary_max;
    public String city;
    public String district;
    public Date posted_date;
    public Date expiry_date;
    public Boolean urgent;

    @ManyToOne
    @JoinColumn(name = "company_id")
    public Company company;

    @ManyToOne
    @JoinColumn(name = "user_id")
    public User user;

    @ManyToOne
    @JoinColumn(name = "position_id")
    public Position_In_Job position;

    @ManyToOne
    @JoinColumn(name = "workingForm_id", nullable = false)
    public Working_Form workingForm;

    @ManyToOne
    @JoinColumn(name = "employment_id")
    public Employment_Type employmentType;

    public Job_Model() {
    }
    @JsonCreator
    public Job_Model(
        @JsonProperty("job_id") Long job_id,
        @JsonProperty("job_title") String job_title,
        @JsonProperty("career") String career,
        @JsonProperty("job_description") String job_description,
        @JsonProperty("location") String location,
        @JsonProperty("quanlity") int quanlity,
        @JsonProperty("salary_min") Long salary_min,
        @JsonProperty("salary_max") Long salary_max,
        @JsonProperty("city") String city,
        @JsonProperty("district") String district,
        @JsonProperty("posted_date") Date posted_date,
        @JsonProperty("expiry_date") Date expiry_date,
        @JsonProperty("urgent") Boolean urgent,
        @JsonProperty("company") Company company,
        @JsonProperty("user") User user,
        @JsonProperty("position") Position_In_Job position,
        @JsonProperty("workingForm") Working_Form workingForm,
        @JsonProperty("employmentType") Employment_Type employmentType
    ) {
        this.job_id = job_id;
        this.job_title = job_title;
        this.career = career;
        this.job_description = job_description;
        this.location = location;
        this.quanlity = quanlity;
        this.salary_min = salary_min;
        this.salary_max = salary_max;
        this.city = city;
        this.district = district;
        this.posted_date = posted_date;
        this.expiry_date = expiry_date;
        this.urgent = urgent;
        this.company = company;
        this.user = user;
        this.position = position;
        this.workingForm = workingForm;
        this.employmentType = employmentType;
    }
    // public Job_Model(String job_title, String career, String job_description, String location, int quanlity,
    //         Long salary_min, Long salary_max, String city, String district, Date posted_date, Date expiry_date,
    //         Boolean urgent, Company company, User user, Position_In_Job position, Working_Form workingForm,
    //         Employment_Type employmentType) {
    //     this.job_title = job_title;
    //     this.career = career;
    //     this.job_description = job_description;
    //     this.location = location;
    //     this.quanlity = quanlity;
    //     this.salary_min = salary_min;
    //     this.salary_max = salary_max;
    //     this.city = city;
    //     this.district = district;
    //     this.posted_date = posted_date;
    //     this.expiry_date = expiry_date;
    //     this.urgent = urgent;
    //     this.company = company;
    //     this.user = user;
    //     this.position = position;
    //     this.workingForm = workingForm;
    //     this.employmentType = employmentType;
    // }

    // public Job_Model(String job_title, String career, String job_description, String location, int quanlity,
    //         Long salary_min, Long salary_max, String city, String district, Date posted_date, Date expiry_date,
    //         Boolean urgent, Position_In_Job position, Working_Form workingForm, Employment_Type employmentType) {
    //     this.job_title = job_title;
    //     this.career = career;
    //     this.job_description = job_description;
    //     this.location = location;
    //     this.quanlity = quanlity;
    //     this.salary_min = salary_min;
    //     this.salary_max = salary_max;
    //     this.city = city;
    //     this.district = district;
    //     this.posted_date = posted_date;
    //     this.expiry_date = expiry_date;
    //     this.urgent = urgent;
    //     this.position = position;
    //     this.workingForm = workingForm;
    //     this.employmentType = employmentType;
    // }

    public Long getJob_id() {
        return this.job_id;
    }

    public void setJob_id(Long job_id) {
        this.job_id = job_id;
    }

    public String getJob_title() {
        return this.job_title;
    }

    public void setJob_title(String job_title) {
        this.job_title = job_title;
    }

    public String getCareer() {
        return this.career;
    }

    public void setCareer(String career) {
        this.career = career;
    }

    public String getJob_description() {
        return this.job_description;
    }

    public void setJob_description(String job_description) {
        this.job_description = job_description;
    }

    public String getLocation() {
        return this.location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public int getQuanlity() {
        return this.quanlity;
    }

    public void setQuanlity(int quanlity) {
        this.quanlity = quanlity;
    }

    public Long getSalary_min() {
        return this.salary_min;
    }

    public void setSalary_min(Long salary_min) {
        this.salary_min = salary_min;
    }

    public Long getSalary_max() {
        return this.salary_max;
    }

    public void setSalary_max(Long salary_max) {
        this.salary_max = salary_max;
    }

    public String getCity() {
        return this.city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getDistrict() {
        return this.district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public Date getPosted_date() {
        return this.posted_date;
    }

    public void setPosted_date(Date posted_date) {
        this.posted_date = posted_date;
    }

    public Date getExpiry_date() {
        return this.expiry_date;
    }

    public void setExpiry_date(Date expiry_date) {
        this.expiry_date = expiry_date;
    }

    public Boolean isUrgent() {
        return this.urgent;
    }

    public Boolean getUrgent() {
        return this.urgent;
    }

    public void setUrgent(Boolean urgent) {
        this.urgent = urgent;
    }

    public Company getCompany() {
        return this.company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public User getUser() {
        return this.user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Position_In_Job getPosition() {
        return this.position;
    }

    public void setPosition(Position_In_Job position) {
        this.position = position;
    }

    public Working_Form getWorkingForm() {
        return this.workingForm;
    }

    public void setWorkingForm(Working_Form workingForm) {
        this.workingForm = workingForm;
    }

    public Employment_Type getEmploymentType() {
        return this.employmentType;
    }

    public void setEmploymentType(Employment_Type employmentType) {
        this.employmentType = employmentType;
    }
    @Override
    public String toString() {
        return "Job_Model{" +
                "job_id=" + job_id +
                ", job_title='" + job_title + '\'' +
                ", career='" + career + '\'' +
                ", job_description='" + job_description + '\'' +
                ", location='" + location + '\'' +
                ", quanlity=" + quanlity +
                ", salary_min=" + salary_min +
                ", salary_max=" + salary_max +
                ", city='" + city + '\'' +
                ", district='" + district + '\'' +
                ", posted_date=" + posted_date +
                ", expiry_date=" + expiry_date +
                ", urgent=" + urgent +
                ", company=" + company +
                ", user=" + user +
                ", position=" + position +
                ", workingForm=" + workingForm +
                ", employmentType=" + employmentType +
                '}';
    }
}

