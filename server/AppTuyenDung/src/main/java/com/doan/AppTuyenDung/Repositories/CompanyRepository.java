package com.doan.AppTuyenDung.Repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.doan.AppTuyenDung.entity.Company;

public interface CompanyRepository extends JpaRepository<Company,Integer>{
    boolean existsByName(String name);
    Optional<Company> findById(Integer id);
}
