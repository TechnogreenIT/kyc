package com.tes.repository.thirdparty;

import java.util.List;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import com.tes.model.RegEffPoll;

public interface RegEffPollRepository extends JpaRepository<RegEffPoll, Long>
{

	@Override
	@SuppressWarnings("unchecked")
	RegEffPoll save(RegEffPoll regEffPoll);

	@Query(value = "SELECT DISTINCT(EXTRACT(YEAR FROM eff.sampE)) FROM RegEffPoll eff ORDER BY eff.sampE ASC")
	public List<Integer> getYearFromEffluent();

	@Query(value = "SELECT DISTINCT eff.sampE FROM RegEffPoll eff ORDER BY eff.sampE ASC")
	public List<String> getYearFromEffluentDate();

	@Query(value = "SELECT DISTINCT(EXTRACT(MONTH FROM rs.sampE)) FROM RegEffPoll rs where (EXTRACT(YEAR FROM rs.sampE)) = :year ORDER BY rs.sampE ASC")
	public List<Integer> getMonthFromEffluent(@Param("year") int year);

	@Query("SELECT DISTINCT(rs.sampE) FROM RegEffPoll rs where (EXTRACT(YEAR FROM rs.sampE)) = :year ORDER BY rs.sampE ASC")
	public List<String> getDateForEffluent(@Param("year") int year);

	// @Query(value = "SELECT ref FROM RegEffPoll ref WHERE (EXTRACT(YEAR FROM ref.sampE)) =:yr AND ref.pollName = :PollName")
	// public List<RegEffPoll> getRegEffPollData(@Param("year") int year, @Param("PollName") String PollName);

	// String getAvgRegEffOuConsE = "SELECT AVG(ou_cons_e) FROM reg_eff_poll WHERE poll_name = :pollName AND EXTRACT(YEAR FROM samp_e) = :esrYear OR EXTRACT(YEAR FROM samp_e) = :esrYear1 AND EXTRACT(MONTH FROM samp_e) = :esrMonth";

	// @Query(value = getAvgRegEffOuConsE, nativeQuery = true)
	// Float getAvgRegEffOuConsE(@Param("pollName") String pollName, @Param("esrYear") String esrYear, @Param("esrYear1") String esrYear1, @Param("esrMonth") String esrMonth);

	String getRegEffReasonByName = "SELECT reason FROM reg_eff_poll WHERE poll_name = :pollName AND EXTRACT(YEAR FROM samp_e) = :esrYear AND EXTRACT(MONTH FROM samp_e) =:esrMonth  AND reason !='NA' ORDER BY rand() LIMIT 1";

	@Query(value = getRegEffReasonByName, nativeQuery = true)
	String getRegEffReasonByName(@Param("pollName") String pollName, @Param("esrYear") String esrYear, @Param("esrMonth") String esrMonth);

	// @Query(value="SELECT re FROM RegEffPoll re WHERE re.pollName = :pollName ORDER BY re.regEffPollId DESC")
	// List<RegEffPoll> getRegEffPollDatas(@Param("pollName") String pollName, Pageable pageable);

	// @Query(value="SELECT re FROM RegEffPoll re WHERE re.pollName = :pollname AND re.sampE <= :todayDate AND EXTRACT(YEAR FROM re.sampE) = :yearValue AND EXTRACT(MONTH FROM re.sampE) = :monthValue ORDER BY re.regEffPollId DESC")
	// List<RegEffPoll> getRegEffPollDataBetweenDateByPollName(@Param("pollname") String pollName, @Param("todayDate") String todayDate,@Param("yearValue") int yearValue,@Param("monthValue") int monthValue,Pageable pageable);

	// @Query(value="SELECT re FROM RegEffPoll re WHERE re.pollName = :pollname AND EXTRACT(YEAR FROM re.sampE) = :yearValue ORDER BY re.regEffPollId DESC")
	// List<RegEffPoll> getRegEffPollDataBetweenDateByPollName(@Param("pollname") String pollName, @Param("yearValue") int yearValue,Pageable pageable);

	// @Query(value="SELECT re FROM RegEffPoll re WHERE re.pollName = :pollname AND EXTRACT(YEAR FROM re.sampE) = :yearValue AND EXTRACT(MONTH FROM re.sampE) = :monthValue ORDER BY re.regEffPollId DESC")
	// List<RegEffPoll> getRegEffPollDataBetweenDateByPollName(@Param("pollname") String pollName,@Param("yearValue") int yearValue,@Param("monthValue") int monthValue,Pageable pageable);

	@Query(value = "SELECT DISTINCT(re.sampE) FROM RegEffPoll re WHERE re.sampE <= :today AND EXTRACT(YEAR FROM re.sampE)= :yearValue ORDER BY re.sampE DESC")
	List<String> getRegEffPollDates(@Param("today") String today, @Param("yearValue") int yearValue, Pageable pageable);

	@Query(value = "SELECT DISTINCT(re.sampE) FROM RegEffPoll re WHERE re.sampE <= :today AND EXTRACT(YEAR FROM re.sampE)= :yearValue AND EXTRACT(MONTH FROM re.sampE)= :monthValue ORDER BY re.sampE DESC")
	List<String> getRegEffPollDates(@Param("today") String today, @Param("yearValue") int yearValue, @Param("monthValue") int monthValue, Pageable pageable);

	@Query(value = "SELECT DISTINCT(re.sampE) FROM RegEffPoll re WHERE re.sampE= :today ORDER BY re.sampE DESC")
	List<String> getRegEffPollDates(@Param("today") String today, Pageable pageable);

	// @Query(value="SELECT re.ouConsE FROM RegEffPoll re WHERE re.pollName = :pollname AND re.sampE = :todayDate")
	// Float getRegEffPollouConsEDatePollName(@Param("pollname") String pollName, @Param("todayDate") String todayDate);

	// @Query(value = "SELECT COALESCE(AVG(ouConsE),0) FROM RegEffPoll rep WHERE rep.pollName = :pollName AND rep.subE BETWEEN :dateFrom AND :dateTo")
	// Float getAvgOuConsEByYear(@Param("pollName") String pollName,@Param("dateFrom") String dateFrom,@Param("dateTo") String dateTo);

	@Query(value = "SELECT ref FROM RegEffPoll ref WHERE  ref.sampE =:date")
	public List<RegEffPoll> getRegEffPollDataDate(@Param("date") String date);

	@Query(value = "SELECT ref FROM RegEffPoll ref WHERE ref.wastewaterTreatment.wastewaterTreatmentId=:id AND ref.sampE =:date")
	List<RegEffPoll> getRegEffPollDataDateAndId(@Param("id") int id, @Param("date") String date);

	@Query("SELECT DISTINCT(ref.sampE) FROM RegEffPoll ref  ORDER BY ref.sampE DESC")
	List<String> getRegEffPollDate(Pageable pageable);

	@Query(value = "SELECT ref FROM RegEffPoll ref WHERE  ref.sampE = :date AND ref.wastewaterTreatment.label=:labelName", nativeQuery = false)
	public List<RegEffPoll> getRegEffPollDatasbyDate(@Param("labelName") String labelName, @Param("date") String date);

	@Query(value = "SELECT AVG(ref.ouConsE) FROM RegEffPoll ref LEFT JOIN ref.wateriePollutantOp wop  LEFT JOIN wop.wateriePollutant wp  WHERE wp.pollName= :pollName AND  ref.sampE BETWEEN :dateFrom AND :dateTo")
	Float getAvgRegEffOuConsE(@Param("pollName") String pollName, @Param("dateTo") String dateTo, @Param("dateFrom") String dateFrom);

	@Query("SELECT (ref.regEffPollId) FROM RegEffPoll ref  ORDER BY ref.regEffPollId DESC")
	List<RegEffPoll> checkRegEffPollData(Pageable pageable);

	// @Query(value = "SELECT AVG(ref.ouConsE) FROM RegEffPoll ref LEFT JOIN ref.wateriePollutantOp wop LEFT JOIN wop.wateriePollutant wp WHERE wp.pollName= :pollName AND ref.sampE BETWEEN :dateFrom AND =:year")

	@Query(value = "SELECT AVG(ref.ouConsE) FROM RegEffPoll ref LEFT JOIN ref.wateriePollutantOp wop  LEFT JOIN wop.wateriePollutant wp  WHERE wp.pollName= :pollName AND  (EXTRACT(YEAR FROM ref.sampE)) =:year")
	Float getEffPollAvg(@Param("pollName") String pollName, @Param("year") int year);
}
