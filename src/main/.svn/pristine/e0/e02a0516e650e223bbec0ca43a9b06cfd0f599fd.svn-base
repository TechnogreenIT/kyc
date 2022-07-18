package com.tes.repository.environmentalofficer.waterinventory;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.tes.model.Prefilter;

@Repository
public interface PrefilterRepository extends JpaRepository<Prefilter, Integer>
{

	@SuppressWarnings("unchecked")
	Prefilter save(Prefilter prefilter);

	@Query("SELECT pf FROM  Prefilter pf")
	public List<Prefilter> findAllPrefilters();

	@Query("select p from  Prefilter p where p.waterSource.waterSourceId= :waterSourceId and p.isPrefilter=true")
	public List<Prefilter> getAllActivePrefilterData(@Param("waterSourceId") int waterSourceId);

	@Query("select p from Prefilter p left join p.waterSource ws where p.isPrefilter=true")
	public List<Prefilter> getAllIdAndIsPrifilter();

	@Query("select p from Prefilter p left join p.waterSource ws where p.prefilterId=:pid")
	public List<Prefilter> getPrefiterById(@Param("pid") int pid);
}
