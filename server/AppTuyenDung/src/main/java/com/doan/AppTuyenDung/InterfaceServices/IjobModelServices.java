package com.doan.AppTuyenDung.InterfaceServices;

import java.util.List;

import com.doan.AppTuyenDung.entity.Job_Model;

public interface IjobModelServices {
    
    List<Job_Model> getAllJobModel();

    Job_Model getJob_ModelById(Long job_id);
}
