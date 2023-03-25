package com.securityhub.demo.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.securityhub.demo.exception.RecordNotFoundException;
import com.securityhub.demo.model.EmployeeEntity;
import com.securityhub.demo.model.SuppressEntity;
import com.securityhub.demo.repository.SuppressRepository;

@Service
public class SuppressService {
	@Autowired
	SuppressRepository suppressRepository;

	public void createSuppress(SuppressEntity entity) throws RecordNotFoundException {

		suppressRepository.save(entity);

	}

	public List<SuppressEntity> getAllSuppressRequest() {
		List<SuppressEntity> suppressList = suppressRepository.findAll();

		if (suppressList.size() > 0) {
			return suppressList;
		} else {
			return new ArrayList<SuppressEntity>();
		}
	}

	public SuppressEntity getSuppressById(Long id) throws RecordNotFoundException {
		Optional<SuppressEntity> suppress = suppressRepository.findById(id);

		if (suppress.isPresent()) {
			return suppress.get();
		} else {
			throw new RecordNotFoundException("No employee record exist for given id");
		}
	}

	public SuppressEntity UpdateSuppressById(SuppressEntity suppressEntity) throws RecordNotFoundException {

		Optional<SuppressEntity> update = suppressRepository.findById(suppressEntity.getId());

		if (update.isPresent()) {
			SuppressEntity entity = update.get();
			entity.setStatus((suppressEntity.getStatus()));
			entity = suppressRepository.save(entity);

			return entity;
		} else {

			suppressEntity = suppressRepository.save(suppressEntity);

			return suppressEntity;
		}
	}
	
	public List<Object[]> countByStatusName() {
        return suppressRepository.countByStatusName();
    }

//	public Optional<SuppressEntity> findByFindingId(String finding_Id) {
//		suppressRepository.equals(findByFindingId(finding_Id));
//		return null;
//	}


}
