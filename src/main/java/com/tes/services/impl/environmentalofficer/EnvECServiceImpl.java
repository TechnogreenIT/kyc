package com.tes.services.impl.environmentalofficer;

import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tes.model.EnvEC;
import com.tes.repository.environmentalofficer.EnvECRepository;
import com.tes.services.environmentalofficer.EnvECServices;

@Service
public class EnvECServiceImpl implements EnvECServices{

	@Autowired
	EnvECRepository envECRepository;

	
	
	@Override
	public EnvEC save(EnvEC envEC) {
		// TODO Auto-generated method stub
		return envECRepository.save(envEC);
	}



	@Override
	public List<EnvEC> findbyEcId(int ecId) 
	{	
		return envECRepository.findbyEcId(ecId);
	}
	
	@Override
	public List<EnvEC> findBycmpId(int compId) {	
		return envECRepository.findBycmpId(compId);
	}


	@Override
	public List<EnvEC> findBycmpId(int compId,String todaysDate) {	
		return envECRepository.findBycmpId(compId,todaysDate);
	}



	@Override
	public int updateECByEcId(int ecId, String ecvalid_upto, String eiaQue, String eiaQue1, String eiaQue2, String eiaQue3,
			String file, String filePath) {
		// TODO Auto-generated method stub
		return envECRepository.updateECByEcId(ecId,ecvalid_upto,eiaQue,eiaQue1,eiaQue2,eiaQue3,file,filePath);
	}
	


}
