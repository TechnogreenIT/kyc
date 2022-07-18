package com.tes.repository.admin;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import com.tes.model.IndustryCategory;

public interface IndustryCategoryRepository extends JpaRepository<IndustryCategory, Long>
{

	@Override
	@Query("SELECT ic FROM IndustryCategory ic")
	List<IndustryCategory> findAll();

	@Override
	@SuppressWarnings("unchecked")
	IndustryCategory save(IndustryCategory industry_category);

}
