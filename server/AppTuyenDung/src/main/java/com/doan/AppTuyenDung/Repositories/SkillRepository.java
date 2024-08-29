package com.doan.AppTuyenDung.Repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.doan.AppTuyenDung.DTO.SkillsCateGoryJobCodeDTO;
import com.doan.AppTuyenDung.entity.Skill;
//( :addressCode is null or ac6.code = :addressCode )
@Repository
public interface SkillRepository extends JpaRepository<Skill, Integer>{
    // @Query(value= " SELECT id ,name ,category_job_code FROM Skills " + //
    //             " WHERE category_job_code = :categoryJobCode ")
    // List<Skill> findAllByCategoryJobCode(@Param("categoryJobCode") String categoryJobCode);
    
}
