package com.doan.AppTuyenDung.Repositories;

import jakarta.persistence.criteria.CriteriaBuilder;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import com.doan.AppTuyenDung.DTO.Response.PostJobTypeCountDTO;
import com.doan.AppTuyenDung.entity.Post;

@Repository
public interface PostRepository extends JpaRepository<Post, Integer>, JpaSpecificationExecutor<Post>  {



}