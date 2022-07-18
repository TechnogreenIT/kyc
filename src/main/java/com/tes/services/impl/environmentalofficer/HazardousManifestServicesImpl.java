package com.tes.services.impl.environmentalofficer;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.tes.model.HazardousManifest;
import com.tes.repository.environmentalofficer.HazardousManifestRepository;
import com.tes.services.environmentalofficer.HazardousManifestServices;

@Service
public class HazardousManifestServicesImpl implements HazardousManifestServices
{

	@Autowired
	HazardousManifestRepository hazardousManifestRepository;

	@Override
	public HazardousManifest save(HazardousManifest hazardousManifest)
	{
		return hazardousManifestRepository.save(hazardousManifest);
	}

	@Override
	public int HWManifestId(String senderName, String sendersMailingAddress, String sendersPhoneNo,
			String sendersAuthorizationNo, String manifestDocumentsNo, String transporterName,
			String transporterAddress, String transportermobilepNo, String vehicleType, String transporterRegNo,
			String transporterVehicleRegNo, String receiversName, String receiversAddress,
			String receiversAuthorizationNo, String receiversPhoneNo, String transportDescWaste,
			String specialHandling)
	{
		return hazardousManifestRepository.HWManifestId(senderName, sendersMailingAddress, sendersPhoneNo,
				sendersAuthorizationNo, manifestDocumentsNo, transporterName, transporterAddress, transportermobilepNo,
				vehicleType, transporterRegNo, transporterVehicleRegNo, receiversName, receiversAddress,
				receiversAuthorizationNo, receiversPhoneNo, transportDescWaste, specialHandling);
	}

	@Override
	public List<Integer> getHazardousManifestYear()
	{
		return hazardousManifestRepository.getHazardousManifestYear();
	}

	@Override
	public List<Integer> HazardousManifestMonthByYear(int y)
	{
		return hazardousManifestRepository.HazardousManifestMonthByYear(y);
	}

	@Override
	public List<Integer> HazardousManifestDayByYearandMonth(int y, int yy)
	{
		return hazardousManifestRepository.HazardousManifestDayByYearandMonth(y, yy);
	}

	@Override
	public List<HazardousManifest> HazardousManifestData(int year, int month, int day)
	{
		return hazardousManifestRepository.HazardousManifestData(year, month, day);
	}

	@Override
	public List<String> getDateFromHazardousManifest(int year)
	{
		return hazardousManifestRepository.getDateFromHazardousManifest(year);
	}

	@Override
	public List<HazardousManifest> getDispatchedToAndFacilityNameByWasteName(String wasteName, String categoryNo, String fromDate,
			String toDate)
	{
		return hazardousManifestRepository.getDispatchedToAndFacilityNameByWasteName(wasteName, categoryNo, fromDate, toDate);
	}

	@Override
	public HazardousManifest getHazardousManifestByDate(String selectedDate)
	{
		return hazardousManifestRepository.getHazardousManifestByDate(selectedDate);
	}

	/*
	 * @Override
	 * public List<Integer> getHazardousManifestDay(String year, String month)
	 * {
	 * return hazardousManifestRepository.getHazardousManifestDay(year, month);
	 * }
	 */
}
