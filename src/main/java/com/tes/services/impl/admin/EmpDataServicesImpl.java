package com.tes.services.impl.admin;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import com.tes.model.CompanyProfile;
import com.tes.model.EmpData;
import com.tes.repository.admin.EmpDataRepository;
import com.tes.services.admin.EmpDataServices;

@Service
public class EmpDataServicesImpl implements EmpDataServices
{

	@Autowired
	EmpDataRepository empDataRepository;

	@Override
	public List<EmpData> findAll()
	{
		return empDataRepository.findAll();
	}

	@Override
	public EmpData save(EmpData empdata)
	{
		return empDataRepository.save(empdata);
	}

	@Override
	public List<EmpData> deleteByempDataId(int empDataId)
	{
		return empDataRepository.deleteByempDataId(empDataId);
	}

	@Override
	public EmpData getUserProfileData(int userId)
	{
		return empDataRepository.getUserProfileData(userId);
	}

	@Override
	public EmpData findByUserId(int userId)
	{
		return empDataRepository.findByUserId(userId);
	}

	@Override
	public int updateUserBasicInfo(String empname, String gender, String birthday, String maritalStatus, String contPerDesig, int id)
	{
		return empDataRepository.updateUserBasicInfo(empname, gender, birthday, maritalStatus, contPerDesig, id);
	}

	@Override
	public int updateUserContactInfo(String address, String address2, String address3, String contPerNo, String email, int id)
	{
		return empDataRepository.updateUserContactInfo(address, address2, address3, contPerNo, email, id);
	}

	@Override
	public List findEmail()
	{
		return empDataRepository.findEmail();
	}

	@Override
	public int disablStatus(int empDataId)
	{
		return empDataRepository.disablStatus(empDataId);
	}

	@Override
	public int enableStatus(int empDataId)
	{
		return empDataRepository.enableStatus(empDataId);
	}

	@Override
	public List findemployeeName()
	{
		return empDataRepository.findemployeeName();
	}

	@Override
	public List<CompanyProfile> getCompanydata()
	{
		return empDataRepository.getCompanydata();
	}

	@Override
	public byte[] getProfilePicByUserId(int usersId)
	{
		return empDataRepository.getProfilePicByUserId(usersId);
	}

	@Override
	public List<EmpData> getEmpDataDetailsByAdmin(Pageable pageable)
	{
		return empDataRepository.getEmpDataDetailsByAdmin(pageable);
	}

	@Override
	public EmpData getEmpData(int userId)
	{
		return empDataRepository.getEmpData(userId);
	}

	@Override
	public String getProfilePicName(int userId)
	{
		return empDataRepository.getProfilePicName(userId);
	}

	@Override
	public int updateUserProfilePic(String profilePicName, int empId)
	{
		return empDataRepository.updateUserProfilePic(profilePicName, empId);
	}

}
