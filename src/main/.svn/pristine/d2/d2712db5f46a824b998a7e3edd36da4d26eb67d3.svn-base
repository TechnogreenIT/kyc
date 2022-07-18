package com.tes.utilities;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import com.tes.model.EmpData;
import com.tes.model.Users;
import com.tes.repository.admin.EmpDataRepository;
import com.tes.services.impl.UsersServicesImpl;

@Component("authenticationUtil")
public class AuthenticationUtil
{

	@Autowired
	private UsersServicesImpl userService;

	@Autowired
	EmpDataRepository empDataRepository;

	public Users getLoggedInUser()
	{
		Users user = null;
		String username = SecurityContextHolder.getContext().getAuthentication().getName();
		user = userService.findUsersByUserName(username);
		return user;
	}

	public EmpData getLoggedInEmp()
	{
		Users user = null;
		EmpData empData = null;
		String username = SecurityContextHolder.getContext().getAuthentication().getName();
		user = userService.findUsersByUserName(username);
		empData = empDataRepository.findByUsers(user);
		return empData;
	}
}
