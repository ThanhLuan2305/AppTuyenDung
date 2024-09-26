package com.doan.AppTuyenDung.Controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.doan.AppTuyenDung.DTO.GetAllJobTypeAdmin.JobtypeDTO;
import com.doan.AppTuyenDung.DTO.GetAllJobTypeAdmin.ResponseAllJobType;
import com.doan.AppTuyenDung.DTO.GetAllJobTypeAdmin.ResponseDelete;
import com.doan.AppTuyenDung.Repositories.AccountRepository;
import com.doan.AppTuyenDung.Repositories.AllCode.CodeJobTypeRepository;
import com.doan.AppTuyenDung.Services.AllCodeService;
import com.doan.AppTuyenDung.Services.JWTUtils;
import com.doan.AppTuyenDung.entity.CodeJobType;

@RestController
@RequestMapping("")
public class CodeAdminController {

    @Autowired
    private JWTUtils jwtUtils; 

    @Autowired
    private AccountRepository accountRepo;

    @Autowired
    private CodeJobTypeRepository codeJobTypeRepository;

    @Autowired
    private AllCodeService allCodeService;


    @PostMapping("/public/create-jobType")
    public ResponseEntity<Map<String, Object>> handleCreateJobType(@RequestHeader("Authorization") String token, 
                                                            @ModelAttribute CodeJobType data,
                                                            @RequestPart("fileimage" ) MultipartFile fileimage) {
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
        Map<String, Object> response = allCodeService.handleCreateNewJobType(data,fileimage);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/public/update-jobtype")
    public ResponseEntity<Map<String, Object>> handleUpdateJobType(@RequestHeader("Authorization") String token, 
                                                        @ModelAttribute CodeJobType data,
                                                        @RequestPart("filethumb" ) MultipartFile filethumb) 
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
        data.setType("JOBTYPE");
        Map<String, Object> response = allCodeService.handleUpdateJobType(data,filethumb);
        return ResponseEntity.ok(response);
    }


    @GetMapping("/public/get-all-jobtype")
    public ResponseEntity<ResponseAllJobType<Page<JobtypeDTO>>> getAllJobType(@RequestHeader("Authorization") String token,
										@RequestParam(defaultValue = "10") int limit,
										@RequestParam(defaultValue = "0") int offset,
										@RequestParam(required = false) String search) 
	{
        Pageable pageable = PageRequest.of(offset, limit);

		if (token.startsWith("Bearer ")) {
            token = token.substring(7).trim();
        }
        // Giải mã token 
        String phonenumber = jwtUtils.extractUserName(token);
        var account = accountRepo.findByPhonenumber(phonenumber);
        System.out.println(account.getRoleCode().getCode());
        if (account == null || !account.getRoleCode().getCode().equals("ADMIN") ) {
            ResponseAllJobType<Page<JobtypeDTO>> response = new ResponseAllJobType<>(-1, "Error from Client without access", null);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
        try {
			if (search != null && !search.isEmpty()) 
			{
				search = "%" + search + "%";
			}
            Page<JobtypeDTO> jobtype = allCodeService.GetAllJobType(search,pageable);
            ResponseAllJobType<Page<JobtypeDTO>> response = new ResponseAllJobType<>(0, "Lấy dữ liệu thành công", jobtype);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            ResponseAllJobType<Page<JobtypeDTO>> response = new ResponseAllJobType<>(-1, "Error from server", null);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }

    }
    @DeleteMapping("/public/delete-jobtype")
    public ResponseEntity<?> handleDelete(@RequestHeader("Authorization") String token,@RequestParam String code) {

        if (token.startsWith("Bearer ")) {
            token = token.substring(7).trim();
        }
        // Giải mã token 
        String phonenumber = jwtUtils.extractUserName(token);
        var account = accountRepo.findByPhonenumber(phonenumber);
        System.out.println(account.getRoleCode().getCode());
        if (account == null || !account.getRoleCode().getCode().equals("ADMIN") ) {
            ResponseDelete response = new ResponseDelete(-1, "Error from Client without access");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }

        return allCodeService.handleDeleteAllCode(code);
    }

    @GetMapping("/public/get-detail-JobType-by-code")
    public ResponseEntity<ResponseAllJobType> getDetail(@RequestHeader("Authorization") String token, @RequestParam String code) {
        if (token.startsWith("Bearer ")) {
            token = token.substring(7).trim();
        }
        // Giải mã token 
        String phonenumber = jwtUtils.extractUserName(token);
        var account = accountRepo.findByPhonenumber(phonenumber);
        System.out.println(account.getRoleCode().getCode());
        if (account == null || !account.getRoleCode().getCode().equals("ADMIN") ) {
            ResponseAllJobType response = new ResponseAllJobType(-1, "Error from Client without access");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
        
        ResponseEntity<ResponseAllJobType> responseEntity = allCodeService.getDetailJobTypeByCode(code);
        ResponseAllJobType response = responseEntity.getBody();
        return ResponseEntity.ok(response);
    }



}
