package com.doan.AppTuyenDung.Controller;

import java.io.IOException;

import java.sql.SQLException;
import java.util.List;

import javax.sql.rowset.serial.SerialException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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

    @PostMapping("/add-new-job")
    public ResponseEntity<?> addNewJobSv(@RequestBody Job_Model jobs ){
        try {
            System.out.println("Controller active");
            
			return new ResponseEntity<>(jobService.addNewJob(jobs), HttpStatus.OK);
		} catch (Exception e) {
			System.out.println("Exception: " + e.getMessage());
			return new ResponseEntity<>("ERROR", HttpStatus.BAD_REQUEST);
		}
    }
    // @PostMapping("/print-job")
    // public String postMethodName(@RequestBody Job_Model jobs) {
    //     System.out.println("print :"+jobs);
    //     return "Success";
    // }
    

    // @PostMapping("/spring/hello/{id}")
    // public String showHello(@PathVariable(value="id") String id,
    //                     @RequestParam(value="param1", required=true) String parameter1,
    //                     @RequestParam(value="param2", required=false) String parameter2) {
    //  // gia tri cua id duoc su dung o day
    //     System.out.println("ID : " + id);
    //     System.out.println("Param 1 : " + parameter1);
    //     System.out.println("Param 2 : " + parameter2);
    //     return "hello";
    // }
    
}
