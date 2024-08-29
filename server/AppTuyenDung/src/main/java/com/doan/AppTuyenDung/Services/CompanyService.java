package com.doan.AppTuyenDung.Services;

import java.nio.charset.StandardCharsets;
import java.time.Instant;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import com.doan.AppTuyenDung.DTO.CloudinaryResponse;
import com.doan.AppTuyenDung.Repositories.AccountRepository;
import com.doan.AppTuyenDung.Repositories.CodeRuleRepository;
import com.doan.AppTuyenDung.Repositories.CompanyRepository;
import com.doan.AppTuyenDung.Repositories.UserRepository;
import com.doan.AppTuyenDung.Repositories.AllCode.CodeCensorStatusRepository;
import com.doan.AppTuyenDung.Repositories.AllCode.CodeStatusRepository;
import com.doan.AppTuyenDung.entity.Account;
import com.doan.AppTuyenDung.entity.CodeCensorstatus;
import com.doan.AppTuyenDung.entity.CodeRule;
import com.doan.AppTuyenDung.entity.CodeStatus;
import com.doan.AppTuyenDung.entity.Company;
import com.doan.AppTuyenDung.entity.User;
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
    private CodeCensorStatusRepository codeCensorStatusRepository;

    @Autowired
    private AccountRepository accountRepository;


    @Autowired
    private CodeRuleRepository codeRuleRepository;

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
            CodeCensorstatus censorCode = codeCensorStatusRepository.findById(company.getFile() != null ? "CS3" : "CS2")
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
