package com.tes.services;

import java.util.List;
import com.tes.model.FilterNameList;

public interface FilterNameListServices
{

	List<FilterNameList> findAll();

	boolean getFilterIdByName(String filterName);
}
