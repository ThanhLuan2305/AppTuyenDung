package com.doan.AppTuyenDung.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.doan.AppTuyenDung.DTO.CompanyDTO;
import com.doan.AppTuyenDung.Services.CompanyService;



@RestController
@RequestMapping
public class CompanyController {
	@Autowired
	CompanyService companyService;
	 @PutMapping("/public/company/ban/{copanyId}")
	 public ResponseEntity<String> banCompany(@PathVariable int copanyId) {
		 companyService.banCompany(copanyId);
		 return ResponseEntity.ok("Đã cấm công ty này");
	 }
	 @PutMapping("/public/company/unban/{copanyId}")
	 public ResponseEntity<String> unBanCompany(@PathVariable int copanyId) {
		 companyService.unBanCompany(copanyId);
		 return ResponseEntity.ok("Đã bỏ cấm công ty này");
	 }
	 @PostMapping("/public/company/create")
	 public ResponseEntity<CompanyDTO> createCompany(@RequestBody CompanyDTO companyDTO) {
		 CompanyDTO createCPN = companyService.createCompany(companyDTO);
		 return ResponseEntity.ok(createCPN);
	 }
	 @PutMapping("/public/company/update/{companyID}")
	 public ResponseEntity<CompanyDTO> updateCompany(@PathVariable int companyID, @RequestBody CompanyDTO companyDTO) {
		 CompanyDTO updateCPN = companyService.updateCompany(companyID, companyDTO);
		 return ResponseEntity.ok(updateCPN);
	 }
}
