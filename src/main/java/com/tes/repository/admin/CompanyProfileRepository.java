package com.tes.repository.admin;

import java.util.List;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.tes.model.CompanyProfile;

@Repository
public interface CompanyProfileRepository extends JpaRepository<CompanyProfile, Long>
{

	@Override
	List<CompanyProfile> findAll();

	@Query("SELECT cp.compName FROM Users u, CompanyProfile cp WHERE u.companyProfile.companyProfileId= :id")
	String findCompanyName(@Param("id") Integer id);

	@Query("SELECT cp FROM CompanyProfile cp ORDER BY cp.companyProfileId DESC")
	public List<CompanyProfile> findOneBycompanyProfileId(Pageable pageable);

	@Query("SELECT cp FROM CompanyProfile cp ORDER BY cp.companyProfileId")
	CompanyProfile findOneBySinglecompanyProfileId();

	@Query("SELECT c.industryCategory FROM CompanyProfile c WHERE c.companyProfileId=:companyId")
	public String getCategoryFromCompany(@Param("companyId") int companyId);

	@Query("SELECT cp.noWorkDays FROM CompanyProfile cp WHERE cp.companyProfileId=:companyId")
	public int noOfWorkingDays(@Param("companyId") int companyId);

	@Query("SELECT noWorkDays FROM CompanyProfile")
	public int getnoOfWorkers();

	@Query("SELECT companyProfileId FROM CompanyProfile")
	public int getCompanyProfileId();

	@Query(value = "select * from company_profile cp order by id DESC limit 1", nativeQuery = true) // remain
	CompanyProfile findOne();

	long count();

	@Query("SELECT companyProfileId FROM CompanyProfile order by companyProfileId DESC")
	Integer checkcompanyprofData();

}
