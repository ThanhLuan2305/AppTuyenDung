package com.doan.AppTuyenDung.Repositories.AllCode;

import com.doan.AppTuyenDung.entity.CodeJobLevel;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface CodeJobLevelRepository extends JpaRepository<CodeJobLevel,String> {
    
}
