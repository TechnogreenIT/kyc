package com.tes.services.environmentalofficer;

import java.util.Collection;
import java.util.List;

import org.apache.xmlbeans.impl.xb.xsdschema.Public;

import com.tes.model.EnvEC;
import com.tes.repository.environmentalofficer.EnvECRepository;

public interface EnvECServices {

	public EnvEC save(EnvEC envEC);

	public List<EnvEC> findbyEcId(int ecId);

	public List<EnvEC> findBycmpId(int compId);
	
	public List<EnvEC> findBycmpId(int compId,String todaysDate);
	//public List<EnvEC> findBycmpId(int compId);String todaysDate

	public int updateECByEcId(int ecId, String ecvalid_upto, String eiaQue, String eiaQue1, String eiaQue2, String eiaQue3,
			String file, String filePath);

}
