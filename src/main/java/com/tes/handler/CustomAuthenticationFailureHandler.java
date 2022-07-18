package com.tes.handler;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Component;
import com.tes.model.Users;
import com.tes.repository.UsersRepository;
import com.tes.services.impl.UsersServicesImpl;

@Component("customAuthenticationFailureHandler")
@PropertySource("classpath:application.properties")
public class CustomAuthenticationFailureHandler extends SimpleUrlAuthenticationFailureHandler
{

	private String DEFAULT_FAILURE_URL = "/login?error";

	@Autowired
	UsersRepository userRepository;

	@Autowired
	UsersServicesImpl usersServicesImpl;

	@Autowired
	private Environment applicationProperties;

	@Override
	public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException exception) throws IOException, ServletException
	{

		setDefaultFailureUrl(DEFAULT_FAILURE_URL);
		super.onAuthenticationFailure(request, response, exception);

		if (exception instanceof BadCredentialsException)
		{
			lockUser(request.getParameter("username"));
		}

	}

	private void lockUser(String username)
	{
		Users user = userRepository.findUsersByUserName(username);

		if (user != null && user.getLocked() == false)
		{
			int failedCount = user.getFailedLoginCount() + 1;
			int constantFailedCount = Integer.valueOf(applicationProperties.getProperty("failed.login.count.max"));
			user.setFailedLoginCount(failedCount);
			if (failedCount > constantFailedCount)
			{
				user.setLocked(true);
			}

			usersServicesImpl.save(user);
		}
	}
}
