package com.tes.repository.environmentalofficer;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tes.model.StackOp;

@Repository
public interface StackOpRepository extends JpaRepository<StackOp, Long>
{

}
