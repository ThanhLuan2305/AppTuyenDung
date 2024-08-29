package com.doan.AppTuyenDung.Services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.doan.AppTuyenDung.Repositories.CodeGenderRepository;
import com.doan.AppTuyenDung.Repositories.CodeRuleRepository;
import com.doan.AppTuyenDung.Repositories.AllCode.CodeExpTypeRepository;
import com.doan.AppTuyenDung.Repositories.AllCode.CodeGendersRepository;
import com.doan.AppTuyenDung.Repositories.AllCode.CodeJobLevelRepository;
import com.doan.AppTuyenDung.Repositories.AllCode.CodeJobTypeRepository;
import com.doan.AppTuyenDung.Repositories.AllCode.CodeProvinceRepository;
import com.doan.AppTuyenDung.Repositories.AllCode.CodeRulesRepository;
import com.doan.AppTuyenDung.Repositories.AllCode.CodeSalaryTypeRepository;
import com.doan.AppTuyenDung.Repositories.AllCode.CodeWorkTypeRepository;
import com.doan.AppTuyenDung.entity.CodeExpType;
import com.doan.AppTuyenDung.entity.CodeGender;
import com.doan.AppTuyenDung.entity.CodeJobLevel;
import com.doan.AppTuyenDung.entity.CodeJobType;
import com.doan.AppTuyenDung.entity.CodeProvince;
import com.doan.AppTuyenDung.entity.CodeRule;
import com.doan.AppTuyenDung.entity.CodeSalaryType;
import com.doan.AppTuyenDung.entity.CodeWorkType;
@Service
public class AllCodeService {
    @Autowired
    private CodeExpTypeRepository codeExpTypeRepository;

    @Autowired
    private CodeJobLevelRepository codeJobLevelRepository;

    @Autowired
    private CodeJobTypeRepository codeJobTypeRepository;

    @Autowired
    private CodeProvinceRepository codeProvinceRepository;

    @Autowired
    private CodeSalaryTypeRepository codeSalaryTypeRepository;

    @Autowired
    private CodeWorkTypeRepository codeWorkTypeRepository;

    @Autowired
    private CodeRulesRepository codeRulesRepository;
    @Autowired
    private CodeGendersRepository codeGendersRepository;
    public List<CodeExpType> getAllExpTypes() {
        return codeExpTypeRepository.findAll();
    }

    public List<CodeJobLevel> getAllJobLevels() {
        return codeJobLevelRepository.findAll();
    }

    public List<CodeJobType> getAllJobTypes() {
        return codeJobTypeRepository.findAll();
    }

    public List<CodeProvince> getAllProvinces() {
        return codeProvinceRepository.findAll();
    }

    public List<CodeSalaryType> getAllSalaryTypes() {
        return codeSalaryTypeRepository.findAll();
    }

    public List<CodeWorkType> getAllWorkTypes() {
        return codeWorkTypeRepository.findAll();
    }

    public List<CodeRule> getAllCodeRules(){
        return codeRulesRepository.findAll();
    }
    public List<CodeGender> getAllCodeGenders(){
        return codeGendersRepository.findAll();
    }

}
