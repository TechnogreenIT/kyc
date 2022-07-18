package com.tes.services.admin;

import java.util.List;
import org.springframework.data.domain.Pageable;
import com.tes.model.CompanyProfile;

public interface CompanyProfileServices
{

	public CompanyProfile save(CompanyProfile CompanyProfile);

	public List<CompanyProfile> findAll();

	public String findCompanyName(Integer id);

	public List<CompanyProfile> findOneBycompanyProfileId(Pageable pageable);

	CompanyProfile findOneBySinglecompanyProfileId();

	public String getCategoryFromCompany(int companyId);

	public int noOfWorkingDays(int companyId);

	public int getnoOfWorkers();

	public int getCompanyProfileId();

	CompanyProfile findOne();

	long count();

}
