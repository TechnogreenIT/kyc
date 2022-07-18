package com.tes.repository.environmentalofficer.waterinventory;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.tes.model.WastewaterRecycle;

@Repository
public interface WasteWaterRecycleRepository extends JpaRepository<WastewaterRecycle, Long>
{

	@Query("SELECT DISTINCT(wts.label) FROM WastewaterRecycle wwr LEFT JOIN wwr.wastewaterTreatment wts")
	List<String> findAllRecycleTypeLabel();

	@Query("SELECT wwr.recycledTo  FROM WastewaterRecycle wwr LEFT JOIN wwr.wastewaterTreatment wts where wts.label=:label")
	List<String> findAllRecycleTypeLabel(@Param("label") String label);

	@Query("SELECT wwr FROM WastewaterRecycle wwr")
	List<WastewaterRecycle> findAllWasteWaterRecycle();

	@Query("SELECT wwr.isMeter FROM WastewaterRecycle wwr LEFT JOIN wwr.wastewaterTreatment wts WHERE  wts.label=:treatType AND wwr.recycledTo= :recycledTo")
	public boolean getMetervalueOfRecycledTo(@Param("treatType") String treatType, @Param("recycledTo") String recycledTo);

	@Query("SELECT wwr FROM WastewaterRecycle wwr where wwr.wastewaterTreatment.wastewaterTreatmentId=:wastewaterTreatmentId order by wwr.useType")
	List<WastewaterRecycle> getWasteWaterRecycle(@Param("wastewaterTreatmentId") int wastewaterTreatmentId);

	@Query("SELECT wwr FROM WastewaterRecycle wwr LEFT JOIN wwr.wastewaterTreatment wt where wt.treatmentType=:treatType AND wt.label=:treatLabel")
	List<WastewaterRecycle> findAllWasteWaterRecycleBytreatLabel(@Param("treatType") String treatType, @Param("treatLabel") String treatLabel);

	@Query("SELECT NEW WastewaterRecycle(wwr.wastewaterRecycleId,wwr.recycledTo) FROM WastewaterRecycle wwr LEFT JOIN wwr.wastewaterTreatment wts where wts.label=:label")
	List<WastewaterRecycle> findAllRecycleIdAndLabel(@Param("label") String label);
}
