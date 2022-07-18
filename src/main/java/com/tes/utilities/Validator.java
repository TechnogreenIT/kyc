package com.tes.utilities;

import java.util.Collection;
import java.util.Map;

public class Validator
{

	private Validator()
	{

	}

	/**
	 * This method returns true if the collection is null or is empty.
	 * 
	 * @param collection
	 * @return true | false
	 */
	public static boolean isEmpty(Collection<?> collection)
	{
		if (collection == null || collection.isEmpty())
		{
			return true;
		}
		return false;
	}

	/**
	 * This method returns true of the map is null or is empty.
	 * 
	 * @param map
	 * @return true | false
	 */
	public static boolean isEmpty(Map<?, ?> map)
	{
		if (map == null || map.isEmpty())
		{
			return true;
		}
		return false;
	}

	/**
	 * This method returns true if the object is null.
	 * 
	 * @param object
	 * @return true | false
	 */
	public static boolean isEmpty(Object object)
	{
		if (object == null)
		{
			return true;
		}
		return false;
	}

	/**
	 * This method returns true if the input array is null or its length is zero.
	 * 
	 * @param array
	 * @return true | false
	 */
	public static boolean isEmpty(Object[] array)
	{
		if (array == null || array.length == 0)
		{
			return true;
		}
		return false;
	}

	/**
	 * This method returns true if the input string is null or its length is zero.
	 * 
	 * @param string
	 * @return true | false
	 */
	public static boolean isEmpty(String string)
	{
		if (string == null || string.trim().length() == 0)
		{
			return true;
		}
		return false;
	}

	public static boolean isEmpty(Float f)
	{
		if (f == 0.0f || f.SIZE == 0 || f == null)
		{
			return true;
		}
		return false;
	}

	public static boolean isNumber(String numbericString)
	{
		return numbericString.matches("^[+-]?[0-9]\\d*(\\.\\d+)?$");
	}

}
