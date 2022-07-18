package com.tes.repository.thirdparty;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.tes.model.AmbientPollData;

@Repository
public interface AmbientPollDataRepository extends JpaRepository<AmbientPollData, Long>
{

	@Override
	@SuppressWarnings("unchecked")
	AmbientPollData save(AmbientPollData regambientpoll);

	@Query(value = "SELECT a FROM AmbientPollData a WHERE a.regAmbientPoll.regAmbientPollId= :regambientId")
	List<AmbientPollData> getAmbientPollData(@Param("regambientId") int regambientId);
}
