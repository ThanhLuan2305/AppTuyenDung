package com.doan.AppTuyenDung.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;


@Data
@Entity
@Table(name = "Position_In_Job")
public class Position_In_Job {
    @Id
    @Column(name = "position_id")
    public Integer position_id;
    public String name_position;

    public Integer getPosition_id() {
        return this.position_id;
    }

    public void setPosition_id(Integer position_id) {
        this.position_id = position_id;
    }

    public String getName_position() {
        return this.name_position;
    }

    public void setName_position(String name_position) {
        this.name_position = name_position;
    }
}
