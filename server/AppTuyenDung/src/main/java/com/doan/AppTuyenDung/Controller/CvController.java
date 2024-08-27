package com.doan.AppTuyenDung.Controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


import com.doan.AppTuyenDung.DTO.Response.ApiResponse;
import com.doan.AppTuyenDung.DTO.Response.CvsResponse;
import com.doan.AppTuyenDung.Services.CvService;

@RestController
@RequestMapping
public class CvController {
	@Autowired
	private CvService cvService;
	@GetMapping("/public/cv/get-all-cv-by-userId")
	public ApiResponse<Page<CvsResponse>> getAllCvByUserId(@RequestParam(defaultValue = "10") int limit
															,@RequestParam(defaultValue = "0") int offset
															,@RequestParam (required = false) Integer userId) {
		ApiResponse<Page<CvsResponse>> apiRs = new ApiResponse<>();
		Pageable pageable = PageRequest.of(offset, limit);
        Page<CvsResponse> listCv = cvService.getAllCvByUserId(userId,pageable);
        apiRs.setMessage("Lấy thành công danh sách cv của userId: "+ userId);
        apiRs.setResult(listCv);
        return apiRs;
	}
}
