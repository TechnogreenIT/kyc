package com.tes.repository;

import java.util.List;
import java.util.TreeSet;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.tes.model.FilterUseNames;

@Repository
public interface FilterUseNameRepository extends JpaRepository<FilterUseNames, Long>
{
	@Override
	List<FilterUseNames> findAll();

	// @Query("SELECT fu.filterUseName FROM FilterUse fu LEFT JOIN fu.filters f WHERE f.filterType= :filterName")
	// public List<String> findFiltersUseByFilterName(@Param("filterName") String filterName);
}
