package com.doan.AppTuyenDung.Repositories.AllCode;

import org.springframework.data.jpa.repository.JpaRepository;

import com.doan.AppTuyenDung.entity.CodeSalaryType;
import org.springframework.stereotype.Repository;
@Repository
public interface CodeSalaryTypeRepository extends JpaRepository<CodeSalaryType,String>{
    
}
