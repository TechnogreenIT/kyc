package com.tes.repository.environmentalofficer;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

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

}
