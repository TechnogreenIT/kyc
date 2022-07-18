package com.tes.services.environmentalofficer;

import java.util.List;
import com.tes.model.HazardousManifest;

public interface HazardousManifestServices
{
	HazardousManifest save(HazardousManifest hazardousManifest);

	public int HWManifestId(String senderName, String sendersMailingAddress, String sendersPhoneNo,
			String sendersAuthorizationNo, String manifestDocumentsNo, String transporterName,
			String transporterAddress, String transportermobilepNo, String vehicleType, String transporterRegNo,
			String transporterVehicleRegNo, String receiversName, String receiversAddress,
			String receiversAuthorizationNo, String receiversPhoneNo, String transportDescWaste,
			String specialHandling);

	public List<Integer> getHazardousManifestYear();

	public List<Integer> HazardousManifestMonthByYear(int y);

	public List<Integer> HazardousManifestDayByYearandMonth(int y, int yy);

	public List<HazardousManifest> HazardousManifestData(int year, int month, int day);

	public List<String> getDateFromHazardousManifest(int year);

	public List<HazardousManifest> getDispatchedToAndFacilityNameByWasteName(String wasteName, String categoryNo, String fromDate, String toDate);

	HazardousManifest getHazardousManifestByDate(String selectedDate);

	// public List<Integer> getHazardousManifestDay(String year, String month);
}
