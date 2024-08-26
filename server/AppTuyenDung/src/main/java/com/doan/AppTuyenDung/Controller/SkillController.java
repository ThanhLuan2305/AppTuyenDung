package com.doan.AppTuyenDung.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.doan.AppTuyenDung.DTO.Response.ApiResponse;
import com.doan.AppTuyenDung.Services.SkillService;
import com.doan.AppTuyenDung.entity.Skill;
@RestController
@RequestMapping
public class SkillController {
	@Autowired
	private SkillService service;
	@GetMapping("/public/skill/get-all-skill-by-job-code")
	public ApiResponse<List<Skill>> getAllSkillJobCode (@RequestParam (required = false) String categoryJobCode) {
		ApiResponse<List<Skill>> lstSkill = new ApiResponse<>();
		lstSkill.setResult(service.GetSkillByCodeJob(categoryJobCode));
		return lstSkill;
	}
}
