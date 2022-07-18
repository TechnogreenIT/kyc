package com.tes.repository.thirdparty;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.tes.model.RegAmbientPoll;

public interface RegAmbientPollRepository extends JpaRepository<RegAmbientPoll, Long>
{

	@Override
	@SuppressWarnings("unchecked")
	RegAmbientPoll save(RegAmbientPoll regambientpoll);

	@Query("SELECT DISTINCT(EXTRACT(YEAR FROM amb.sampAmb)) FROM RegAmbientPoll amb ORDER BY amb.sampAmb ASC")
	public List<Integer> getYearFromAmbient();

	@Query("SELECT DISTINCT amb.sampAmb FROM RegAmbientPoll amb ORDER BY amb.sampAmb ASC")
	public List<String> getYearFromAmbientDate();

	@Query("SELECT DISTINCT(EXTRACT(MONTH FROM rs.sampAmb)) FROM RegAmbientPoll rs where (EXTRACT(YEAR FROM rs.sampAmb)) = :yr ORDER BY rs.sampAmb ASC")
	public List<String> getMonthFromAmbient(@Param("yr") int yr);

	@Query("SELECT DISTINCT(rs.sampAmb) FROM RegAmbientPoll rs where (EXTRACT(YEAR FROM rs.sampAmb)) = :year ORDER BY rs.sampAmb ASC")
	public List<String> getDateFromAmbient(@Param("year") int year);

	@Query("SELECT DISTINCT(EXTRACT(MONTH FROM rs.sampAmb)) FROM RegAmbientPoll rs where (EXTRACT(YEAR FROM rs.sampAmb)) = :yr ORDER BY rs.sampAmb ASC")
	public List<Integer> getMonthFromAmbients(@Param("yr") int yr);

	// @Query("SELECT DISTINCT(rs.sampAmb) FROM RegAmbientPoll rs,Ambient a where rs.ambient.ambientId=a.ambientId and a.consentToOperate='Yes' and (EXTRACT(YEAR FROM rs.sampAmb)) = :yr ORDER BY rs.sampAmb ASC")
	@Query("SELECT (rs.sampAmb) FROM RegAmbientPoll rs,AmbientOp a WHERE rs.ambient.ambientId=a.ambient.ambientId AND (EXTRACT(YEAR FROM rs.sampAmb)) = :yr ORDER BY rs.sampAmb ASC")
	public List<String> getDateForAmbient(@Param("yr") int yr);

	@Query(value = "SELECT rs FROM RegAmbientPoll rs WHERE rs.ambient.ambientId= :ambientId AND rs.regAmbientPollId= :regAmbientPollId")
	public List<RegAmbientPoll> findByAmbientId(@Param("ambientId") int ambientId, @Param("regAmbientPollId") int regAmbientPollId);

	@Query(value = "SELECT rap.regAmbientPollId FROM RegAmbientPoll rap WHERE rap.ambient.ambientId= :ambientId")
	public List<Integer> getRegAmbientId(@Param("ambientId") int ambientId);

	@Query(value = "SELECT rs FROM RegAmbientPoll rs WHERE rs.sampAmb= :date")
	public List<RegAmbientPoll> findByAmbientDate(@Param("date") String date);

}
