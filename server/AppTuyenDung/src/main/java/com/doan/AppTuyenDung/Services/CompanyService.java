package com.doan.AppTuyenDung.Services;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Date;
import java.util.List;
import java.util.Optional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.doan.AppTuyenDung.DTO.Request.CompanyDTO;
import com.doan.AppTuyenDung.DTO.Response.CompanyResponse;
import com.doan.AppTuyenDung.Exception.AppException;
import com.doan.AppTuyenDung.Exception.ErrorCode;
import com.doan.AppTuyenDung.Repositories.CodeCensorstatusRepository;
import com.doan.AppTuyenDung.Repositories.CodeStatusRepository;
import com.doan.AppTuyenDung.Repositories.CompanyRepository;
import com.doan.AppTuyenDung.Repositories.UserRepository;
import com.doan.AppTuyenDung.entity.CodeStatus;
import com.doan.AppTuyenDung.entity.CodeCensorstatus;
import com.doan.AppTuyenDung.entity.Company;
import com.doan.AppTuyenDung.entity.User;

import jakarta.persistence.EntityNotFoundException;

@Service
public class CompanyService {
	@Autowired 
	private CompanyRepository companyRepository;
	@Autowired
	private CodeStatusRepository codeStatusRepository;
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private CodeCensorstatusRepository censorstatusRepository;
	
	public CompanyResponse banCompany(int id) {
		Optional<Company> companyOptional = companyRepository.findById(id);
		Company company = companyOptional.get();
		String status = company.getStatusCode().getCode();
		if(status != "S2") {
			CodeStatus code = codeStatusRepository.findByCode("S2");
			company.setStatusCode(code);
		}
		Company companyRs = companyRepository.save(company);
		return convertEntityToDTO(company);
	}
	public CompanyResponse unBanCompany(int id) {
		Optional<Company> companyOptional = companyRepository.findById(id);
		Company company = companyOptional.get();
		String status = company.getStatusCode().getCode();
		if(status != "S1") {
			CodeStatus code = codeStatusRepository.findByCode("S1");
			company.setStatusCode(code);
		}
		Company companyRs = companyRepository.save(company);
		return convertEntityToDTO(company);
	}
	public CompanyResponse createCompany(CompanyDTO companyDTO) {
		Company company = new Company();
		try {
	        convertDTOToEntity(companyDTO, company);
	        company.setCreatedAt(new Date()); 
	        company.setUpdatedAt(new Date());
		} catch (Exception e) {
            throw new AppException(ErrorCode.UNCATEGORIZED_EXCEPTION);
		}

        Company savedCompany = companyRepository.save(company);
        return convertEntityToDTO(savedCompany);
    }
	public List<CompanyResponse> getCompanies() {
		List<Company> lstCompany = companyRepository.findAll();
		List<CompanyResponse> lstCpnDTO = new ArrayList<CompanyResponse>(); 
		for(Company c : lstCompany) {
			lstCpnDTO.add(convertEntityToDTO(c));
		}
		return lstCpnDTO;
	}
	public CompanyResponse getCompanyByID(int id) {
		Optional<Company> companyOptional = companyRepository.findById(id);
		Company company = companyOptional.get();
		return convertEntityToDTO(company); 
	}
    public CompanyResponse updateCompany(int companyId, CompanyDTO companyDTO) {
        Optional<Company> companyOptional = companyRepository.findById(companyId);
        if (companyOptional.isPresent()) {
            Company company = companyOptional.get();
            convertDTOToEntity(companyDTO, company);
            company.setUpdatedAt(new Date()); 
            Company updatedCompany = companyRepository.save(company);
            return convertEntityToDTO(updatedCompany);
        } else {
            throw new AppException(ErrorCode.NOTEXISTCOMPANY);
        }
    }

    private void convertDTOToEntity(CompanyDTO companyDTO, Company company) {
    	CodeStatus code = codeStatusRepository.findByCode(companyDTO.getStatusCode());
    	CodeCensorstatus codeCS = censorstatusRepository.findByCode(companyDTO.getCensorCode());
        company.setName(companyDTO.getName());
        company.setThumbnail(companyDTO.getThumbnail());
        company.setCoverImage(companyDTO.getCoverImage());
        company.setDescriptionHTML(companyDTO.getDescriptionHTML());
        company.setDescriptionMarkdown(companyDTO.getDescriptionMarkdown());
        company.setWebsite(companyDTO.getWebsite());
        company.setAddress(companyDTO.getAddress());
        company.setPhonenumber(companyDTO.getPhonenumber());
        company.setAmountEmployer(companyDTO.getAmountEmployer());
        company.setTaxnumber(companyDTO.getTaxnumber());
        company.setStatusCode(code);
        company.setFile(companyDTO.getFile().getBytes());
        company.setAllowPost(companyDTO.getAllowPost());
        company.setAllowHotPost(companyDTO.getAllowHotPost());
        company.setAllowCvFree(companyDTO.getAllowCvFree());
        company.setAllowCV(companyDTO.getAllowCV());
        company.setCensorCode(codeCS);
        Optional<User> userOptional = userRepository.findById(companyDTO.getIdUser());
        User user = userOptional.get();
        company.setUser(user);
    }

    private CompanyResponse convertEntityToDTO(Company company) {
    	CompanyResponse companyResponse = new CompanyResponse();
    	companyResponse.setId(company.getId());
    	companyResponse.setName(company.getName() != null ? company.getName() : "Chưa có tên");
    	companyResponse.setThumbnail(company.getThumbnail() != null ? company.getThumbnail() : "Chưa có thumbnail");
    	companyResponse.setCoverImage(company.getCoverImage() != null ? company.getCoverImage() : "Chưa có cover image");
    	companyResponse.setDescriptionHTML(company.getDescriptionHTML() != null ? company.getDescriptionHTML() : "Chưa có mô tả HTML");
    	companyResponse.setDescriptionMarkdown(company.getDescriptionMarkdown() != null ? company.getDescriptionMarkdown() : "Chưa có mô tả Markdown");
    	companyResponse.setWebsite(company.getWebsite() != null ? company.getWebsite() : "Chưa có website");
        companyResponse.setAddress(company.getAddress() != null ? company.getAddress() : "Chưa có địa chỉ");
        companyResponse.setPhonenumber(company.getPhonenumber() != null ? company.getPhonenumber() : "Chưa có số điện thoại");
        companyResponse.setAmountEmployer(company.getAmountEmployer() != null ? company.getAmountEmployer() : 0);
        companyResponse.setTaxnumber(company.getTaxnumber() != null ? company.getTaxnumber() : "Chưa có mã số thuế");     
        companyResponse.setStatusCode(company.getStatusCode() != null ? company.getStatusCode().getCode() : "Chưa có trạng thái");
        if(company.getFile() == null) {
        	companyResponse.setFile("Chưa có file");
        }
        else {
        	byte[] byteArray = company.getFile();
        	String encodedString = Base64.getEncoder().encodeToString(byteArray);
        	String decodedString = new String(byteArray, StandardCharsets.UTF_8);
        	companyResponse.setFile(decodedString);
        }
        companyResponse.setAllowPost(company.getAllowPost() != null ? company.getAllowPost() : 0);
        companyResponse.setAllowHotPost(company.getAllowHotPost() != null ? company.getAllowHotPost() : 0);
        companyResponse.setAllowCvFree(company.getAllowCvFree() != null ? company.getAllowCvFree() : 0);
        companyResponse.setAllowCV(company.getAllowCV() != null ? company.getAllowCV() : 0);
        companyResponse.setCensorCode(company.getCensorCode() != null ? company.getCensorCode().getCode() : "Chưa có mã kiểm duyệt");
        companyResponse.setIdUser(company.getUser() != null ? company.getUser().getId() : null);
        companyResponse.setCreatedAt(company.getCreatedAt() != null ? company.getCreatedAt() : null);
        companyResponse.setUpdatedAt(company.getUpdatedAt() != null ? company.getUpdatedAt() : null);

        return companyResponse;
    }

}
