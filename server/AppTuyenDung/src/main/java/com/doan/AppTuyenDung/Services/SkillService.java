package com.doan.AppTuyenDung.Services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.doan.AppTuyenDung.Repositories.SkillRepository;
import com.doan.AppTuyenDung.entity.Skill;

@Service
public class SkillService {
	@Autowired
	private SkillRepository skillRepository;
	public List<Skill> GetSkillByCodeJob(String code) {
		List<Skill> lstSkill = skillRepository.findByCategoryJobCode(code);
		List<Skill> lstSkillRs = new ArrayList<Skill>();
		for(Skill sk : lstSkill) {
			lstSkillRs.add(sk);
		}
		return lstSkillRs;
	}
}
