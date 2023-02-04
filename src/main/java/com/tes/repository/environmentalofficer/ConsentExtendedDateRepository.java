package com.tes.repository.environmentalofficer;

import java.util.List;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import com.tes.model.ConsentExtendedDate;

@Repository
public interface ConsentExtendedDateRepository extends JpaRepository<ConsentExtendedDate, Long>
{

	@Override
	@SuppressWarnings("unchecked")
	ConsentExtendedDate save(ConsentExtendedDate consentExtendedDate);

	@Query("SELECT ce.validUpto FROM ConsentExtendedDate ce ORDER BY ce.consentExtendedDateId")
	List findByConsValidUpto(Pageable pageable);

	@Query("SELECT ce.validUpto FROM Consent  c LEFT JOIN c.consentExtendedDate ce  WHERE ce.consent.consentId=:consId ORDER BY ce.consentExtendedDateId ")
	List<String> findByConsExtendedById(@Param("consId") Integer consId);

	/// mmm
	@Transactional
	@Modifying
	@Query(value = "UPDATE ConsentExtendedDate ce SET ce.inputDate=:upInputDate,ce.validUpto=:upValidUpto WHERE ce.consent.consentId=:consentId , nativeQuery = true")
	int updatDateByID(@Param("consentId") int consentId, @Param("upInputDate") String upInputDate, @Param("upValidUpto") String upValidUpto);

	//// mmm
	@Query("SELECT ce.consentExtendedDateId FROM Consent  c LEFT JOIN c.consentExtendedDate ce  WHERE ce.consent.consentId=:consentId  ")
	List<ConsentExtendedDate> checkConsetID(@Param("consentId") int consentId);

	// chnages by pallavi...
	// @Query("SELECT ce.id FROM ConsentExtendedDate ce ")
	// int findByConsById(@Param ("consentId") Integer consentId) ;
	//
	// @Query("UPDATE ce.id FROM ConsentExtendedDate ce ")
	// int updateExeDate(@Param ("consentId") Integer consentId);

}
