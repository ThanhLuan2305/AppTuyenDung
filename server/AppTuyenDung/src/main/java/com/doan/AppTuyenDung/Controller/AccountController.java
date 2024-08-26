package com.doan.AppTuyenDung.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.doan.AppTuyenDung.DTO.Request.ChangePasswordRequest;
import com.doan.AppTuyenDung.DTO.Response.ApiResponse;
import com.doan.AppTuyenDung.Services.AccountService;

@RestController
@RequestMapping
public class AccountController {
	@Autowired
	AccountService accountService;
	@PutMapping("/public/change_password/{idUser}")
	ApiResponse<String> changePassword(@PathVariable int idUser, @RequestBody ChangePasswordRequest cPasswordRequest) {
		ApiResponse<String> apiRQ = new ApiResponse<String>();
		apiRQ.setMessage(accountService.changePassword(idUser, cPasswordRequest));
		return apiRQ;
	}
}
