package com.tes.repository.admin;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;
import com.tes.model.IndustryTypeList;

public interface IndustryTypeListRepository extends JpaRepository<IndustryTypeList, Long>
{

	@Override
	List<IndustryTypeList> findAll();

	@Override
	@SuppressWarnings("unchecked")
	IndustryTypeList save(IndustryTypeList industry_type_list);

	@Transactional
	// @Query(value="select p from CompanyProfile cp where cp.id='id' ",
	// nativeQuery=true)
	IndustryTypeList findOneByindustryTypeId(Integer id);
}
