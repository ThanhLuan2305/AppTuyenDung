package com.doan.AppTuyenDung.Services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.doan.AppTuyenDung.Repositories.Job__ModelRepository;
import com.doan.AppTuyenDung.entity.Job_Model;

import lombok.AllArgsConstructor;
import com.doan.AppTuyenDung.Exception.ResourceNotFoundException;
import com.doan.AppTuyenDung.InterfaceServices.IjobModelServices;

@Service

@AllArgsConstructor
public class JobService implements IjobModelServices{
    @Autowired
    public Job__ModelRepository jobRepository;

    public List<Job_Model> getAllJobModel(){
		System.out.println("Get all Job in services");
		List<Job_Model> jobs = jobRepository.findAll();
		return jobs;
	}

    public Job_Model getJob_ModelById(Long job_id)
    {
        Job_Model jobmodel = jobRepository.findById(job_id).orElseThrow(
            ()-> new ResourceNotFoundException("Not found: "+job_id)
        );
        return jobmodel;
    }
    
    
}
