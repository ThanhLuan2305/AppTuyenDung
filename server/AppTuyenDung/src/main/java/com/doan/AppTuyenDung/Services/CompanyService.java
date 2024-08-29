package com.doan.AppTuyenDung.Services;

import java.nio.charset.StandardCharsets;

import java.util.ArrayList;
import java.util.Base64;
import java.util.Date;
import java.util.List;
import java.util.Optional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.doan.AppTuyenDung.DTO.Request.CompanyDTO;
import com.doan.AppTuyenDung.DTO.Response.CompanyResponse;
import com.doan.AppTuyenDung.Exception.AppException;
import com.doan.AppTuyenDung.Exception.ErrorCode;
import com.doan.AppTuyenDung.Repositories.CodeCensorstatusRepository;
import com.doan.AppTuyenDung.Repositories.AllCode.CodeStatusRepository;
import com.doan.AppTuyenDung.Repositories.CompanyRepository;
import com.doan.AppTuyenDung.Repositories.UserRepository;
import com.doan.AppTuyenDung.entity.CodeStatus;
import com.doan.AppTuyenDung.entity.CodeCensorstatus;
import com.doan.AppTuyenDung.entity.Company;
import com.doan.AppTuyenDung.entity.User;

import jakarta.persistence.EntityNotFoundException;
import java.time.Instant;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import com.doan.AppTuyenDung.DTO.CloudinaryResponse;
import com.doan.AppTuyenDung.Repositories.AccountRepository;
import com.doan.AppTuyenDung.Repositories.CodeRuleRepository;
import com.doan.AppTuyenDung.Repositories.AllCode.CodeCensorStatusRepository;
import com.doan.AppTuyenDung.Repositories.AllCode.CodeStatusRepository;
import com.doan.AppTuyenDung.entity.Account;
import com.doan.AppTuyenDung.entity.CodeRule;
@Service
public class CompanyService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private CompanyRepository companyRepository;
    @Autowired
    private CloudinaryService cloudinaryService;
    @Autowired
    private CodeStatusRepository codeStatusRepository;
    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private CodeRuleRepository codeRuleRepository;
    @Autowired
    private CodeCensorstatusRepository censorstatusRepository;

    public CompanyResponse banCompany(int id) {
      Optional<Company> companyOptional = companyRepository.findById(id);
      Company company = companyOptional.get();
      String status = company.getStatusCode().getCode();
      if(status != "S2") {
        CodeStatus code = codeStatusRepository.findByCode("S2");
        company.setStatusCode(code);
    }
        Company companyRs = companyRepository.save(company);
      return convertEntityToDTO(company);
    }
    public CompanyResponse unBanCompany(int id) {
      Optional<Company> companyOptional = companyRepository.findById(id);
      Company company = companyOptional.get();
      String status = company.getStatusCode().getCode();
      if(status != "S1") {
        CodeStatus code = codeStatusRepository.findByCode("S1");
        company.setStatusCode(code);
      }
      Company companyRs = companyRepository.save(company);
      return convertEntityToDTO(company);
    }
    public List<CompanyResponse> getCompanies() {
      List<Company> lstCompany = companyRepository.findAll();
      List<CompanyResponse> lstCpnDTO = new ArrayList<CompanyResponse>(); 
      for(Company c : lstCompany) {
        lstCpnDTO.add(convertEntityToDTO(c));
      }
      return lstCpnDTO;
    }
    public CompanyResponse getCompanyByID(int id) {
      Optional<Company> companyOptional = companyRepository.findById(id);
      Company company = companyOptional.get();
      return convertEntityToDTO(company); 
    }
    public CompanyResponse updateCompany(int companyId, CompanyDTO companyDTO) {
        Optional<Company> companyOptional = companyRepository.findById(companyId);
        if (companyOptional.isPresent()) {
            Company company = companyOptional.get();
            convertDTOToEntity(companyDTO, company);
            company.setUpdatedAt(new Date()); 
            Company updatedCompany = companyRepository.save(company);
            return convertEntityToDTO(updatedCompany);
        } else {
            throw new AppException(ErrorCode.NOTEXISTCOMPANY);
        }
    }

    private void convertDTOToEntity(CompanyDTO companyDTO, Company company) {
    	CodeStatus code = codeStatusRepository.findByCode(companyDTO.getStatusCode());
    	CodeCensorstatus codeCS = censorstatusRepository.findByCode(companyDTO.getCensorCode());
        company.setName(companyDTO.getName());
        company.setThumbnail(companyDTO.getThumbnail());
        company.setCoverImage(companyDTO.getCoverImage());
        company.setDescriptionHTML(companyDTO.getDescriptionHTML());
        company.setDescriptionMarkdown(companyDTO.getDescriptionMarkdown());
        company.setWebsite(companyDTO.getWebsite());
        company.setAddress(companyDTO.getAddress());
        company.setPhonenumber(companyDTO.getPhonenumber());
        company.setAmountEmployer(companyDTO.getAmountEmployer());
        company.setTaxnumber(companyDTO.getTaxnumber());
        company.setStatusCode(code);
        company.setFile(companyDTO.getFile().getBytes());
        company.setAllowPost(companyDTO.getAllowPost());
        company.setAllowHotPost(companyDTO.getAllowHotPost());
        company.setAllowCvFree(companyDTO.getAllowCvFree());
        company.setAllowCV(companyDTO.getAllowCV());
        company.setCensorCode(codeCS);
        Optional<User> userOptional = userRepository.findById(companyDTO.getIdUser());
        User user = userOptional.get();
        company.setUser(user);
    }

    private CompanyResponse convertEntityToDTO(Company company) {
    	CompanyResponse companyResponse = new CompanyResponse();
    	companyResponse.setId(company.getId());
    	companyResponse.setName(company.getName() != null ? company.getName() : "Chưa có tên");
    	companyResponse.setThumbnail(company.getThumbnail() != null ? company.getThumbnail() : "Chưa có thumbnail");
    	companyResponse.setCoverImage(company.getCoverImage() != null ? company.getCoverImage() : "Chưa có cover image");
    	companyResponse.setDescriptionHTML(company.getDescriptionHTML() != null ? company.getDescriptionHTML() : "Chưa có mô tả HTML");
    	companyResponse.setDescriptionMarkdown(company.getDescriptionMarkdown() != null ? company.getDescriptionMarkdown() : "Chưa có mô tả Markdown");
    	companyResponse.setWebsite(company.getWebsite() != null ? company.getWebsite() : "Chưa có website");
        companyResponse.setAddress(company.getAddress() != null ? company.getAddress() : "Chưa có địa chỉ");
        companyResponse.setPhonenumber(company.getPhonenumber() != null ? company.getPhonenumber() : "Chưa có số điện thoại");
        companyResponse.setAmountEmployer(company.getAmountEmployer() != null ? company.getAmountEmployer() : 0);
        companyResponse.setTaxnumber(company.getTaxnumber() != null ? company.getTaxnumber() : "Chưa có mã số thuế");     
        companyResponse.setStatusCode(company.getStatusCode() != null ? company.getStatusCode().getCode() : "Chưa có trạng thái");
        if(company.getFile() == null) {
        	companyResponse.setFile("Chưa có file");
        }
        else {
        	byte[] byteArray = company.getFile();
        	String encodedString = Base64.getEncoder().encodeToString(byteArray);
        	String decodedString = new String(byteArray, StandardCharsets.UTF_8);
        	companyResponse.setFile(decodedString);
        }
        companyResponse.setAllowPost(company.getAllowPost() != null ? company.getAllowPost() : 0);
        companyResponse.setAllowHotPost(company.getAllowHotPost() != null ? company.getAllowHotPost() : 0);
        companyResponse.setAllowCvFree(company.getAllowCvFree() != null ? company.getAllowCvFree() : 0);
        companyResponse.setAllowCV(company.getAllowCV() != null ? company.getAllowCV() : 0);
        companyResponse.setCensorCode(company.getCensorCode() != null ? company.getCensorCode().getCode() : "Chưa có mã kiểm duyệt");
        companyResponse.setIdUser(company.getUser() != null ? company.getUser().getId() : null);
        companyResponse.setCreatedAt(company.getCreatedAt() != null ? company.getCreatedAt() : null);
        companyResponse.setUpdatedAt(company.getUpdatedAt() != null ? company.getUpdatedAt() : null);

        return companyResponse;
    }
    public Map<String, Object> getDetailCompanyByUserId(Integer userId, Integer companyId) {
        Map<String, Object> response = new HashMap<>();

        if (userId == null && companyId == null) {
            response.put("errCode", 1);
            response.put("errMessage", "Missing required parameters!");
            return response;
        }

        try {
            Company company = null;

            if (userId != null) {
                Optional<User> userOpt = userRepository.findById(userId);
                if (userOpt.isPresent()) {
                    User user = userOpt.get();
                    int idCompany = user.getCompanyId().intValue();
                    company = companyRepository.findById(idCompany).orElse(null);
                }
            } else {
                company = companyRepository.findById(companyId).orElse(null);
            }

            if (company == null) {
                response.put("errCode", 2);
                response.put("errMessage", "Không tìm thấy công ty người dùng sở hữu");
            } else {
                if (company.getFile() != null) {
                    byte[] decodedFile = Base64.getDecoder().decode(company.getFile());
                    // String fileAsBinary = new String(decodedFile, StandardCharsets.UTF_8);
                    company.setFile(decodedFile);
                }
                response.put("errCode", 0);
                response.put("data", company);
            }
        } catch (Exception e) {
            response.put("errCode", 3);
            response.put("errMessage", "An error occurred");
        }

        return response;
    }


    public Map<String, Object> createNewCompany(Company company,MultipartFile filethumb,MultipartFile fileCover) {
        Map<String, Object> response = new HashMap<>();

        if (company.getName() == null || company.getPhonenumber() == null || company.getAddress() == null
        || company.getDescriptionHTML() == null || company.getDescriptionMarkdown() == null
        || company.getAmountEmployer() == 0 || company.getUser().getId() == null) {
            response.put("errCode", 1);
            response.put("errMessage", "Missing required parameters!");
            return response;
        }

        // Check if company name exists
        if (companyRepository.existsByName(company.getName())) {
            response.put("errCode", 2);
            response.put("errMessage", "Tên công ty đã tồn tại");
            return response;
        }
        company.setName(company.getName());
        try {
            String thumbnailUrl = "";
            String coverImageUrl = "";
            String FileNamethumb = company.getName()+"ThumbnailsImage";
            String fileNameCover = company.getName()+"CoverImage";
            // Upload to Cloudinary
            if (company.getThumbnail() == null && company.getCoverImage() == null && filethumb != null &&fileCover != null) {
                CloudinaryResponse thumbnailResponse = cloudinaryService.uploadFile(filethumb,FileNamethumb);
                thumbnailUrl = thumbnailResponse.getUrl();
                CloudinaryResponse coverImageResponse = cloudinaryService.uploadFile(fileCover,fileNameCover);
                coverImageUrl = coverImageResponse.getUrl();
            }

            // Set URLs for uploaded images
            company.setThumbnail(thumbnailUrl);
            company.setCoverImage(coverImageUrl);

            // Set other default values
            CodeStatus status = codeStatusRepository.findById("S1")
                    .orElseThrow(() -> new RuntimeException("CodeStatus not found"));
            company.setStatusCode(status);

            // Set CensorCode nếu cần
            CodeCensorstatus censorCode = censorstatusRepository.findById(company.getFile() != null ? "CS3" : "CS2")
                    .orElseThrow(() -> new RuntimeException("CodeCensorstatus not found"));
            company.setCensorCode(censorCode);


            // value default khi tạo
            company.setAllowPost(0); 
            company.setAllowHotPost(0); 
            company.setAllowCvFree(0); 
            company.setAllowCV(0); 
            company.setCreatedAt(Date.from(Instant.now())); 
            company.setUpdatedAt(Date.from(Instant.now()));
            

            company.setDescriptionHTML(company.getDescriptionHTML());
            company.setDescriptionMarkdown(company.getDescriptionMarkdown());
            company.setWebsite(company.getWebsite());
            company.setAddress(company.getAddress());
            company.setPhonenumber(company.getPhonenumber());
            company.setAmountEmployer(company.getAmountEmployer());
            company.setTaxnumber(company.getTaxnumber());
            // Save new company
            System.out.println("id user: "+company.getUser().getId());
            Company savedCompany = companyRepository.save(company);

            // Update user and account
            Optional<User> userOpt = userRepository.findById(company.getUser().getId());
            System.out.println(userOpt.isPresent());
            Account accountOpt = accountRepository.findByUserId(company.getUser().getId());
            System.out.println("ID accountOpt : "+ accountOpt.getId());
            if (userOpt.isPresent() && accountOpt != null) {
                User user = userOpt.get();
                Account account = accountOpt;
            
                // Cập nhật thông tin cho User và Account
                user.setCompanyId(company.getId());
                userRepository.save(user);
            
                // tìm thấy `CodeRule`
                CodeRule companyRole = codeRuleRepository.findById("COMPANY")
                    .orElseThrow(() -> new RuntimeException("CodeRule not found"));
                // save
                account.setRoleCode(companyRole);
                accountRepository.save(account);
            } else {
                // Xử lý khi không tìm thấy User hoặc Account
                if (!userOpt.isPresent()) {
                    throw new RuntimeException("User not found");
                }
                if (accountOpt == null) {
                    throw new RuntimeException("Account not found");
                }
            }
            
            if (userOpt.isPresent() && accountOpt != null) {
                User user = userOpt.get();
                // Account account = accountOpt.get();

                user.setCompanyId(savedCompany.getId());
                userRepository.save(user);

                CodeRule companyRole = codeRuleRepository.findById("COMPANY")
                .orElseThrow(() -> new RuntimeException("CodeRule not found"));
                accountOpt.setRoleCode(companyRole);
                accountRepository.save(accountOpt);

                response.put("errCode", 0);
                response.put("errMessage", "Đã tạo công ty thành công");
                response.put("company", savedCompany);  // Trả về đối tượng Company vừa tạo
            } else {
                response.put("errCode", 2);
                response.put("errMessage", "Không tìm thấy người dùng");
            }
        } catch (Exception e) {
            response.put("errCode", 3);
            response.put("errMessage", "An error occurred");
        }

        return response;
    }

}
