package com.doan.AppTuyenDung.Services;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.doan.AppTuyenDung.Repositories.CompanyRepository;
import com.doan.AppTuyenDung.Repositories.Job__ModelRepository;
import com.doan.AppTuyenDung.Repositories.UserRepository;
import com.doan.AppTuyenDung.entity.Company;
import com.doan.AppTuyenDung.entity.Employment_Type;
import com.doan.AppTuyenDung.entity.Job_Model;
import com.doan.AppTuyenDung.entity.Position_In_Job;
import com.doan.AppTuyenDung.entity.User;
import com.doan.AppTuyenDung.entity.Working_Form;

import lombok.AllArgsConstructor;
import com.doan.AppTuyenDung.Exception.ResourceNotFoundException;
import com.doan.AppTuyenDung.InterfaceServices.IjobModelServices;


@Service

@AllArgsConstructor
public class JobService implements IjobModelServices{
    @Autowired
    public Job__ModelRepository jobRepository;

    @Autowired
    public UserRepository  userRepository;

    @Autowired 
    public CompanyRepository companyRepository;


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

    public Job_Model addNewJob(Job_Model jobs) {
        jobs.setExpiry_date(new Date(System.currentTimeMillis()));
        jobs.setPosted_date(new Date(System.currentTimeMillis()));
		return jobRepository.save(jobs);
    }

    
    // public Job_Model addNewJob(Integer iduser,String job_title, String career, String job_description, String location,
    //         int quanlity, Long salary_min, Long salary_max, String city, String district, Date posted_date,
    //         Date expiry_date, Boolean urgent, Company company, Object object, Position_In_Job position,
    //         Working_Form workingForm, Employment_Type employmentType) {
    //     Job_Model jobmodel = new Job_Model();
    //     jobmodel.setJob_title(job_title);
    //     jobmodel.setCareer(career);
    //     jobmodel.setJob_description(job_description);
    //     jobmodel.setLocation(location);
    //     jobmodel.setQuanlity(quanlity);
    //     jobmodel.setSalary_min(salary_min);
    //     jobmodel.setSalary_max(salary_max);
    //     jobmodel.setCity(city);
    //     jobmodel.setDistrict(district);
    //     jobmodel.setPosted_date(posted_date);
    //     jobmodel.setExpiry_date(expiry_date);
    //     jobmodel.setCompany(company);
    //     jobmodel.getUser().setUser_id(iduser);        
    //     jobmodel.setPosition(position);
    //     jobmodel.setWorkingForm(workingForm);
    //     jobmodel.setEmploymentType(employmentType);
    //     return jobRepository.save(jobmodel);
    //}

    // public String addNewJob(Integer id_user, String job_title, String career, String job_description, String location,
    //         int quanlity, Long salary_min, Long salary_max, String city, String district, Date posted_date,
    //         Date expiry_date, Boolean urgent, Company company, Object setUser_id, Position_In_Job position,
    //         Working_Form workingForm, Employment_Type employmentType) {
    //     // TODO Auto-generated method stub
    //     throw new UnsupportedOperationException("Unimplemented method 'addNewJob'");
    // }

    // public Object addNewJob(Integer id_user, Integer id_company, String job_title, String career,
    //         String job_description, String location, int quanlity, Long salary_min, Long salary_max, String city,
    //         String district, Date posted_date, Date expiry_date, Boolean urgent, Object setCompany_id,
    //         Object setUser_id, Position_In_Job position, Working_Form workingForm, Employment_Type employmentType) {
    //     // TODO Auto-generated method stub
    //     throw new UnsupportedOperationException("Unimplemented method 'addNewJob'");
    // }

    
    
    
}
