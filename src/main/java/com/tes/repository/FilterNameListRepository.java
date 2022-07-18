package com.tes.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.tes.model.FilterNameList;

@Repository
public interface FilterNameListRepository extends JpaRepository<FilterNameList, Long>
{
	@Override
	List<FilterNameList> findAll();

	@Query("SELECT count(id)>0 FROM FilterNameList WHERE FilterName=:filterName")
	boolean getFilterIdByName(@Param("filterName") String filterName);

}
