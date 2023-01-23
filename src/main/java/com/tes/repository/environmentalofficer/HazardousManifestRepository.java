package com.tes.repository.environmentalofficer;

import java.util.List;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.tes.model.HazardousManifest;

@Repository
public interface HazardousManifestRepository extends JpaRepository<HazardousManifest, Long>
{
	@Override
	@SuppressWarnings("unchecked")
	HazardousManifest save(HazardousManifest hazardousManifest);

	@Query("SELECT hm.hazardousManifestId FROM HazardousManifest hm  WHERE hm.sendersName = :senderName AND hm.sendersMailingAddress = :sendersMailingAddress AND hm.sendersPhoneNo = :sendersPhoneNo AND hm.sendersAuthorizationNo = :sendersAuthorizationNo AND hm.manifestDocumentsNo = :manifestDocumentsNo AND hm.transporterName = :transporterName AND hm.transporterAddress = :transporterAddress AND hm.transportermobilepNo = :transportermobilepNo AND hm.vehicleType = :vehicleType AND hm.transporterRegNo = :transporterRegNo AND hm.transporterVehicleRegNo = :transporterVehicleRegNo AND hm.receiversName = :receiversName AND hm.receiversAddress= :receiversAddress AND hm.receiversAuthorizationNo = :receiversAuthorizationNo AND hm.receiversPhoneNo = :receiversPhoneNo AND hm.transportDescWaste = :transportDescWaste AND  hm.specialHandling = :specialHandling")
	public int HWManifestId(@Param("senderName") String senderName,
			@Param("sendersMailingAddress") String sendersMailingAddress,
			@Param("sendersPhoneNo") String sendersPhoneNo,
			@Param("sendersAuthorizationNo") String sendersAuthorizationNo,
			@Param("manifestDocumentsNo") String manifestDocumentsNo, @Param("transporterName") String transporterName,
			@Param("transporterAddress") String transporterAddress,
			@Param("transportermobilepNo") String transportermobilepNo, @Param("vehicleType") String vehicleType,
			@Param("transporterRegNo") String transporterRegNo,
			@Param("transporterVehicleRegNo") String transporterVehicleRegNo,
			@Param("receiversName") String receiversName, @Param("receiversAddress") String receiversAddress,
			@Param("receiversAuthorizationNo") String receiversAuthorizationNo,
			@Param("receiversPhoneNo") String receiversPhoneNo, @Param("transportDescWaste") String transportDescWaste,
			@Param("specialHandling") String specialHandling);

	@Query("SELECT DISTINCT(EXTRACT(YEAR FROM hm.submittedDate)) FROM HazardousManifest hm  ORDER BY hm.submittedDate ASC")
	public List<Integer> getHazardousManifestYear();

	@Query("SELECT DISTINCT(EXTRACT(MONTH FROM hm.submittedDate)) FROM HazardousManifest hm WHERE (EXTRACT(YEAR FROM hm.submittedDate)) = :year ORDER BY hm.submittedDate ASC")
	public List<Integer> HazardousManifestMonthByYear(@Param("year") int year);

	@Query("SELECT EXTRACT(DAY FROM hm.submittedDate) FROM HazardousManifest hm WHERE (EXTRACT(YEAR FROM hm.submittedDate)) = :year AND (EXTRACT(MONTH FROM hm.submittedDate)) = :month ORDER BY hm.submittedDate ASC")
	public List<Integer> HazardousManifestDayByYearandMonth(@Param("year") int year, @Param("month") int month);

	@Query("SELECT hm FROM HazardousManifest hm WHERE EXTRACT(YEAR FROM hm.submittedDate) = :year AND EXTRACT(MONTH FROM hm.submittedDate) = :month AND EXTRACT(DAY FROM hm.submittedDate) = :day")
	public List<HazardousManifest> HazardousManifestData(@Param("year") int year, @Param("month") int month, @Param("day") int day);

	@Query("SELECT DISTINCT(hm.submittedDate) FROM HazardousManifest hm WHERE (EXTRACT(YEAR FROM hm.submittedDate)) =:year ORDER BY hm.submittedDate ASC")
	public List<String> getDateFromHazardousManifest(@Param("year") int year);

	@Query("SELECT DISTINCT NEW HazardousManifest(hm.receiversName, hm.dispatchedTo) FROM WasteDescriptionConsistency wc INNER JOIN wc.hazardousManifest hm WHERE wc.wasteName LIKE '%'||:wasteName  AND wc.wasteCategoryNumber=:categoryNo AND hm.submittedDate BETWEEN :fromDate AND :toDate")
	public List<HazardousManifest> getDispatchedToAndFacilityNameByWasteName(@Param("wasteName") String wasteName, @Param("categoryNo") String categoryNo, @Param("fromDate") String fromDate, @Param("toDate") String toDate);

	@Query("SELECT hm FROM HazardousManifest hm WHERE hm.submittedDate = :selectedDate")
	HazardousManifest getHazardousManifestByDate(@Param("selectedDate") String selectedDate);

	@Query("SELECT hm FROM HazardousManifest hm ORDER BY hm.hazardousManifestId DESC")
	List<HazardousManifest> checkHazManifestDTPresent(Pageable pageble);

	// @Query("SELECT EXTRACT(DAY FROM hm.submittedDate) HazardousManifest hm where EXTRACT(YEAR FROM hm.submittedDate) = :year and EXTRACT(MONTH FROM hm.submittedDate)= :month")
	// public List<Integer> getHazardousManifestDay(@Param("year") String year, @Param("month") String month);

}
