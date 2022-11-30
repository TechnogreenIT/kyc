package com.tes.repository.environmentalofficer;

import java.util.List;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import com.tes.model.Consent;

@Repository
public interface ConsentRepository extends JpaRepository<Consent, Long>
{

	@Override
	@SuppressWarnings("unchecked")
	Consent save(Consent consent);

	@Query("SELECT NEW Consent(cn.consentId,cn.consNo) FROM Consent cn,CompanyProfile cmp WHERE cn.companyProfile.companyProfileId=cmp.companyProfileId AND cn.companyProfile.companyProfileId=:cmpId")
	public List<Consent> findByCmpId(@Param("cmpId") int cmpId);

	@Query("SELECT NEW Consent(cn.consentId,cn.consNo) FROM Consent cn,CompanyProfile cmp WHERE cn.companyProfile.companyProfileId=cmp.companyProfileId AND cn.companyProfile.companyProfileId=:cmpId AND cn.consType='Consent to Establish'")
	public List<Consent> findByCmpIdAndCtoE(@Param("cmpId") int cmpId);

	@Query("SELECT NEW Consent(cn.consentId,cn.consNo) FROM Consent cn,CompanyProfile cmp WHERE cn.companyProfile.companyProfileId=cmp.companyProfileId AND cn.companyProfile.companyProfileId=:cmpId AND cn.consType='Consent to Operate'")
	public List<Consent> findByCmpIdAndCtoO(@Param("cmpId") int cmpId);

	@Query("SELECT COALESCE(SUM(c.totPlotArea),0) FROM Consent c WHERE c.consType='Consent to Establish'")
	Float findBySumTotalAreaCtoEs();

	@Query("SELECT COALESCE(SUM(c.totPlotArea),0) FROM Consent c WHERE c.consType='Consent to Operate'")
	Float findBySumTotalAreaCtoOp();

	@Query("SELECT COALESCE(SUM(c.totBuildArea),0) FROM Consent c WHERE c.consType='Consent to Establish'")
	Float findBySumTotalBuildAreaCtoEs();

	@Query("SELECT COALESCE(SUM(c.totBuildArea),0) FROM Consent c WHERE c.consType='Consent to Operate'")
	Float findBySumTotalBuildAreaCtoOp();

	@Query("SELECT COALESCE(SUM(c.openSpaceAva),0) FROM Consent c WHERE c.consType='Consent to Establish'")
	Float findBySumTOpenSpaceAreaCtoEs();

	@Query("SELECT COALESCE(SUM(c.openSpaceAva),0) FROM Consent c WHERE c.consType='Consent to Operate'")
	Float findBySumTOpenSpaceAreaCtoOp();

	@Query("SELECT COALESCE(SUM(c.totGreenArea),0) FROM Consent c WHERE c.consType='Consent to Establish'")
	Float findBySumTotalGreenAreaCtoOp();

	@Query("SELECT COALESCE(SUM(c.totGreenArea),0) FROM Consent c WHERE c.consType='Consent to Operate'")
	Float findBySumTotalGreenAreaCtoEs();

	@Query("SELECT COUNT(c.consentId) FROM Consent c WHERE c.consType =:consType")
	public int findByConsType(@Param("consType") String consType);

	@Query("SELECT c.consentId,c.consNo,c.validUpto,c.grossCi,c.issueDate  FROM Consent c  WHERE c.consType ='Consent to Operate'  AND c.consStatus != 'EXPIRED'")
	List<Object[]> findByConsCTO();

	@Query("SELECT c.consentId,c.consNo,c.validUpto FROM Consent c WHERE c.consType = 'Consent to Operate'  AND c.validUpto <= :todayDate  AND c.consStatus != 'EXPIRED'")
	// @Query(value="SELECT c.id,c.cons_no,c.valid_upto FROM consent c left join consent_extended_date ce on c.id=ce.consent_id where c.cons_type = 'Consent to Operate' AND c.cons_status != 'EXPIRED' AND (ce.valid_upto <= :todayDate And c.valid_upto <= :todayDate)", nativeQuery=true)
	List<Object[]> findByConsByValidUpto(@Param("todayDate") String todayDate);

	@Query("SELECT c.consentId FROM Consent c WHERE c.consType='Consent to Establish' AND c.validUpto >= :todaysDate")
	int[] findByCtoEAndToday(@Param("todaysDate") String todaysDate);

	@Query("SELECT c.consentId FROM Consent c WHERE c.consType='Consent to Operate' AND c.validUpto >= :todaysDate")
	int[] findByCtoOAndToday(@Param("todaysDate") String todaysDate);

	@Query("SELECT c.consentId,c.consNo,c.validUpto,c.grossCi,c.issueDate FROM Consent c WHERE c.consType = 'Consent to Operate' AND c.consStatus != 'EXPIRED'")
	List<Consent> findByCons();

	@Query("SELECT NEW Consent(c.consNo,c.consType,c.issueDate,c.validUpto,c.grossCi,c.noStaff,c.noWorker,c.totPlotArea,c.totBuildArea,c.totGreenArea,c.consentFilePath,c.consentFileName) FROM Consent c WHERE c.consentId= :consentId")
	List<Consent> findbyConsentId(@Param("consentId") int consentId);

	@Transactional
	@Modifying
	@Query("UPDATE Consent c SET c.issueDate = :issueDate ,c.validUpto = :validUpto ,c.grossCi = :grossCi ,c.noStaff = :noStaff ,c.noWorker = :noWorker ,c.totPlotArea = :totPlotArea ,c.totBuildArea = :totBuildArea ,c.totGreenArea = :totGreenArea,c.consentFileName = :file, c.consentFilePath = :filePath WHERE c.consentId = :consentId")
	int updateConsentByConsentId(@Param("consentId") int consentId, @Param("issueDate") String issueDate, @Param("validUpto") String validUpto, @Param("grossCi") float grossCi, @Param("noStaff") int noStaff, @Param("noWorker") int noWorker,
			@Param("totPlotArea") float totPlotArea, @Param("totBuildArea") float totBuildArea, @Param("totGreenArea") float totGreenArea, @Param("file") String file, @Param("filePath") String filePath);

	@Query("SELECT c.consentId FROM Consent c WHERE c.issueDate <= :selectedDate  AND c.consType='Consent to Operate'  AND c.consStatus !='Expired'  ORDER BY c.consentId DESC")
	List<Consent> findConsentIdByDate(@Param("selectedDate") String selectedDate, Pageable pageable);

	@Query("SELECT coalesce(MIN(EXTRACT(YEAR FROM c.issueDate)),0)  FROM Consent c WHERE c.consStatus !='Expired'")
	public int consentMinYear();

	@Query("SELECT coalesce(MIN(EXTRACT(YEAR FROM c.issueDate)),0)  FROM Consent c WHERE c.consStatus !='Expired' and c.consType='Consent to Operate'")
	public int consentMinYearForEsr();

	@Query("SELECT c.consentId,c.consNo,c.validUpto,c.grossCi,c.issueDate FROM Consent c WHERE c.consType = 'Consent to Operate' AND c.consStatus != 'EXPIRED' AND c.issueDate <= :selectedDate")
	List<Consent> findByConsByDate(@Param("selectedDate") String selectedDate);

	@Query("SELECT NEW Consent(c.consNo, c.issueDate) FROM Consent c WHERE c.issueDate between :fromDate AND :toDate AND c.validUpto>:today AND c.consType='Consent to Operate' ORDER BY c.issueDate ASC")
	List<Consent> getIssueDateAndConsNoByIssueDate(@Param("fromDate") String fromDate, @Param("toDate") String toDate, @Param("today") String today);

	@Query("SELECT c.consentId,c.consNo FROM Consent c LEFT JOIN c.consentExtendedDate ce WHERE c.consType='Consent to Operate' AND c.consStatus!= 'EXPIRED' AND (c.validUpto >= :today OR ce.validUpto >= :today) ORDER BY c.consentId DESC")
	List<Object[]> getListOfConsentByTodayDate(@Param("today") String today);

	@Query("SELECT DISTINCT NEW Consent(c.consentId,c.consNo,c.validUpto,c.grossCi,c.issueDate) FROM Consent c WHERE c.consType = 'Consent to Operate' AND c.consStatus != 'EXPIRED' AND c.issueDate <=:selectedDate")
	List<Consent> getConsentDataByIssueDate(@Param("selectedDate") String selectedDate);

	// // // mmmm
	// @Query("SELECT DISTINCT NEW Consent(c.consentId,c.consNo,c.validUpto,c.grossCi,c.issueDate) FROM Consent c WHERE c.consType = 'Consent to Operate' AND c.consStatus != 'EXPIRED' AND c.issueDate <=:dateTo ORDER BY c.consentId DESC")
	// List<Consent> getConsentDataByIssueDate1(@Param("dateTo") String dateTo, Pageable pageable);

	@Transactional
	@Modifying
	@Query(value = "UPDATE consent c SET c.valid_upto = :validUptoForEx WHERE c.id = :consId", nativeQuery = true)
	int updateconsentextdate(@Param("validUptoForEx") String validUptoForEx, @Param("consId") int consId);

	@Query("SELECT c FROM Consent c WHERE c.consType= :consType  AND c.consStatus !='Expired'  ORDER BY c.consentId DESC")
	List<Consent> findLastAddedConsentByConsType(@Param("consType") String consType, Pageable pageable);

	@Query("SELECT NEW Consent(c.consentId,c.consNo) FROM Consent c WHERE c.consType= :consType  AND c.consStatus !='Expired'")
	List<Consent> findAllConsentByConsentType(@Param("consType") String consType);

	@Query("SELECT c.consentId FROM Consent c WHERE c.consType='Consent to Operate' AND c.issueDate <=:dateTo ORDER BY c.consentId DESC")
	List<Integer> findByDate(@Param("dateTo") String dateTo, Pageable pageable);
}
