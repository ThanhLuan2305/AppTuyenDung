package com.doan.AppTuyenDung.Repositories;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.doan.AppTuyenDung.entity.Skill;

public interface SkillRepository extends JpaRepository<Skill, Integer> {
	public List<Skill> findByCategoryJobCode(String code);
}
