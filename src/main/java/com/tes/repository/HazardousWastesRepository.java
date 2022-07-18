package com.tes.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.tes.model.HazardousWastes;

@Repository
public interface HazardousWastesRepository extends JpaRepository<HazardousWastes, Long>
{

	@Query("SELECT hw.categoryDesc FROM HazardousWastes hw WHERE hw.categoryNumber= :categoryNumber")
	String getGategoryTypeByCatNumber(@Param("categoryNumber") String categoryNumber);

	@Query("SELECT hw.categoryNumber FROM HazardousWastes hw WHERE hw.categoryDesc= :categoryDesc")
	String getHzNumberByCatDesc(@Param("categoryDesc") String categoryDesc);

}
