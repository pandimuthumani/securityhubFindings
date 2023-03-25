package com.securityhub.demo.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.securityhub.demo.model.SuppressEntity;

@Repository
public interface SuppressRepository extends JpaRepository<SuppressEntity, Long> {
	
	@Query(value = "SELECT status, COUNT(status) FROM Security_Suppress GROUP BY status;", nativeQuery = true)
	List<Object[]> countByStatusName();
	
	
}
