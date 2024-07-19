package com.doan.AppTuyenDung.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;


@Data
@Entity
@Table(name = "Working_Form")
public class Working_Form {
    @Id
    @Column(name="workingForm_id")
    public String workingForm_id;
    public String name_workingForm;

    public String getWorkingForm_id() {
        return this.workingForm_id;
    }

    public void setWorkingForm_id(String workingForm_id) {
        this.workingForm_id = workingForm_id;
    }

    public String getName_workingForm() {
        return this.name_workingForm;
    }

    public void setName_workingForm(String name_workingForm) {
        this.name_workingForm = name_workingForm;
    }
}
