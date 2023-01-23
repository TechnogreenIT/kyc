package com.tes.repository.thirdparty;

import java.util.List;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import com.tes.model.RegSewPoll;

public interface RegSewPollRepository extends JpaRepository<RegSewPoll, Long>
{

	@Override
	@SuppressWarnings("unchecked")
	RegSewPoll save(RegSewPoll regSewPoll);

	@Query("SELECT DISTINCT(EXTRACT(YEAR FROM sew.sampS)) FROM RegSewPoll sew ORDER BY sew.sampS ASC")
	public List<Integer> getYearFromSewage();

	@Query("SELECT DISTINCT sew.sampS FROM RegSewPoll sew ORDER BY sew.sampS ASC")
	public List<String> getYearFromSewageDate();

	@Query("SELECT DISTINCT(EXTRACT(MONTH FROM sew.sampS)) FROM RegSewPoll sew where (EXTRACT(YEAR FROM sew.sampS)) =:year ORDER BY sew.sampS ASC")
	public List<Integer> getMonthFromSewage(@Param("year") int year);

	@Query("SELECT DISTINCT(rs.sampS) FROM RegSewPoll rs where (EXTRACT(YEAR FROM rs.sampS)) =:year ORDER BY rs.sampS ASC")
	public List<String> getDateFromSewage(@Param("year") int year);

	// @Query(value="SELECT rs FROM RegSewPoll rs WHERE (EXTRACT(YEAR FROM rs.sampS)) = :yr AND rs.pollName = :pollname",nativeQuery=false)
	// public List<RegSewPoll> getRegSewagePollData(@Param("yr") int yr,@Param("pollname") String pollname);

	// @Query(value = "SELECT rs FROM RegSewPoll rs WHERE rs.pollName = :pollname and rs.sampS= :sampDate ORDER BY rs.regSewPollId DESC", nativeQuery = false)
	// List<RegSewPoll> getRegSewPollDatas(@Param("pollname") String pollname, @Param("sampDate") String sampDate);

	// @Query(value="SELECT rsp FROM RegSewPoll rsp WHERE rsp.pollName = :pollname AND rsp.sampS <= :todayDate AND EXTRACT(YEAR FROM rsp.sampS) = :yearValue AND EXTRACT(MONTH FROM rsp.sampS) = :monthValue ORDER BY rsp.sampS DESC",nativeQuery=false)
	// List<RegSewPoll> getRegSewPollDataBetweenDateByPollNameDay(@Param("pollname") String pollName, @Param("todayDate") String todayDate, @Param("yearValue") int yearValue, @Param("monthValue") int monthValue,Pageable pageable);

	// @Query(value="SELECT rsp FROM RegSewPoll rsp WHERE rsp.pollName = :pollname AND EXTRACT(YEAR FROM rsp.sampS) = :yearValue ORDER BY rsp.sampS DESC",nativeQuery=false)
	// List<RegSewPoll> getRegSewPollDataBetweenDateByPollNameYear(@Param("pollname") String pollName,@Param("yearValue") int yearValue,Pageable pageable);

	// @Query(value="SELECT rsp FROM RegSewPoll rsp WHERE rsp.pollName = :pollname AND EXTRACT(YEAR FROM rsp.sampS) = :yearValue AND EXTRACT(MONTH FROM rsp.sampS) = :monthValue ORDER BY rsp.sampS DESC",nativeQuery=false)
	// List<RegSewPoll> getRegSewPollDataBetweenDateByPollNameMonth(@Param("pollname") String pollName, @Param("yearValue") int yearValue, @Param("monthValue") int monthValue,Pageable pageable);

	@Query(value = "SELECT DISTINCT(rs.sampS) FROM RegSewPoll rs WHERE rs.sampS <= :today AND EXTRACT(YEAR FROM rs.sampS)= :yearValue ORDER BY rs.sampS DESC", nativeQuery = false)
	List<String> getRegSewPollDates(@Param("today") String today, @Param("yearValue") int yearValue, Pageable pageable);

	@Query(value = "SELECT DISTINCT(rs.sampS) FROM RegSewPoll rs WHERE rs.sampS <= :today AND EXTRACT(YEAR FROM rs.sampS)= :yearValue AND EXTRACT(MONTH FROM rs.sampS)= :monthValue ORDER BY rs.sampS DESC", nativeQuery = false)
	List<String> getRegSewPollDates(@Param("today") String today, @Param("yearValue") int yearValue, @Param("monthValue") int monthValue, Pageable pageable);

	@Query(value = "SELECT DISTINCT(rs.sampS) FROM RegSewPoll rs WHERE rs.sampS= :today ORDER BY rs.sampS DESC", nativeQuery = false)
	List<String> getRegSewPollDates(@Param("today") String today, Pageable pageable);

	// @Query(value="SELECT rsp.ouConsS FROM RegSewPoll rsp WHERE rsp.pollName = :pollname AND rsp.sampS = :todayDate",nativeQuery=false)
	// Float getRegSewPollouConsEDatePollName(@Param("pollname") String pollName, @Param("todayDate") String todayDate);

	// @Query("SELECT COALESCE(AVG(rs.ouConsS),0) FROM RegSewPoll rs where rs.pollName = :pollName AND rs.sampS BETWEEN :dateFrom and :dateTo")
	// Float getSewAvgOuConsEByYear(@Param("pollName") String pollName,@Param("dateFrom") String dateFrom,@Param("dateTo") String dateTo);

	@Query(value = "SELECT rs FROM RegSewPoll rs WHERE  rs.sampS = :date", nativeQuery = false)
	public List<RegSewPoll> getRegSewagePollDataDate(@Param("date") String date);

	@Query("SELECT DISTINCT(rs.sampS) FROM RegSewPoll rs  ORDER BY rs.sampS DESC")
	List<String> getRegSewPollDate(Pageable pageable);

	@Query(value = "SELECT rsf FROM RegSewPoll rsf WHERE rsf.wastewaterTreatment.wastewaterTreatmentId=:id AND rsf.sampS =:date")
	List<RegSewPoll> getRegSewPollDataDateAndId(@Param("id") int id, @Param("date") String date);

	@Query(value = "SELECT rs FROM RegSewPoll rs WHERE  rs.sampS = :date AND rs.wastewaterTreatment.label=:labelName", nativeQuery = false)
	public List<RegSewPoll> getRegSewPollDatasbyDate(@Param("labelName") String labelName, @Param("date") String date);

	@Query(value = "SELECT AVG(res.ouConsS) FROM RegSewPoll res LEFT JOIN res.waterSewPollOp wop  LEFT JOIN wop.waterSewPoll wsp  WHERE wsp.pollName= :pollName AND  res.sampS BETWEEN :dateFrom AND :dateTo")
	Float getSewAvgOuConsByDate(@Param("pollName") String pollName, @Param("dateFrom") String dateFrom, @Param("dateTo") String dateTo);

	@Query("SELECT rs.regSewPollId FROM RegSewPoll rs  ORDER BY rs.regSewPollId DESC")
	List<RegSewPoll> checkRegSewPollData(Pageable pageable);

	@Query(value = "SELECT AVG(res.ouConsS) FROM RegSewPoll res LEFT JOIN res.waterSewPollOp wop  LEFT JOIN wop.waterSewPoll wsp  WHERE wsp.pollName= :pollName AND  (EXTRACT(YEAR FROM res.sampS)) =:year")
	Float getSewAvgOuConsByDate(@Param("pollName") String pollName, @Param("year") int year);

}
