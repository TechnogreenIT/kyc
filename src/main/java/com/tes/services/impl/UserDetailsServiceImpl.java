package com.tes.services.impl;

import java.util.Date;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.SpringSecurityMessageSource;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.tes.model.UserPrinciple;
import com.tes.model.Users;
import com.tes.repository.UsersRepository;

@Service
public class UserDetailsServiceImpl implements UserDetailsService
{
	protected final Log logger = LogFactory.getLog(getClass());

	protected MessageSourceAccessor messages = SpringSecurityMessageSource.getAccessor();

	@Autowired
	UsersRepository userRepository;

	@Autowired
	UsersServicesImpl usersServicesImpl;

	@Override
	@Transactional
	public UserDetails loadUserByUsername(String username)
			throws UsernameNotFoundException
	{

		Users user = userRepository.findUsersByUserName(username);
		if (user == null)
		{
			throw new UsernameNotFoundException(username);
		}
		else
		{
			if (user.getFailedLoginCount() == 0)
			{
				if (user != null)
				{
					// do check if account expired / suspended / deleted
					Boolean isLocked = false;
					// Boolean status = false;
					user.setFailedLoginCount(0);
					user.setLastLoginDate(new Date());
					user.setLocked(isLocked);
					usersServicesImpl.save(user);
				}
			}

		}

		if (user.getLocked() == true)
		{
			logger.debug("User account is locked");

			throw new LockedException(messages.getMessage(
					"AbstractUserDetailsAuthenticationProvider.locked",
					"User account is locked"));
		}

		return UserPrinciple.build(user);
	}
}
