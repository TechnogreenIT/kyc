package com.tes.repository.environmentalofficer;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tes.model.EnvEC;
@Repository
public interface EnvECRepository extends JpaRepository<EnvEC, Long> {
	@Override
	@SuppressWarnings("unchecked")
	EnvEC save(EnvEC envEC);


}
