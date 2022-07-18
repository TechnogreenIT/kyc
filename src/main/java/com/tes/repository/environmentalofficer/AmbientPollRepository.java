package com.tes.repository.environmentalofficer;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.tes.model.Ambient;
import com.tes.model.AmbientPoll;

@Repository
public interface AmbientPollRepository extends JpaRepository<AmbientPoll, Long>
{

	@Override
	@SuppressWarnings("unchecked")
	AmbientPoll save(AmbientPoll ambientPoll);

	@Query("SELECT NEW AmbientPoll(a.ambient,a.pollName,a.unit) FROM AmbientPoll a WHERE a.ambient.ambientId= :ambientId")
	public List<AmbientPoll> findAmbientInfo(@Param("ambientId") Integer ambientId);

	@Query("SELECT amp FROM AmbientPoll amp WHERE amp.ambient.ambientId = :ambientId")
	List<AmbientPoll> findByAmbientId(@Param("ambientId") int ambientId);

	@Transactional
	List<AmbientPoll> deleteByAmbient(Ambient ambientId);

	@Transactional
	List<AmbientPoll> deleteByAmbientPollId(int ambientPollId);

}
