package com.doan.AppTuyenDung.Repositories.AllCode;

import org.springframework.data.jpa.repository.JpaRepository;

import com.doan.AppTuyenDung.entity.CodeJobType;
import org.springframework.stereotype.Repository;
@Repository
public interface CodeJobTypeRepository extends JpaRepository<CodeJobType,String>{
    
}
