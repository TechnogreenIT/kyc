package com.tes.kyc;

import java.io.IOException;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.GenericFilterBean;
import com.tes.multitenant.TenantContext;
import com.tes.utilities.Validator;

@Component
@Order(Ordered.HIGHEST_PRECEDENCE)
public class CustomFilter extends GenericFilterBean
{

	@Override
	public void destroy()
	{
	}

	@Override
	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterchain)
			throws IOException, ServletException
	{

		HttpServletResponse response = (HttpServletResponse) servletResponse;
		HttpServletRequest request = (HttpServletRequest) servletRequest;

		if (!Validator.isEmpty(request.getSession(true)))
		{
			String tenantHeader = (String) request.getSession(true).getAttribute("tenantSession");
			if (tenantHeader != null && !tenantHeader.isEmpty())
			{
				TenantContext.setCurrentTenant(tenantHeader);
			}
		}

		filterchain.doFilter(request, response);
	}

}
