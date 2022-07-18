package com.tes.services.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import com.tes.model.Users;
import com.tes.repository.UsersRepository;
import com.tes.services.UsersServices;

@Service
public class UsersServicesImpl implements UsersServices
{

	@Autowired
	UsersRepository usersRepository;

	@Override
	public Users save(Users users)
	{

		return usersRepository.save(users);
	}

	@Override
	public int findUserIdByUserName(String userName)
	{
		return usersRepository.findUserIdByUserName(userName);
	}

	@Override
	public String findPassword(int usersId, String oldPassword)
	{

		return usersRepository.findPassword(usersId, oldPassword);
	}

	@Override
	public int updatePassword(String newPassword, int usersId)
	{
		return usersRepository.updatePassword(newPassword, usersId);
	}

	@Override
	public String[] findUserNameAndPassword(String email)
	{
		return usersRepository.findUserNameAndPassword(email);
	}

	@Override
	public List findUserStatus()
	{
		return usersRepository.findUserStatus();
	}

	@Override
	public List findUserName()
	{
		return usersRepository.findUserName();
	}

	@Override
	public List<Users> getUsersDetailsByAdmin(Pageable pageable)
	{
		return usersRepository.getUsersDetailsByAdmin(pageable);
	}

	@Override
	public int findByUserName(String userName, String password)
	{
		return usersRepository.findByUserName(userName, password);
	}

	@Override
	public Users findUsersByUserName(String userName)
	{
		return usersRepository.findUsersByUserName(userName);
	}

	@Override
	public Users findByUsersId(int usersId)
	{
		return usersRepository.findByUsersId(usersId);
	}

	// @Override
	// public Users getUserByUsername(String username)
	// {
	// return (Users) usersRepository.findUserName();
	// }

}
