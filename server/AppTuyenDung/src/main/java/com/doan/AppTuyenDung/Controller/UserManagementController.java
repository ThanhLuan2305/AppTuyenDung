package com.doan.AppTuyenDung.Controller;

import com.doan.AppTuyenDung.entity.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import com.doan.AppTuyenDung.entity.User;
import com.doan.AppTuyenDung.DTO.Request.ProfileUserRequest;
import com.doan.AppTuyenDung.DTO.Request.ReqRes;
import com.doan.AppTuyenDung.DTO.Request.UserUpdateRequest;
import com.doan.AppTuyenDung.Services.UserManagermentService;



@RestController
@RequestMapping
public class UserManagementController {
	@Autowired
    private UserManagermentService usersManagementService;

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
    @GetMapping("/public/get-profile/{token}")
    public ResponseEntity<ProfileUserRequest> getProfile(@PathVariable String token){
        return ResponseEntity.ok(usersManagementService.getProfile(token));

    }

}