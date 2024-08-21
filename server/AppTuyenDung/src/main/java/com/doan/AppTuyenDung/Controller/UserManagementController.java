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
import com.doan.AppTuyenDung.DTO.ReqRes;
import com.doan.AppTuyenDung.DTO.UserAccountDTO;
import com.doan.AppTuyenDung.DTO.UserUpdateRequest;
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
    public ResponseEntity<ReqRes> getAllUsers(){
        return ResponseEntity.ok(usersManagementService.getAllUsers());

    }

    @GetMapping("/admin/get-users/{userId}")
    public ResponseEntity<ReqRes> getUSerByID(@PathVariable Integer userId){
        return ResponseEntity.ok(usersManagementService.getUsersById(userId));

    }

    @PutMapping("/admin/update/{userId}")
    public ResponseEntity<ReqRes> updateUser(@PathVariable Integer userId, @RequestBody UserUpdateRequest reqres){
        return ResponseEntity.ok(usersManagementService.updateUser(userId, reqres));
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