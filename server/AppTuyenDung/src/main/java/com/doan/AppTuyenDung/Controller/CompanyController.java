package com.doan.AppTuyenDung.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.doan.AppTuyenDung.DTO.Request.CompanyDTO;
import com.doan.AppTuyenDung.DTO.Response.ApiResponse;
import com.doan.AppTuyenDung.DTO.Response.CompanyResponse;
import com.doan.AppTuyenDung.Services.CompanyService;


@RestController
@RequestMapping
public class CompanyController {
	@Autowired
	CompanyService companyService;
	 @PutMapping("/public/company/ban/{copanyId}")
	 public ApiResponse<CompanyResponse> banCompany(@PathVariable int copanyId) {
		 ApiResponse<CompanyResponse> apiResponse = new ApiResponse<>();
		 apiResponse.setResult(companyService.banCompany(copanyId));
		 apiResponse.setMessage("Công ty đã bị cấm");
		 return apiResponse;
	 }
	 @PutMapping("/public/company/unban/{copanyId}")
	 public ApiResponse<CompanyResponse> unBanCompany(@PathVariable int copanyId) {
		 ApiResponse<CompanyResponse> apiResponse = new ApiResponse<>();
		 apiResponse.setResult(companyService.unBanCompany(copanyId));
		 apiResponse.setMessage("Công ty đã được mở cấm");
		 return apiResponse;
	 }
	 @PostMapping("/public/company/create")
	 public ApiResponse<CompanyResponse> createCompany(@RequestBody CompanyDTO companyDTO) {
		 CompanyResponse createCPN = companyService.createCompany(companyDTO);
		 ApiResponse<CompanyResponse> apiResponse = new ApiResponse<>();
		 apiResponse.setResult(createCPN);
		 apiResponse.setMessage("Thêm công ty thành công");
		 return apiResponse;
	 }
	 @PutMapping("/public/company/update/{companyID}")
	 public ApiResponse<CompanyResponse> updateCompany(@PathVariable int companyID, @RequestBody CompanyDTO companyDTO) {
		 CompanyResponse updateCPN = companyService.updateCompany(companyID, companyDTO);
		 ApiResponse<CompanyResponse> apiResponse = new ApiResponse<>();
		 apiResponse.setResult(updateCPN);
		 apiResponse.setMessage("Sửa công ty thành công");
		 return apiResponse;
	 }
	 @GetMapping("/public/company/get_all_company")
	 public ApiResponse<List<CompanyResponse>> getAllCompany() {
		 ApiResponse<List<CompanyResponse>> lstCpn = new ApiResponse<>();
		 lstCpn.setResult(companyService.getCompanies());
		 lstCpn.setMessage("Hiện tất cả công ty thành công" );
		 return lstCpn;
	 }
	 @GetMapping("/public/company/get_company/{companyID}")
	 public ApiResponse<CompanyResponse> getAllCompany(@PathVariable int companyID) {
		 ApiResponse<CompanyResponse> apiResponse = new ApiResponse<>();
		 apiResponse.setResult(companyService.getCompanyByID(companyID));
		 apiResponse.setMessage("Tìm thấy công ti với id: "+companyID );
		 return apiResponse;
	 }
}
