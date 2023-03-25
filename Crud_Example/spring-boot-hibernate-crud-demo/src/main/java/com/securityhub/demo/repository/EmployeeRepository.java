package com.securityhub.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.securityhub.demo.model.EmployeeEntity;
 
@Repository
public interface EmployeeRepository
        extends JpaRepository<EmployeeEntity, Long> {
 
	@Query(value = "SELECT severity, COUNT(severity) FROM Security_Findings GROUP BY severity;", nativeQuery = true)
	List<Object[]> countBySeverityName();
	
	@Query(value = "SELECT account_Id, COUNT(account_Id) FROM Security_Findings GROUP BY account_Id;", nativeQuery = true)
	List<Object[]> countByAccount_Id();
	
	@Query(value = "SELECT account_Id, severity, COUNT(*) FROM Security_Findings WHERE account_Id IN (account_Id) GROUP BY account_Id, severity;", nativeQuery = true)
	List<Object[]> accountIdFilterSeverity();
	
	
	
	
}
