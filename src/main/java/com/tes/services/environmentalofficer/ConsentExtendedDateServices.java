package com.tes.services.environmentalofficer;

import java.util.List;
import org.springframework.data.domain.Pageable;
import com.tes.model.ConsentExtendedDate;

public interface ConsentExtendedDateServices
{

	public ConsentExtendedDate save(ConsentExtendedDate consentExtendedDate);

	List findByConsValidUpto(Pageable pageable);

	List<String> findByConsExtendedById(Integer consId);

	public int updatDateByID(int consentId, String upInputDate, String upValidUpto);

	public List<ConsentExtendedDate> checkConsetID(int consentId);

	// public Integer findByConsById(int consentId);
	//
	// public Integer updateExeDate(int consentId);
	//
	//

}
