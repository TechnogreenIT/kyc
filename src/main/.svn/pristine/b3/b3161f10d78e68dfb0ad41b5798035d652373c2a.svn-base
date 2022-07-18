package com.tes.services.impl.environmentalofficer;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.tes.model.ModifiedRecord;
import com.tes.repository.environmentalofficer.ModifiedRecordRepository;

@Service(value = "modifiedRecordServices")
public class ModifiedRecordServicesImpl
{

	@Autowired
	ModifiedRecordRepository modifiedRecordRepository;

	public List<ModifiedRecord> getAllModifiedRecordsByType(String productType, String actionType)
	{
		return modifiedRecordRepository.getAllModifiedRecordsByType(productType, actionType);
	}
}
