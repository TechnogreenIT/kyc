package com.tes.repository.environmentalofficer;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.tes.model.Ambient;

@Repository
public interface AmbientRepository extends JpaRepository<Ambient, Long>
{

	@Override
	@SuppressWarnings("unchecked")
	Ambient save(Ambient ambient);

	// remain
	@Query(value = "SELECT DISTINCT(a.id) FROM ambient a LEFT JOIN consent c on c.id=a.cons_id LEFT JOIN reg_amb_poll ap on ap.ambient_id=a.id LEFT JOIN consent_extended_date ce on ce.consent_id=c.id WHERE c.cons_status!='EXPIRED' AND a.consent_to_operate='Yes' AND c.company_profile_id= :companyId AND(c.valid_upto>= :today OR ce.valid_upto>= :today)", nativeQuery = true)
	List<Integer> getAmbientId(@Param("today") String today, @Param("companyId") int companyId);

	public Ambient findByAmbientId(int ambientId);

	@Query("SELECT  a FROM AmbientOp aop LEFT JOIN aop.ambient a LEFT JOIN aop.consent c  LEFT JOIN c.consentExtendedDate ce WHERE c.consType='Consent to Operate' AND  c.consStatus != 'EXPIRED' AND (ce.validUpto >= :today OR c.validUpto >= :today)")
	public List<Ambient> findByAmbient(@Param("today") String today);

	@Query("SELECT a FROM  Ambient a, Consent c WHERE a.consent=c.consentId AND c.consType='Consent to Establish'")
	List<Ambient> findByConsType();

	@Query("SELECT DISTINCT(a.ambient.ambientId) FROM AmbientOp a ,RegAmbientPoll rp LEFT JOIN   a.consent c  LEFT JOIN c.consentExtendedDate ce   WHERE rp.ambient.ambientId=a.ambient.ambientId AND c.consStatus != 'EXPIRED'  AND  c.companyProfile.companyProfileId  = :companyId   AND (c.validUpto>= :today OR ce.validUpto>= :today)")
	int[] getAmbientIdForMonitor(@Param("today") String today, @Param("companyId") int companyId);

	@Transactional
	@Modifying
	@Query("UPDATE Ambient a SET a.consentToOperate ='Yes' WHERE a.ambientId = :ambientId")
	int updateConsentToOperate(@Param("ambientId") int ambientId);

	@Query("SELECT a FROM Ambient a WHERE a.consent.consentId = :consentId ORDER BY a.ambientId ASC")
	List<Ambient> findByConsentId(@Param("consentId") int consentId);

	@Query("SELECT  a FROM AmbientOp aop LEFT JOIN aop.ambient a LEFT JOIN aop.consent c  LEFT JOIN c.consentExtendedDate ce WHERE c.consType='Consent to Operate' AND aop.consent.consentId=:consentId")
	List<Ambient> findByconsentToOperateAndConsId(@Param("consentId") int consentId);

	@Query(value = "SELECT a.id FROM AmbientOp aop LEFT JOIN aop.ambient a LEFT JOIN aop.consent c  LEFT JOIN c.consentExtendedDate ce WHERE c.consType='Consent to Operate' AND c.companyProfile.companyProfileId=:companyId AND   c.consStatus != 'EXPIRED' AND (ce.validUpto >= :today OR c.validUpto >= :today)")
	List<Integer> getAmbientIdForAhp(@Param("today") String today, @Param("companyId") int companyId);

	@Transactional
	List<Ambient> deleteByAmbientId(int productId);
}
