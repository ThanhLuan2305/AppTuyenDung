package com.doan.AppTuyenDung.Services;

import java.util.Date;
import java.util.Optional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.doan.AppTuyenDung.DTO.CompanyDTO;
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
	public CompanyDTO createCompany(CompanyDTO companyDTO) {
		Company company = new Company();
		try {
	        convertDTOToEntity(companyDTO, company);
	        company.setCreatedAt(new Date()); 
	        company.setUpdatedAt(new Date());
		} catch (Exception e) {
			ErrorCode err = new ErrorCode(404, e.getMessage(), HttpStatus.BAD_REQUEST);
            throw new AppException(err);
		}

        Company savedCompany = companyRepository.save(company);
        return convertEntityToDTO(savedCompany);
    }

    public CompanyDTO updateCompany(int companyId, CompanyDTO companyDTO) {
        Optional<Company> companyOptional = companyRepository.findById(companyId);
        if (companyOptional.isPresent()) {
            Company company = companyOptional.get();
            convertDTOToEntity(companyDTO, company);
            company.setUpdatedAt(new Date()); 
            Company updatedCompany = companyRepository.save(company);
            return convertEntityToDTO(updatedCompany);
        } else {
        	ErrorCode err = new ErrorCode(404, "Không tìm thấy công ty với id trên", HttpStatus.BAD_REQUEST);
            throw new AppException(err);
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

    private CompanyDTO convertEntityToDTO(Company company) {
        CompanyDTO companyDTO = new CompanyDTO();
        companyDTO.setName(company.getName());
        companyDTO.setThumbnail(company.getThumbnail());
        companyDTO.setCoverImage(company.getCoverImage());
        companyDTO.setDescriptionHTML(company.getDescriptionHTML());
        companyDTO.setDescriptionMarkdown(company.getDescriptionMarkdown());
        companyDTO.setWebsite(company.getWebsite());
        companyDTO.setAddress(company.getAddress());
        companyDTO.setPhonenumber(company.getPhonenumber());
        companyDTO.setAmountEmployer(company.getAmountEmployer());
        companyDTO.setTaxnumber(company.getTaxnumber());
        companyDTO.setStatusCode(company.getStatusCode().getCode());
        companyDTO.setFile(company.getFile().toString());
        companyDTO.setAllowPost(company.getAllowPost());
        companyDTO.setAllowHotPost(company.getAllowHotPost());
        companyDTO.setAllowCvFree(company.getAllowCvFree());
        companyDTO.setAllowCV(company.getAllowCV());
        companyDTO.setCensorCode(company.getCensorCode().getCode());
        companyDTO.setIdUser(company.getUser().getId());
        companyDTO.setCreatedAt(company.getCreatedAt());
        companyDTO.setUpdatedAt(company.getUpdatedAt());
        return companyDTO;
    }
}
