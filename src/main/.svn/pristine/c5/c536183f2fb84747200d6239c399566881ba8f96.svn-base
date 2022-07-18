package com.tes.services.impl.admin;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import com.tes.model.CompanyProfile;
import com.tes.repository.admin.CompanyProfileRepository;
import com.tes.services.admin.CompanyProfileServices;

@Service
public class CompanyProfileServicesImpl implements CompanyProfileServices
{

	@Autowired
	CompanyProfileRepository companyProfileRepository;

	@Override
	public CompanyProfile save(CompanyProfile companyProfile)
	{
		return companyProfileRepository.save(companyProfile);
	}

	@Override
	public List<CompanyProfile> findAll()
	{
		return companyProfileRepository.findAll();
	}

	@Override
	public String findCompanyName(Integer id)
	{
		return companyProfileRepository.findCompanyName(id);
	}

	@Override
	public List<CompanyProfile> findOneBycompanyProfileId(Pageable pageable)
	{
		return companyProfileRepository.findOneBycompanyProfileId(pageable);
	}

	@Override
	public CompanyProfile findOneBySinglecompanyProfileId()
	{
		return companyProfileRepository.findOneBySinglecompanyProfileId();
	}

	@Override
	public String getCategoryFromCompany(int companyId)
	{
		return companyProfileRepository.getCategoryFromCompany(companyId);
	}

	@Override
	public int noOfWorkingDays(int companyId)
	{
		return companyProfileRepository.noOfWorkingDays(companyId);
	}

	@Override
	public int getnoOfWorkers()
	{
		return companyProfileRepository.getnoOfWorkers();
	}

	@Override
	public int getCompanyProfileId()
	{
		return companyProfileRepository.getCompanyProfileId();
	}

	@Override
	public CompanyProfile findOne()
	{
		return companyProfileRepository.findOne();
	}

	@Override
	public long count()
	{
		return companyProfileRepository.count();
	}

}
