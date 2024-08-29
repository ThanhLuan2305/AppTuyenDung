package com.doan.AppTuyenDung.Controller;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Instant;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.doan.AppTuyenDung.DTO.Request.CompanyDTO;
import com.doan.AppTuyenDung.DTO.Response.ApiResponse;
import com.doan.AppTuyenDung.DTO.Response.CompanyResponse;
import com.doan.AppTuyenDung.DTO.CloudinaryResponse;
import com.doan.AppTuyenDung.Repositories.AccountRepository;
import com.doan.AppTuyenDung.Repositories.CodeRuleRepository;
import com.doan.AppTuyenDung.Repositories.CompanyRepository;
import com.doan.AppTuyenDung.Repositories.UserRepository;
import com.doan.AppTuyenDung.Repositories.AllCode.CodeCensorStatusRepository;
import com.doan.AppTuyenDung.Repositories.AllCode.CodeStatusRepository;
import com.doan.AppTuyenDung.Services.CloudinaryService;
import com.doan.AppTuyenDung.Services.CompanyService;
import com.doan.AppTuyenDung.Services.JWTUtils;
import com.doan.AppTuyenDung.entity.Account;
import com.doan.AppTuyenDung.entity.CodeCensorstatus;
import com.doan.AppTuyenDung.entity.CodeRule;
import com.doan.AppTuyenDung.entity.CodeStatus;
import com.doan.AppTuyenDung.entity.Company;
import com.doan.AppTuyenDung.entity.User;

@RestController
@RequestMapping("/public/admin")
public class CompanyController {

    @Autowired
    private CompanyService companyService;

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

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JWTUtils jwtUtils; 
    @Autowired
    private AccountRepository accountRepo;

    @Autowired
	  CompanyService companyService;
	 @PutMapping("/company/ban/{copanyId}")
	 public ApiResponse<CompanyResponse> banCompany(@PathVariable int copanyId) {
		 ApiResponse<CompanyResponse> apiResponse = new ApiResponse<>();
		 apiResponse.setResult(companyService.banCompany(copanyId));
		 apiResponse.setMessage("Công ty đã bị cấm");
		 return apiResponse;
	 }
	 @PutMapping("/company/unban/{copanyId}")
	 public ApiResponse<CompanyResponse> unBanCompany(@PathVariable int copanyId) {
		 ApiResponse<CompanyResponse> apiResponse = new ApiResponse<>();
		 apiResponse.setResult(companyService.unBanCompany(copanyId));
		 apiResponse.setMessage("Công ty đã được mở cấm");
		 return apiResponse;
	 }
	 @PutMapping("/company/update/{companyID}")
	 public ApiResponse<CompanyResponse> updateCompany(@PathVariable int companyID, @RequestBody CompanyDTO companyDTO) {
		 CompanyResponse updateCPN = companyService.updateCompany(companyID, companyDTO);
		 ApiResponse<CompanyResponse> apiResponse = new ApiResponse<>();
		 apiResponse.setResult(updateCPN);
		 apiResponse.setMessage("Sửa công ty thành công");
		 return apiResponse;
	 }
	 @GetMapping("/company/get_all_company")
	 public ApiResponse<List<CompanyResponse>> getAllCompany() {
		 ApiResponse<List<CompanyResponse>> lstCpn = new ApiResponse<>();
		 lstCpn.setResult(companyService.getCompanies());
		 lstCpn.setMessage("Hiện tất cả công ty thành công" );
		 return lstCpn;
	 }
	 @GetMapping("/company/get_company/{companyID}")
	 public ApiResponse<CompanyResponse> getAllCompany(@PathVariable int companyID) {
		 ApiResponse<CompanyResponse> apiResponse = new ApiResponse<>();
		 apiResponse.setResult(companyService.getCompanyByID(companyID));
		 apiResponse.setMessage("Tìm thấy công ti với id: "+companyID );
		 return apiResponse;
     
    @GetMapping("api/get-detail-company-by-userId")
    public ResponseEntity<?> getDetailCompanyByUserId(
            @RequestParam(value = "userId", required = false) String userId,
            @RequestParam(value = "companyId", required = false) String companyId) 
    {
        Integer parsedUserId = userId != null && !"null".equalsIgnoreCase(userId) ? Integer.parseInt(userId) : null;
        Integer parsedCompanyId = companyId != null && !"null".equalsIgnoreCase(companyId) ? Integer.parseInt(companyId) : null;
        if (userId == null && companyId == null) {
            Map<String, Object> errorResponse = new HashMap<>();
            errorResponse.put("errCode", 1);
            errorResponse.put("errMessage", "Missing required parameters!");
            return ResponseEntity.badRequest().body(errorResponse);
        }
        Map<String, Object> response = companyService.getDetailCompanyByUserId(parsedUserId, parsedCompanyId);
        return ResponseEntity.ok(response);

    }

    @PostMapping("/create-company")
    public ResponseEntity<Map<String, Object>> createNewCompany(@RequestHeader("Authorization") String token, 
    @ModelAttribute Company company,@RequestPart("filethumb" ) MultipartFile filethumb,
    @RequestPart("fileCover")MultipartFile fileCover, @RequestPart("filepdf")MultipartFile filepdf) throws IOException
    {
        if (token.startsWith("Bearer ")) {
            token = token.substring(7).trim();
        }
        // Giải mã token 
        String phonenumber = jwtUtils.extractUserName(token);

        System.out.println(phonenumber);
        var account = accountRepo.findByPhonenumber(phonenumber);
        if (account == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
        company.setUser(account.getUser());
        if (filepdf != null && !filepdf.isEmpty()) {
            company.setFile(filepdf.getBytes()); 
        }
        Map<String, Object> response = companyService.createNewCompany(company,filethumb,fileCover);
        return ResponseEntity.ok(response);
    }
}
