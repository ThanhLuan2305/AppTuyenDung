package com.doan.AppTuyenDung.Services;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.doan.AppTuyenDung.Repositories.SkillRepository;
import com.doan.AppTuyenDung.entity.Skill;

@Service
public class SkillService{
    @Autowired
    private SkillRepository skillRepository;

    // public ResponseEntity<?> getAllSkillByJobCode(String categoryJobCode) {
    //     List<Skill> skills;
    //     if (categoryJobCode.equals("getAll")) {
    //         return ResponseEntity.ok(skills = skillRepository.findAll());
    //     } else {
    //         return ResponseEntity.ok(skills = skillRepository.findAllByCategoryJobCode(categoryJobCode));
    //     }
        
    // }

}
