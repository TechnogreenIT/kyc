package com.tes.repository.environmentalofficer;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import com.tes.model.WateriePollutantOp;

@Repository
public interface WateriePollutantOpRepository extends JpaRepository<WateriePollutantOp, Long>
{
	@Modifying
	@Transactional
	@Query("DELETE FROM WateriePollutantOp wp WHERE wp.wateriePollutant.wateriePollutantId=:productId")
	int deleteWateriePollutantOp(@Param("productId") int productId);

	@Query("SELECT  wop FROM WateriePollutantOp wop LEFT JOIN wop.wateriePollutant  wp LEFT JOIN wop.consent c LEFT JOIN c.consentExtendedDate ce WHERE c.consType='Consent to Operate' AND  c.consStatus != 'EXPIRED' AND (ce.validUpto >= :today OR c.validUpto >= :today)")
	List<WateriePollutantOp> findByTodayDateAndCid(@Param("today") String today);

}
