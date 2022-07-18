package com.tes.services.environmentalofficer;

import java.util.List;
import com.tes.model.EsrHwSolidWaste;

public interface EsrHwSolidWasteServices
{

	public EsrHwSolidWaste save(EsrHwSolidWaste esrHwSolidWaste);

	public List<EsrHwSolidWaste> findAll();

	List<Object[]> findOneByGetHazardousWasteByYear(String yearTo, String type);

	List<EsrHwSolidWaste> getEsrHwSolidWasteDataByHazYear(String year);

	List<EsrHwSolidWaste> getEsrHwSolidWasteDataBySolidYear(String year);

	public List<EsrHwSolidWaste> getAllDataByTypeMonth(String esrWholeYear, String type);

}
