package com.securityhub.demo.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.securityhub.demo.dto.GetSecurityFindingDto;
import com.securityhub.demo.exception.RecordNotFoundException;
import com.securityhub.demo.model.EmployeeEntity;
import com.securityhub.demo.repository.EmployeeRepository;

@Service
public class EmployeeService {

	@Autowired
	EmployeeRepository repository;

	public List<EmployeeEntity> getAllEmployees() {
		List<EmployeeEntity> employeeList = repository.findAll();

		if (employeeList.size() > 0) {
			return employeeList;
		} else {
			return new ArrayList<EmployeeEntity>();
		}
	}

	public EmployeeEntity getEmployeeById(Long id) throws RecordNotFoundException {
		Optional<EmployeeEntity> employee = repository.findById(id);

		if (employee.isPresent()) {
			return employee.get();
		} else {
			throw new RecordNotFoundException("No employee record exist for given id");
		}
	}

	public EmployeeEntity createOrUpdateEmployee(EmployeeEntity entity) throws RecordNotFoundException {
		/*
		 * Optional<EmployeeEntity> employee = repository.findById(entity.getId());
		 * 
		 * if(employee.isPresent()) { EmployeeEntity newEntity = employee.get();
		 * newEntity.setEmail(entity.getEmail());
		 * newEntity.setFirstName(entity.getFirstName());
		 * newEntity.setLastName(entity.getLastName());
		 * 
		 * newEntity = repository.save(newEntity);
		 * 
		 * return newEntity; } else {
		 */
		entity = repository.save(entity);

		return entity;
		/* } */
	}

	public void deleteEmployeeById(Long id) throws RecordNotFoundException {
		Optional<EmployeeEntity> employee = repository.findById(id);

		if (employee.isPresent()) {
			repository.deleteById(id);
		} else {
			throw new RecordNotFoundException("No employee record exist for given id");
		}
	}

	public void createAwsFindings(List<GetSecurityFindingDto> awsData) throws RecordNotFoundException {
		for (GetSecurityFindingDto getSecurityFindingDto : awsData) {

			EmployeeEntity entity = new EmployeeEntity(getSecurityFindingDto.getTitle(),
					getSecurityFindingDto.getDescription(), getSecurityFindingDto.getFinding_Id(),
					getSecurityFindingDto.getSeverity(), getSecurityFindingDto.getAccount_Id());
			entity = repository.save(entity);
		}

	}
	
	public List<Object[]> countBySeverityName() {
        return repository.countBySeverityName();
    }
	
	public List<Object[]> countByAccount_Id() {
        return repository.countByAccount_Id();
    }
	
	public List<Object[]> accountIdFilterSeverity() {
        return repository.accountIdFilterSeverity();
    }
	
}