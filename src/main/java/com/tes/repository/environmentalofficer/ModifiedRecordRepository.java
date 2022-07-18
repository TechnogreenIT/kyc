package com.tes.repository.environmentalofficer;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.tes.model.ModifiedRecord;

@Repository
public interface ModifiedRecordRepository extends JpaRepository<ModifiedRecord, Integer>
{

	@Query("SELECT mr FROM ModifiedRecord mr LEFT JOIN mr.regId r LEFT JOIN r.allProductComparativeSheet apc LEFT JOIN apc.allProducts a LEFT JOIN a.allProductName an WHERE an.type=:productType AND mr.action =:actionType ORDER BY mr.id DESC")
	List<ModifiedRecord> getAllModifiedRecordsByType(@Param("productType") String productType, @Param("actionType") String actionType);

	ModifiedRecord findById(int id);

}
