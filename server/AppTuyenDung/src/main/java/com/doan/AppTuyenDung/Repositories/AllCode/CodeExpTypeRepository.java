package com.doan.AppTuyenDung.Repositories.AllCode;

import org.springframework.data.jpa.repository.JpaRepository;

import com.doan.AppTuyenDung.entity.CodeExpType;
import org.springframework.stereotype.Repository;
@Repository
public interface CodeExpTypeRepository extends JpaRepository<CodeExpType,String>{
    
}
