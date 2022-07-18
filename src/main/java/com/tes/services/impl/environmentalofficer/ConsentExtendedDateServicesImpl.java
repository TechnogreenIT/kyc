package com.tes.services.impl.environmentalofficer;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.tes.model.ConsentExtendedDate;
import com.tes.repository.environmentalofficer.ConsentExtendedDateRepository;
import com.tes.services.environmentalofficer.ConsentExtendedDateServices;

@Service
public class ConsentExtendedDateServicesImpl implements ConsentExtendedDateServices
{

	@Autowired
	ConsentExtendedDateRepository consentExtendedDateRepository;

	@Override
	public List findByConsValidUpto(Pageable pageable)
	{
		return consentExtendedDateRepository.findByConsValidUpto(pageable);
	}

	@Override
	public List<String> findByConsExtendedById(Integer consId)
	{
		return consentExtendedDateRepository.findByConsExtendedById(consId);
	}

	@Override
	public ConsentExtendedDate save(ConsentExtendedDate consentExtendedDate)
	{
		return consentExtendedDateRepository.save(consentExtendedDate);
	}

}
