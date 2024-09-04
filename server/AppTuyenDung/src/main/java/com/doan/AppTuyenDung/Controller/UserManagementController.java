package com.doan.AppTuyenDung.Controller;

import com.doan.AppTuyenDung.entity.Account;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import com.doan.AppTuyenDung.entity.User;
import com.doan.AppTuyenDung.DTO.Request.ProfileUserRequest;
import com.doan.AppTuyenDung.DTO.Request.ReqRes;
import com.doan.AppTuyenDung.DTO.Request.UserSettingDTO;
import com.doan.AppTuyenDung.DTO.Request.UserUpdateRequest;
import com.doan.AppTuyenDung.DTO.Response.AccountResponse;
import com.doan.AppTuyenDung.DTO.Response.ApiResponse;
import com.doan.AppTuyenDung.DTO.UserAccountDTO;
import com.doan.AppTuyenDung.Repositories.AccountRepository;
import com.doan.AppTuyenDung.Repositories.UserRepository;
import com.doan.AppTuyenDung.Services.JWTUtils;
import com.doan.AppTuyenDung.Services.UserManagermentService;





@RestController
@RequestMapping
public class UserManagementController {
	@Autowired
    private UserManagermentService usersManagementService;
    @Autowired
    private JWTUtils jwtUtils; 
    @Autowired
    private AccountRepository accountRepo;
    @Autowired
    private UserRepository userRepository;
    @PostMapping("/auth/register")
    public ResponseEntity<ReqRes> regeister(@RequestBody ReqRes reg){
        return ResponseEntity.ok(usersManagementService.register(reg));
    }

    @PostMapping("/auth/login")
    public ResponseEntity<ReqRes> login(@RequestBody ReqRes req){
        return ResponseEntity.ok(usersManagementService.login(req));
    }

    @PostMapping("/auth/refresh")
    public ResponseEntity<ReqRes> refreshToken(@RequestBody ReqRes req){
        return ResponseEntity.ok(usersManagementService.refreshToken(req));
    }

    @GetMapping("/admin/get-all-users")
    public ApiResponse<List<AccountResponse>> getAllUsers() throws Exception{

    	ApiResponse apiResponse = new ApiResponse<>();
    	try {
        	apiResponse.setMessage("Danh sách người dừng: ");
        	apiResponse.setResult(usersManagementService.getAllUsers());
		} catch (Exception e) {
        	apiResponse.setMessage(e.getMessage());
        	apiResponse.setCode(404);
		}
        return apiResponse;
    }

    @GetMapping("/public/get-user/{userId}")
    public ApiResponse<AccountResponse> getUSerByID(@PathVariable Integer userId) throws Exception{
    	ApiResponse apiResponse = new ApiResponse<>();
    	apiResponse.setMessage("Tìm thấy người dùng với id: "+userId);
    	apiResponse.setResult(usersManagementService.getUsersById(userId));
        return apiResponse;

    }

    @PutMapping("/admin/update/{userId}")
    public ResponseEntity<ReqRes> updateUser(@PathVariable Integer userId, @RequestBody UserUpdateRequest reqres){
        return ResponseEntity.ok(usersManagementService.updateUser(userId, reqres));
    }
    @GetMapping("/public/get-profile/{token}")
    public ResponseEntity<ProfileUserRequest> getProfile(@PathVariable String token){
        return ResponseEntity.ok(usersManagementService.getProfile(token));

    }
    @PostMapping("/public/set-user-setting")
    public ApiResponse setDataUserSetting(@RequestBody UserSettingDTO data) {
    	ApiResponse apiRS = new ApiResponse<>();
    	apiRS.setMessage(usersManagementService.setDataUserSetting(data));
        return apiRS;
    }


    @PostMapping("/public/get-info")
    public ResponseEntity<List<UserAccountDTO>> getUserInfo(@RequestHeader("Authorization") String token) {
        if (token.startsWith("Bearer ")) {
            token = token.substring(7);
        }
        // Giải mã token 
        String phonenumber = jwtUtils.extractUserName(token);

        var account = accountRepo.findByPhonenumber(phonenumber);
        if (account == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
        List<UserAccountDTO> userAccountInfo = userRepository.findInfoUser(phonenumber);
        return ResponseEntity.ok(userAccountInfo);
    }



    @GetMapping("/public/check-phonenumber-user")
    public ResponseEntity<?> getMethodName(@RequestParam String phonenumber) {
        boolean exists = accountRepo.existsByPhonenumber(phonenumber);
        if (exists) {
            return ResponseEntity.ok(true);
        } 
        // Nếu không tồn tại, trả về sdt đó
        else {
            return ResponseEntity.ok(phonenumber);
        }
    }
    
}