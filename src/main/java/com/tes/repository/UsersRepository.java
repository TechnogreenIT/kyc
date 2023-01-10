package com.tes.repository;

import java.util.List;
import javax.transaction.Transactional;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.tes.model.Users;

@Repository
public interface UsersRepository extends JpaRepository<Users, Long>
{

	@Query("SELECT u.usersId FROM Users u WHERE u.userName=:userName")
	public int findUserIdByUserName(@Param("userName") String userName);

	@SuppressWarnings("unchecked")
	@Override
	Users save(Users users);

	@Query("SELECT u.usersId FROM Users u WHERE u.usersId= :usersId AND u.password= :oldPassword")
	public String findPassword(@Param("usersId") int usersId, @Param("oldPassword") String oldPassword);

	@Transactional
	@Modifying
	@Query("UPDATE Users u SET u.password= :newPassword WHERE u.usersId= :usersId")
	public int updatePassword(@Param("newPassword") String newPassword, @Param("usersId") int usersId);

	@Query("SELECT u.userName, u.password FROM Users u, EmpData e WHERE u.usersId=e.users.usersId AND e.email= :email")
	public String[] findUserNameAndPassword(@Param("email") String email);

	@Query("SELECT u.enabled FROM EmpData e, Users u WHERE e.users.usersId=u.usersId")
	public List findUserStatus();

	@Query("SELECT u.userName FROM Users u")
	public List findUserName();

	@Query("SELECT u FROM EmpData e INNER JOIN e.users u WHERE e.contPerDesig='Admin' ORDER BY e.empDataId DESC ")
	List<Users> getUsersDetailsByAdmin(Pageable pageable);

	@Query("SELECT u.usersId FROM Users u WHERE u.userName = :userName AND u.password = :password AND  u.enabled=1")
	public int findByUserName(@Param("userName") String userName, @Param("password") String password);

	Users findUsersByUserName(String userName);

	Boolean existsByUserName(String username);

	@Query("select u from Users u where u.usersId=:usersId")
	public Users findByUsersId(@Param("usersId") int usersId);

	// public Users findUsersByUserName(String userName);

	Users findByusersId(int usersId);

	@Query("SELECT u.usersId FROM Users u  ORDER BY u.usersId DESC")
	public List<Users> checkUserData(Pageable pageable);

}
