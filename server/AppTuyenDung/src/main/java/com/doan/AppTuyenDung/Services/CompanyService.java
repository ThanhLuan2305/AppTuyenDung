package com.doan.AppTuyenDung.Services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.doan.AppTuyenDung.Repositories.CodeStatusRepository;
import com.doan.AppTuyenDung.Repositories.CompanyRepository;
import com.doan.AppTuyenDung.entity.CodeStatus;
import com.doan.AppTuyenDung.entity.Company;

@Service
public class CompanyService {
	@Autowired 
	private CompanyRepository companyRepository;
	@Autowired
	private CodeStatusRepository codeStatusRepository;
	
	public void banCompany(int id) {
		Optional<Company> companyOptional = companyRepository.findById(id);
		Company company = companyOptional.get();
		String status = company.getStatusCode().getCode();
		if(status != "S2") {
			CodeStatus code = codeStatusRepository.findByCode("S2");
			company.setStatusCode(code);
		}
		Company companyRs = companyRepository.save(company);
	}
	public void unBanCompany(int id) {
		Optional<Company> companyOptional = companyRepository.findById(id);
		Company company = companyOptional.get();
		String status = company.getStatusCode().getCode();
		if(status != "S1") {
			CodeStatus code = codeStatusRepository.findByCode("S1");
			company.setStatusCode(code);
		}
		Company companyRs = companyRepository.save(company);
	}
	//public void UpdateCompany()
}
