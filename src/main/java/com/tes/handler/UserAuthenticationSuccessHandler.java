package com.tes.handler;

import java.io.IOException;
import java.util.Collection;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

@Component
public class UserAuthenticationSuccessHandler implements AuthenticationSuccessHandler
{

	public static String userName;

	private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException
	{
		userName = request.getParameter("username");

		boolean hasAdminRole = false;
		boolean hasSuperAdminRole = false;
		boolean hasEnvrOfficerRole = false;
		boolean hasManagementRole = false;
		boolean hasThirdPartyRole = false;
		Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
		for (GrantedAuthority grantedAuthority : authorities)
		{

			if (grantedAuthority.getAuthority().equalsIgnoreCase("ROLE_ADMIN"))
			{
				hasAdminRole = true;
				break;
			}
			else if (grantedAuthority.getAuthority().equalsIgnoreCase("ROLE_SUPERADMIN"))
			{
				hasSuperAdminRole = true;
				break;
			}
			else if (grantedAuthority.getAuthority().equalsIgnoreCase("ROLE_ENVROFFICER"))
			{
				hasEnvrOfficerRole = true;
				break;
			}
			else if (grantedAuthority.getAuthority().equalsIgnoreCase("ROLE_MANAGEMENT"))
			{
				hasManagementRole = true;
				break;
			}
			else if (grantedAuthority.getAuthority().equalsIgnoreCase("ROLE_THIRDPARTY"))
			{
				hasThirdPartyRole = true;
				break;
			}
		}

		if (hasAdminRole)
		{
			redirectStrategy.sendRedirect(request, response, "/admin/dashboard");
		}
		else if (hasSuperAdminRole)
		{
			redirectStrategy.sendRedirect(request, response, "/superadmin/dashboard");
		}
		else if (hasEnvrOfficerRole)
		{
			redirectStrategy.sendRedirect(request, response, "/env/dashboard");
		}
		else if (hasManagementRole)
		{
			redirectStrategy.sendRedirect(request, response, "/management/dashboard");
		}
		else if (hasThirdPartyRole)
		{
			redirectStrategy.sendRedirect(request, response, "/thirdParty/dashboard");
		}
		else
		{
			throw new IllegalStateException();
		}
	}
}
