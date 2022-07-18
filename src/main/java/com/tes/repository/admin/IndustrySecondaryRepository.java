package com.tes.repository.admin;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import com.tes.model.IndustrySecondary;

public interface IndustrySecondaryRepository extends JpaRepository<IndustrySecondary, Long>
{

	@Override
	@Query("SELECT ins FROM IndustrySecondary ins")
	List<IndustrySecondary> findAll();

	@Override
	@SuppressWarnings("unchecked")
	IndustrySecondary save(IndustrySecondary industry_secondary);

	@Query("SELECT ins FROM IndustrySecondary ins WHERE ins.type='secondary' ")
	public List<IndustrySecondary> findSecondary();
}
