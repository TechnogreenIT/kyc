package com.tes.repository.environmentalofficer;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.tes.model.Containers;

@Repository
public interface ContainersRepository extends JpaRepository<Containers, Long>
{

	@Override
	@SuppressWarnings("unchecked")
	Containers save(Containers containers);

	@Query("SELECT c FROM  Containers c LEFT JOIN c.hazardousManifest WHERE  c.hazardousManifest.hazardousManifestId = :hmId")
	public List<Containers> containersDataById(@Param("hmId") int hmId);

	String containersType = "SELECT DISTINCT (c.containersType) FROM  Containers c, HazardousManifest h WHERE  h.submittedDate BETWEEN :from AND :to";

	@Query(value = containersType, nativeQuery = false)
	public List<Containers> containersTypeByDate(@Param("from") String from, @Param("to") String to);

	@Query("SELECT c FROM Containers c")
	List<Containers> findContainersData();

	@Query("SELECT c FROM Containers c INNER JOIN c.hazardousManifest h  WHERE  h.submittedDate BETWEEN :from AND :to")
	List<Containers> getTransportAndWasteDetails(@Param("from") String from, @Param("to") String to);

}
