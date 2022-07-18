package com.tes.services.environmentalofficer;

import java.util.List;

import org.springframework.data.domain.Pageable;

import com.tes.model.ConsentExtendedDate;

public interface ConsentExtendedDateServices
{

	public ConsentExtendedDate save(ConsentExtendedDate consentExtendedDate);

	List findByConsValidUpto(Pageable pageable);

	List<String> findByConsExtendedById(Integer consId);
}
