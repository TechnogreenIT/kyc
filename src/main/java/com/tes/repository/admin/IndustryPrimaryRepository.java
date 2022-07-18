package com.tes.repository.admin;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;
import com.tes.model.IndustryPrimary;

public interface IndustryPrimaryRepository extends JpaRepository<IndustryPrimary, Long>
{

	@Override
	@Query("SELECT ip FROM IndustryPrimary ip")
	List<IndustryPrimary> findAll();

	@Override
	@SuppressWarnings("unchecked")
	IndustryPrimary save(IndustryPrimary industry_primary);

	@Transactional
	// @Query(value="select p from CompanyProfile cp where cp.id='id' ",
	// nativeQuery=true)
	IndustryPrimary findOneByindustryPrimaryId(Integer id);

	@Query("SELECT ip FROM IndustryPrimary ip WHERE type='primary'")
	public List<IndustryPrimary> findPrimary();
}
