package com.tes.multitenant;

public class TenantContext
{

	private static final ThreadLocal<Object> currentTenant = new ThreadLocal<>();

	public static void setCurrentTenant(Object tenant)
	{
		currentTenant.set(tenant);
	}

	public static Object getCurrentTenant()
	{
		return currentTenant.get();
	}

	public static void clear()
	{
		currentTenant.set(null);
	}
}
