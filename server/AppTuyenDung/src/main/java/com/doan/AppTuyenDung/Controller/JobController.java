package com.doan.AppTuyenDung.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.doan.AppTuyenDung.Repositories.Job__ModelRepository;
import com.doan.AppTuyenDung.Services.JobService;
import com.doan.AppTuyenDung.entity.Job_Model;

import lombok.RequiredArgsConstructor;



@RestController
@RequestMapping("/api/job")
@RequiredArgsConstructor
public class JobController {
    @Autowired
    public JobService jobService;
    
    @Autowired
    public Job__ModelRepository jobRepository;


    @GetMapping("")
	public ResponseEntity<List<Job_Model>> getAllJobModel(){

		System.out.println("Get all job in controller");
			List<Job_Model> jobs = jobService.getAllJobModel();
			return new ResponseEntity<List<Job_Model>>(jobs, HttpStatus.OK);
	}

    @GetMapping("/getJob/{jobId}")
    public ResponseEntity<Job_Model> getJobModelbyId(@PathVariable("jobId") Long jobId) {
        Job_Model job = jobService.getJob_ModelById(jobId);
        return ResponseEntity.ok(job);
    }
    
}
