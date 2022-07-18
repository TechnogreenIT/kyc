package com.tes.services.admin;

import java.util.List;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;
import com.tes.model.CompanyProfile;
import com.tes.model.EmpData;

public interface EmpDataServices
{
	List<EmpData> findAll();

	@SuppressWarnings("unchecked")
	EmpData save(EmpData empdata);

	@Transactional
	List<CompanyProfile> getCompanydata();

	@Transactional
	List<EmpData> deleteByempDataId(int empDataId);

	EmpData getUserProfileData(int userId);

	EmpData findByUserId(int userId);

	int updateUserBasicInfo(String empname, String gender, String birthday, String maritalStatus, String contPerDesig, int id);

	int updateUserContactInfo(String address, String address2, String address3, String contPerNo, String email, int id);

	public List findEmail();

	public int disablStatus(int empDataId);

	public int enableStatus(int empDataId);

	public List findemployeeName();

	public byte[] getProfilePicByUserId(int usersId);

	List<EmpData> getEmpDataDetailsByAdmin(Pageable pageable);

	EmpData getEmpData(int userId);

	String getProfilePicName(int userId);

	int updateUserProfilePic(String profilePicName, int empId);
}
