package com.tes.controller;

import java.util.Enumeration;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.LogoutHandler;

public class TaskImplementingLogoutHandler implements LogoutHandler
{

	/*
	 * (non-Javadoc)
	 * @see org.springframework.security.web.authentication.logout.LogoutHandler#logout(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse, org.springframework.security.core.Authentication)
	 */
	@Override
	public void logout(HttpServletRequest req, HttpServletResponse res,
			Authentication authentication)
	{

		SecurityContextHolder.getContext().setAuthentication(null);
		SecurityContextHolder.clearContext();
		HttpSession hs = req.getSession();
		Enumeration e = hs.getAttributeNames();
		while (e.hasMoreElements())
		{
			String attr = (String) e.nextElement();
			hs.setAttribute(attr, null);
		}
		removeCookies(req);
		hs.invalidate();

	}

	public void removeCookies(HttpServletRequest request)
	{
		Cookie[] cookies = request.getCookies();
		if (cookies != null && cookies.length > 0)
		{
			for (int i = 0; i < cookies.length; i++)
			{
				cookies[i].setMaxAge(0);
			}
		}
	}

}
