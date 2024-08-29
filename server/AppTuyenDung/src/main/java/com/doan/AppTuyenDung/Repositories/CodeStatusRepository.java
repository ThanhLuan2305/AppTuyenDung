package com.doan.AppTuyenDung.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.doan.AppTuyenDung.entity.CodeStatus;

public interface CodeStatusRepository extends JpaRepository<CodeStatus, String> {
	CodeStatus findByCode(String code);
}
