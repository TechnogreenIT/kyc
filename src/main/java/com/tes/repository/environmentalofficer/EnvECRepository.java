package com.tes.repository.environmentalofficer;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.tes.model.EnvEC;
@Repository
public interface EnvECRepository extends JpaRepository<EnvEC, Long> {
	@Override
	@SuppressWarnings("unchecked")
	EnvEC save(EnvEC envEC);

	@Query("SELECT NEW EnvEC(ec.ecNo,ec.ecvalid_date,ec.ecFilePath,ec.ecFileName,ec.eia_Notification,ec.protect_Area_Wildlife,ec.criticalPoll_Area_Identify,ec.ecosensitive_Area) FROM EnvEC ec WHERE ec.ecId= :ecId")
	List<EnvEC> findbyEcId(@Param("ecId")int ecId);

	
	@Query("SELECT NEW EnvEC(ec.ecId,ec.ecNo) FROM EnvEC ec,CompanyProfile cmp WHERE ec.companyProfile.companyProfileId=cmp.companyProfileId AND ec.companyProfile.companyProfileId=:cmpId")
	public List<EnvEC> findBycmpId(@Param("cmpId") int cmpId);

	@Query("SELECT NEW EnvEC(ec.ecId,ec.ecNo) FROM EnvEC ec,CompanyProfile cmp WHERE ec.companyProfile.companyProfileId=cmp.companyProfileId AND ec.companyProfile.companyProfileId=:cmpId AND ec.ecvalid_date >= :todaysDate")
	public List<EnvEC> findBycmpId(@Param("cmpId")int compId,@Param("todaysDate") String todaysDate);

	@Transactional
	@Modifying
	@Query("UPDATE EnvEC ec SET ec.ecvalid_date = :ecvalid_upto ,ec.eia_Notification = :eiaQue ,ec.protect_Area_Wildlife = :eiaQue1 ,ec.criticalPoll_Area_Identify = :eiaQue2 ,ec.ecosensitive_Area = :eiaQue3,ec.ecFileName = :file, ec.ecFilePath = :filePath WHERE ec.ecId = :ecId")
	int updateECByEcId(@Param("ecId")int ecId,@Param("ecvalid_upto")String ecvalid_upto,@Param("eiaQue")String eiaQue,@Param("eiaQue1")String eiaQue1,@Param("eiaQue2")String eiaQue2,@Param("eiaQue3")String eiaQue3,
			@Param("file")String file,@Param("filePath") String filePath);

	
}
