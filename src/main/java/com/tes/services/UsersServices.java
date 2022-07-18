package com.tes.services;

import java.util.List;
import org.springframework.data.domain.Pageable;
import com.tes.model.Users;

public interface UsersServices
{

	public Users save(Users users);

	public int findUserIdByUserName(String userName);

	public String findPassword(int usersId, String password);

	public int updatePassword(String newPassword, int usersId);

	public String[] findUserNameAndPassword(String email);

	public List findUserStatus();

	public List findUserName();

	public List<Users> getUsersDetailsByAdmin(Pageable pageable);

	int findByUserName(String userName, String password);

	Users findUsersByUserName(String userName);

	public Users findByUsersId(int usersId);

	// public Users getUserByUsername(String username);
}
