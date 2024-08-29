package com.doan.AppTuyenDung.Services;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.doan.AppTuyenDung.DTO.Response.SkillResponse;
import com.doan.AppTuyenDung.Repositories.SkillRepository;
import com.doan.AppTuyenDung.entity.Skill;

@Service
public class SkillService {
	@Autowired
	private SkillRepository skillRepository;
	 public List<SkillResponse> GetSkillByCodeJob(String categoryJobCode) {
	        List<Skill> skills = skillRepository.findByCategoryJobCode(categoryJobCode);
	        
	        return skills.stream()
	            .map(skill -> mapToSkillResponse(skill))
	            .collect(Collectors.toList());
	    }

	    private SkillResponse mapToSkillResponse(Skill skill) {
	        SkillResponse response = new SkillResponse();
	        response.setId(skill.getId());
	        response.setName(skill.getName());
	        response.setCategoryJobCode(skill.getCategoryJobCode());
	        return response;
	    }

}
