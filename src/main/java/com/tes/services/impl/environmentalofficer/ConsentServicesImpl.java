package com.tes.services.impl.environmentalofficer;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import com.tes.model.Consent;
import com.tes.model.ConsentAmulgamation;
import com.tes.repository.environmentalofficer.ConsentAmulgamationRepository;
import com.tes.repository.environmentalofficer.ConsentRepository;
import com.tes.repository.environmentalofficer.UnitRepository;
import com.tes.services.environmentalofficer.ConsentServices;

@Service
public class ConsentServicesImpl implements ConsentServices
{

	@Autowired
	ConsentRepository consentRepository;

	@Autowired
	ConsentAmulgamationRepository consentAmulgamationRepository;

	@Autowired
	UnitRepository unitRepository;

	@Override
	public Consent save(Consent consent)
	{
		return consentRepository.save(consent);
	}

	@Override
	public List<Consent> findByCmpId(int cmpId)
	{
		return consentRepository.findByCmpId(cmpId);
	}

	@Override
	public List<Consent> findByCmpIdAndCtoE(int cmpId)
	{
		return consentRepository.findByCmpIdAndCtoE(cmpId);
	}

	@Override
	public List<Consent> findByCmpIdAndCtoO(int cmpId)
	{
		return consentRepository.findByCmpIdAndCtoO(cmpId);
	}

	@Override
	public ConsentAmulgamation save(ConsentAmulgamation consentAmulgamation)
	{
		return consentAmulgamationRepository.save(consentAmulgamation);
	}

	@Override
	public int updateconsentextdate(String validUptoForEx, int consId)
	{
		return consentRepository.updateconsentextdate(validUptoForEx, consId);
	}

	@Override
	public float findBySumTotalAreaCtoEs()
	{
		return consentRepository.findBySumTotalAreaCtoEs();
	}

	@Override
	public float findBySumTotalAreaCtoOp()
	{
		return consentRepository.findBySumTotalAreaCtoOp();
	}

	@Override
	public float findBySumTotalBuildAreaCtoEs()
	{
		return consentRepository.findBySumTotalBuildAreaCtoEs();
	}

	@Override
	public float findBySumTotalBuildAreaCtoOp()
	{
		return consentRepository.findBySumTotalBuildAreaCtoOp();
	}

	@Override
	public float findBySumTOpenSpaceAreaCtoEs()
	{
		return consentRepository.findBySumTOpenSpaceAreaCtoEs();
	}

	@Override
	public float findBySumTOpenSpaceAreaCtoOp()
	{
		return consentRepository.findBySumTOpenSpaceAreaCtoOp();
	}

	@Override
	public float findBySumTotalGreenAreaCtoOp()
	{
		return consentRepository.findBySumTotalGreenAreaCtoOp();
	}

	@Override
	public float findBySumTotalGreenAreaCtoEs()
	{
		return consentRepository.findBySumTotalGreenAreaCtoEs();
	}

	@Override
	public int findByConsType(String consType)
	{
		return consentRepository.findByConsType(consType);
	}

	@Override
	public List<Object[]> findByConsCTO()
	{
		return consentRepository.findByConsCTO();
	}

	@Override
	public List<Object[]> findByConsByValidUpto(String todayDate)
	{
		return consentRepository.findByConsByValidUpto(todayDate);
	}

	@Override
	public int[] findByCtoEAndToday(String todaysDate)
	{
		return consentRepository.findByCtoEAndToday(todaysDate);
	}

	@Override
	public int[] findByCtoOAndToday(String todaysDate)
	{
		return consentRepository.findByCtoOAndToday(todaysDate);
	}

	@Override
	public List<Consent> findByCons()
	{
		return consentRepository.findByCons();
	}

	@Override
	public List<Consent> findbyConsentId(int consentId)
	{
		return consentRepository.findbyConsentId(consentId);
	}

	@Override
	public int updateConsentByConsentId(int consentId, String issueDate, String validUpto,
			float grossCi, int noStaff, int noWorker, float totPlotArea, float totBuildArea, float totGreenArea, String file, String filePath)
	{
		return consentRepository.updateConsentByConsentId(consentId, issueDate, validUpto,
				grossCi, noStaff, noWorker, totPlotArea, totBuildArea, totGreenArea, file, filePath);
	}

	@Override
	public List<Consent> findConsentIdByDate(String selectedDate, Pageable pageable)
	{
		return consentRepository.findConsentIdByDate(selectedDate, pageable);
	}

	@Override
	public int consentMinYear()
	{
		return consentRepository.consentMinYear();
	}

	@Override
	public List<Consent> findByConsByDate(String selectedDate)
	{
		return consentRepository.findByConsByDate(selectedDate);
	}

	@Override
	public List<Consent> getIssueDateAndConsNoByIssueDate(String fromDate, String toDate, String today)
	{
		return consentRepository.getIssueDateAndConsNoByIssueDate(fromDate, toDate, today);
	}

	@Override
	public List<Object[]> getListOfConsentByTodayDate(String today)
	{
		return consentRepository.getListOfConsentByTodayDate(today);
	}

	@Override
	public List<Consent> getConsentDataByIssueDate(String selectedDate)
	{
		return consentRepository.getConsentDataByIssueDate(selectedDate);
	}

	// // mmmmm
	//
	// @Override
	// public List<Consent> getConsentDataByIssueDate1(String dateTo, Pageable pageable)
	// {
	// return consentRepository.getConsentDataByIssueDate1(dateTo, pageable);
	// }

	@Override
	public int consentMinYearForEsr()
	{
		return consentRepository.consentMinYearForEsr();
	}

	@Override
	public List<Consent> findLastAddedConsentByConsType(String consType, Pageable pageable)
	{
		return consentRepository.findLastAddedConsentByConsType(consType, pageable);
	}

	@Override
	public List<Consent> findAllConsentByConsentType(String consentType)
	{
		return consentRepository.findAllConsentByConsentType(consentType);
	}

	@Override
	public List<Integer> findByDate(String dateTo, Pageable pageable)
	{
		return consentRepository.findByDate(dateTo, pageable);
	}
}
