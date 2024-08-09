package com.doan.AppTuyenDung.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.doan.AppTuyenDung.entity.Company;

public interface CompanyRepository extends JpaRepository<Company,Integer>{
    boolean existsByName(String name);

    
  
}
