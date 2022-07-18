package com.tes.repository.admin;

import java.util.List;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import com.tes.model.CompanyProfile;
import com.tes.model.EmpData;
import com.tes.model.Users;

@Repository
public interface EmpDataRepository extends JpaRepository<EmpData, Long>
{

	@Override
	List<EmpData> findAll();

	@Override
	@SuppressWarnings("unchecked")
	EmpData save(EmpData empdata);

	@Transactional
	@Query("SELECT cp FROM CompanyProfile cp WHERE cp.companyProfileId=1")
	List<CompanyProfile> getCompanydata();

	@Transactional
	List<EmpData> deleteByempDataId(int empDataId);

	@Query("SELECT e FROM EmpData e WHERE e.users.usersId= :userId")
	EmpData getUserProfileData(@Param("userId") int userId);

	@Query("SELECT new EmpData(e.empDataId,e.users,e.employeeName,e.companyProfile,e.profilePic,e.contPerDesig) FROM EmpData e WHERE e.users.usersId= :userId")
	EmpData findByUserId(@Param("userId") int userId);

	@Transactional
	@Modifying
	@Query("Update EmpData e SET e.employeeName= :empname,e.gender= :gender, e.birthday =:birthday, e.maritalStatus =:maritalStatus, e.contPerDesig =:contPerDesig WHERE e.users.usersId= :userId")
	int updateUserBasicInfo(@Param("empname") String empname, @Param("gender") String gender, @Param("birthday") String birthday, @Param("maritalStatus") String maritalStatus, @Param("contPerDesig") String contPerDesig, @Param("userId") int userId);

	@Transactional
	@Modifying
	@Query("Update EmpData e SET e.address= :address,e.address2= :address2,e.address3= :address3,e.contPerNo= :contPerNo,e.email= :email WHERE e.users.usersId= :id")
	int updateUserContactInfo(@Param("address") String address, @Param("address2") String address2, @Param("address3") String address3, @Param("contPerNo") String contPerNo, @Param("email") String email, @Param("id") int id);

	@Query("SELECT e.email FROM EmpData e")
	public List findEmail();

	// remain *2
	@Transactional
	@Modifying
	@Query(value = "update users u LEFT JOIN emp_data e ON  e.user_id=u.id  SET u.enabled=0 where e.id= :empDataId", nativeQuery = true)
	public int disablStatus(@Param("empDataId") int empDataId);

	@Transactional
	@Modifying
	@Query(value = "update users u LEFT JOIN emp_data e ON  e.user_id=u.id  SET u.enabled=1 where e.id= :empDataId", nativeQuery = true)
	public int enableStatus(@Param("empDataId") int empDataId);

	@Query("SELECT e.employeeName FROM EmpData e")
	public List findemployeeName();

	@Query("SELECT e.profilePic FROM EmpData e WHERE e.users.usersId= :usersId")
	public byte[] getProfilePicByUserId(@Param("usersId") int usersId);

	@Query("SELECT e FROM EmpData e WHERE e.contPerDesig='Admin' ORDER BY e.empDataId DESC")
	List<EmpData> getEmpDataDetailsByAdmin(Pageable pageable);

	@Query("SELECT e FROM EmpData e WHERE e.id= :empId")
	EmpData getEmpData(@Param("empId") int empId);

	@Query("SELECT e.profilePic FROM EmpData e WHERE e.empDataId= :empId")
	String getProfilePicName(@Param("empId") int empId);

	@Transactional
	@Modifying
	@Query("Update EmpData e SET e.profilePic= :profilePicName WHERE e.empDataId= :empId")
	int updateUserProfilePic(@Param("profilePicName") String profilePicName, @Param("empId") int empId);

	List<EmpData> findByContPerDesig(String contPerDesig);

	EmpData findByUsers(Users user);

}
