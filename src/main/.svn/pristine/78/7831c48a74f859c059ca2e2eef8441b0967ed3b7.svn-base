package com.tes.repository.environmentalofficer;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.tes.model.ModifiedRecord;

@Repository
public interface ViewModifiedRecordRepository extends JpaRepository<ModifiedRecord, Long>
{

	// @Query("SELECT m.requestFor,m.requestForDate,m.quantity, m.modifiedDate, m.reason,m.newQuantity,e.employeeName FROM ModifiedRecord m, EmpData e where m.action='modify' AND m.modifiedBy = e.users.usersId")
	// public List<Object[]> getModifiedRecordAndEmpName();
}
