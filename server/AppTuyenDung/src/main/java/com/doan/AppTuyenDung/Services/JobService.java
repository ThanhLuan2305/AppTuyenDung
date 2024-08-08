package com.doan.AppTuyenDung.Services;

import java.io.IOException;
import java.sql.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

// import com.doan.AppTuyenDung.Repositories.CompanyRepository;
// import com.doan.AppTuyenDung.Repositories.Job__ModelRepository;
// import com.doan.AppTuyenDung.Repositories.UserRepository;

// import com.doan.AppTuyenDung.entity.Job_Model;
import lombok.AllArgsConstructor;
import software.amazon.awssdk.services.s3.S3Client;

import com.doan.AppTuyenDung.AWS.S3Util;
import com.doan.AppTuyenDung.DTO.CreateCompanyRequest;
import com.doan.AppTuyenDung.DTO.CreateCompanyResponse;
import com.doan.AppTuyenDung.Entity.Account;
import com.doan.AppTuyenDung.Entity.Company;
import com.doan.AppTuyenDung.Entity.User;
import com.doan.AppTuyenDung.Exception.ResourceNotFoundException;
import com.doan.AppTuyenDung.InterfaceServices.IjobModelServices;
import com.doan.AppTuyenDung.Repositories.AccountRepository;
import com.doan.AppTuyenDung.Repositories.CompanyRepository;
import com.doan.AppTuyenDung.Repositories.UserRepository;
import com.doan.AppTuyenDung.Services.AWS_S3.S3Service;


@Service
@AllArgsConstructor
public class JobService implements IjobModelServices{

    @Autowired
    private CompanyRepository companyRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private S3Service s3Service;

    public CreateCompanyResponse createCompany(CreateCompanyRequest request) {
        CreateCompanyResponse response = new CreateCompanyResponse();

        try {
            if (request.getName() == null || request.getPhonenumber() == null || request.getAddress() == null ||
                request.getDescriptionHTML() == null || request.getDescriptionMarkdown() == null ||
                request.getAmountEmployer() == null || request.getUserId() == null) {
                response.setErrCode(1);
                response.setErrMessage("Missing required parameters !");
                return response;
            }

            // Check if company name already exists
            if (companyRepository.existsByName(request.getName())) {
                response.setErrCode(2);
                response.setErrMessage("Tên công ty đã tồn tại");
                return response;
            }

            // Upload images to S3
            String thumbnailUrl = "";
            String coverImageUrl = "";
            if (request.getThumbnail() != null && !request.getThumbnail().isEmpty()) {
                thumbnailUrl = uploadImageToS3(request.getThumbnail());
            }
            if (request.getCoverimage() != null && !request.getCoverimage().isEmpty()) {
                coverImageUrl = uploadImageToS3(request.getCoverimage());
            }

            // Create company
            Company company = new Company();
            company.setName(request.getName());
            company.setThumbnail(thumbnailUrl);
            company.setCoverImage(coverImageUrl);
            company.setDescriptionHTML(request.getDescriptionHTML());
            company.setDescriptionMarkdown(request.getDescriptionMarkdown());
            company.setWebsite(request.getWebsite());
            company.setAddress(request.getAddress());
            company.setPhonenumber(request.getPhonenumber());
            company.setAmountEmployer(request.getAmountEmployer());
            company.setTaxnumber(request.getTaxnumber());
            company.setStatusCode("S1");
            company.setUser(userRepository.findById(request.getUserId()).orElse(null));
            company.setCensorCode(request.getFile() != null ? "CS3" : "CS2");
            company.setFile(request.getFile());
            
            Company savedCompany = companyRepository.save(company);

            User user = userRepository.findById(request.getUserId()).orElse(null);
            Account account = accountRepository.findByUserId(request.getUserId());

            if (user != null && account != null) {
                user.setCompanyId(savedCompany.getId());
                userRepository.save(user);
                account.setRoleCode("COMPANY");
                accountRepository.save(account);

                response.setErrCode(0);
                response.setErrMessage("Đã tạo công ty thành công");
                response.setCompanyId(savedCompany.getId());
            } else {
                response.setErrCode(2);
                response.setErrMessage("Không tìm thấy người dùng");
            }

        } catch (Exception e) {
            response.setErrCode(3);
            response.setErrMessage("Đã xảy ra lỗi: " + e.getMessage());
        }

        return response;
    }

    private String uploadImageToS3(MultipartFile file) throws IOException {
        String fileName = file.getOriginalFilename();
        String newFileName = S3Util.urlFolder + fileName;
        S3Util.uploadFile(newFileName, file.getInputStream());

        return "https://s3-" + S3Util.region + ".amazonaws.com/" + S3Util.bucketName + "/" + newFileName;
    }


    // public ResponseEntity<String> uploadImageToS3(MultipartFile multipart, Integer companyId) {

    //     String fileName = multipart.getOriginalFilename();
    //     String message;

    //     try {
    //         String newFileName = S3Util.urlFolder + "default-images-demo" + fileName.substring(fileName.lastIndexOf('.'));
            
    //         //upload 
    //         S3Util.uploadFile(newFileName, multipart.getInputStream());

    //         // get  URL của file đã upload
    //         String fileUrl = "https://s3-" + S3Util.region + ".amazonaws.com/" + S3Util.bucketName + "/" + newFileName;
            
    //         Optional<Company> optionalCompany = companyRepository.findById(companyId);
    //         if (!optionalCompany.isPresent()) {
    //             return new ResponseEntity<>("Company not found", HttpStatus.NOT_FOUND);
    //         }
    //         Company company = optionalCompany.get();
        
    //         // Cập nhật thông tin file URL vào model Company
    //         company.setFile(fileUrl);
    //         companyRepository.save(company);

    //         message = "Your file has been uploaded successfully. File URL: " + fileUrl;
    //         System.out.println(message);
    //         return new ResponseEntity<>(message, HttpStatus.OK);

    //     } catch (IOException ex) {
    //         message = "Error uploading file: " + ex.getMessage();
    //         System.out.println(message);
    //         return new ResponseEntity<>(message, HttpStatus.INTERNAL_SERVER_ERROR);
    //     }

    //     // String fileName = "path/to/image"; // Get actual file name or path
    //     // s3Service.putS3Object(S3Client.builder().build(), "bks3-images-company", fileName, imagePath);
    //     // return "https://s3.amazonaws.com/imagecoms3/" + fileName; // Construct URL based on your S3 bucket
    // }

//     @Autowired
//     public Job__ModelRepository jobRepository;

//     @Autowired
//     public UserRepository  userRepository;

//     @Autowired 
//     public CompanyRepository companyRepository;


//     public List<Job_Model> getAllJobModel(){
// 		System.out.println("Get all Job in services");
// 		List<Job_Model> jobs = jobRepository.findAll();
// 		return jobs;
// 	}

//     public Job_Model getJob_ModelById(Long job_id)
//     {
//         Job_Model jobmodel = jobRepository.findById(job_id).orElseThrow(
//             ()-> new ResourceNotFoundException("Not found: "+job_id)
//         );
//         return jobmodel;
//     }

//     public Job_Model addNewJob(Job_Model jobs) {
//         jobs.setExpiry_date(new Date(System.currentTimeMillis()));
//         jobs.setPosted_date(new Date(System.currentTimeMillis()));
// 		return jobRepository.save(jobs);
//     }

    
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
