package com.tes.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tes.model.Users;

@Repository
public interface LoginRepository extends JpaRepository<Users, Long>
{

	@Override
	List<Users> findAll();
}
