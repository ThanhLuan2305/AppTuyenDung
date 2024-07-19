package com.doan.AppTuyenDung.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;


@Data
@Entity
@Table(name = "Employment_Type")
public class Employment_Type {
    @Id
    @Column(name="employment_id")
    public String employment_id;
    public String employment_name;

    public String getEmployment_id() {
        return this.employment_id;
    }

    public void setEmployment_id(String employment_id) {
        this.employment_id = employment_id;
    }

    public String getEmployment_name() {
        return this.employment_name;
    }

    public void setEmployment_name(String employment_name) {
        this.employment_name = employment_name;
    }
}
