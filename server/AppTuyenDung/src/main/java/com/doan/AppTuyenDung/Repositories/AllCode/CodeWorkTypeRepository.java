package com.doan.AppTuyenDung.Repositories.AllCode;

import org.springframework.data.jpa.repository.JpaRepository;

import com.doan.AppTuyenDung.entity.CodeWorkType;
import org.springframework.stereotype.Repository;
@Repository
public interface CodeWorkTypeRepository extends JpaRepository<CodeWorkType,String> {
    
}
