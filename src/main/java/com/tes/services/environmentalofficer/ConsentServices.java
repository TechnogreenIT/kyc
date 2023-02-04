package com.tes.services.environmentalofficer;

import java.util.List;
import org.springframework.data.domain.Pageable;
import com.tes.model.Consent;
import com.tes.model.ConsentAmulgamation;

public interface ConsentServices
{

	public Consent save(Consent consent);

	public List<Consent> findByCmpId(int cmpId);

	public List<Consent> findByCmpIdAndCtoE(int cmpId);

	public List<Consent> findByCmpIdAndCtoO(int cmpId);

	public ConsentAmulgamation save(ConsentAmulgamation consentAmulgamation);

	public float findBySumTotalAreaCtoEs();

	public float findBySumTotalAreaCtoOp();

	public float findBySumTotalBuildAreaCtoEs();

	public float findBySumTotalBuildAreaCtoOp();

	public float findBySumTOpenSpaceAreaCtoEs();

	public float findBySumTOpenSpaceAreaCtoOp();

	public float findBySumTotalGreenAreaCtoOp();

	public float findBySumTotalGreenAreaCtoEs();

	public int findByConsType(String ConsType);

	public List<Object[]> findByConsCTO();

	public List<Object[]> findByConsByValidUpto(String todayDate);

	public int[] findByCtoEAndToday(String todaysDate);

	public int[] findByCtoOAndToday(String todaysDate);

	public int updateconsentextdate(String validUptoForEx, int consId);

	public List<Consent> findByCons();

	public List<Consent> findbyConsentId(int consentId);

	public int updateConsentByConsentId(int consentId, String issueDate, String validUpto,
			float grossCi, int noStaff, int noWorker, float totPlotArea, float totBuildArea, float totGreenArea, String file, String filePath);

	List<Consent> findConsentIdByDate(String selectedDate, Pageable pageable);

	public int consentMinYear();

	public int consentMinYearForEsr();

	public List<Consent> findByConsByDate(String selectedDate);

	public List<Consent> getIssueDateAndConsNoByIssueDate(String fromDate, String toDate, String today);

	List<Object[]> getListOfConsentByTodayDate(String today);

	public List<Consent> getConsentDataByIssueDate(String selectedDate);

	// //// mmmmm
	// public List<Consent> getConsentDataByIssueDate1(String dateTo, Pageable pageable);

	List<Consent> findLastAddedConsentByConsType(String consType, Pageable pageable);

	public List<Consent> findAllConsentByConsentType(String consentType);

	public List<Integer> findByDate(String dateTo, Pageable pageable);

	// public Integer checkDataPresent();

	public List<Consent> checkDataPresent(String consentType, Pageable pageable);

}
