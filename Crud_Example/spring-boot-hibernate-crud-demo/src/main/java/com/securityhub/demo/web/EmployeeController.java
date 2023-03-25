package com.securityhub.demo.web;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.securityhub.demo.dto.GetSecurityFindingDto;
import com.securityhub.demo.dto.SuppressDto;
import com.securityhub.demo.exception.RecordNotFoundException;
import com.securityhub.demo.model.EmployeeEntity;
import com.securityhub.demo.model.SuppressEntity;
import com.securityhub.demo.repository.SuppressRepository;
import com.securityhub.demo.service.EmployeeService;
import com.securityhub.demo.service.GetFindingService;
import com.securityhub.demo.service.SuppressService;

@RestController
@RequestMapping("/aws_rest")
public class EmployeeController {
	@Autowired
	EmployeeService service;

	@Autowired
	GetFindingService awsService;

	@Autowired
	SuppressService suppressService;

	@GetMapping
	public ResponseEntity<List<EmployeeEntity>> getAllEmployees() {
		List<EmployeeEntity> list = service.getAllEmployees();

		return new ResponseEntity<List<EmployeeEntity>>(list, new HttpHeaders(), HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<EmployeeEntity> getEmployeeById(@PathVariable("id") Long id) throws RecordNotFoundException {
		EmployeeEntity entity = service.getEmployeeById(id);

		return new ResponseEntity<EmployeeEntity>(entity, new HttpHeaders(), HttpStatus.OK);
	}

	@PostMapping
	public ResponseEntity<String> createOrUpdateEmployee() throws RecordNotFoundException {
		awsService.getFindingService();
//        EmployeeEntity updated = service.createOrUpdateEmployee(employee); 
		return new ResponseEntity<String>("Data added in db successfully", new HttpHeaders(), HttpStatus.OK);
	}

//    @DeleteMapping("/{id}")
//    public HttpStatus deleteEmployeeById(@PathVariable("id") Long id)
//                                                    throws RecordNotFoundException {
//        service.deleteEmployeeById(id);
//        return HttpStatus.FORBIDDEN;
//    }

//    @GetMapping("/getFindings")
//    public List<GetSecurityFindingDto> getFinding() {
//        GetFindingService gf = new GetFindingService();
//        gf.getFindingService();
//        return finding;
//    }

	@PostMapping(path = "/create_suppress_request")
	public ResponseEntity<String> createSuppressRequest(@RequestBody SuppressDto suppressDto) {
//		System.out.println(suppressDto.getFinding_Id());
//		System.out.println(suppressDto.getRequester_Name());
//		System.out.println(suppressDto.getComments());
		Date date = new Date();
		SuppressEntity addEntity = new SuppressEntity(suppressDto.getFinding_Id(), suppressDto.getRequester_Name(),
				suppressDto.getComments(),"PENDING", date);
		try {
			suppressService.createSuppress(addEntity);
		} catch (RecordNotFoundException e) {
			e.printStackTrace();
		}
		return new ResponseEntity<String>("Data added in db successfully", new HttpHeaders(), HttpStatus.OK);
	}
	
	
	
	
//	
//	@PostMapping
//    public ResponseEntity<?> create(@RequestBody SuppressDto suppressDto) {
//        SuppressEntity existingEntity = new SuppressEntity(suppressDto.getFinding_Id();
//        if (existingEntity.isPresent()) {
//            return new ResponseEntity<>("Data already exists in database", HttpStatus.CONFLICT);
//        } else {
//            MyEntity savedEntity = SuppressRepository.save(suppressDto);
//            return new ResponseEntity<>(savedEntity, HttpStatus.CREATED);
//        }
//    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleException(Exception e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

	
	
	
	

	@GetMapping(path = "/get_suppress_request")
	public ResponseEntity<List<SuppressEntity>> getAllSuppressRequest() {
		List<SuppressEntity> list = suppressService.getAllSuppressRequest();

		return new ResponseEntity<List<SuppressEntity>>(list, new HttpHeaders(), HttpStatus.OK);
	}

	@GetMapping("/get_suppress_request/{id}")
	public ResponseEntity<SuppressEntity> getSuppressById(@PathVariable("id") long id) throws RecordNotFoundException {
		SuppressEntity entity = suppressService.getSuppressById(id);

		return new ResponseEntity<SuppressEntity>(entity, new HttpHeaders(), HttpStatus.OK);
	}

	@PutMapping("/update_suppress_request")
	public ResponseEntity<String> UpdateSuppressById(@RequestBody SuppressEntity suppressEntity)
			throws RecordNotFoundException {
		suppressService.UpdateSuppressById(suppressEntity);
		return new ResponseEntity<String>("Updated Succesfully", new HttpHeaders(), HttpStatus.OK);
	}
	
	@GetMapping(path = "/list_of_status")
	public ResponseEntity <List<Object[]>> listOfStatus() {
		List<Object[]> list = suppressService.countByStatusName();

		return new ResponseEntity <List<Object[]>>(list, new HttpHeaders(), HttpStatus.OK);
	}
	
	
	@GetMapping(path = "/list_of_severity")
	public ResponseEntity <List<Object[]>> listOfSeverity() {
		List<Object[]> list = service.countBySeverityName();

		return new ResponseEntity <List<Object[]>>(list, new HttpHeaders(), HttpStatus.OK);
	}
	
	@GetMapping(path = "/list_of_account_id")
	public ResponseEntity <List<Object[]>> listOfAccountId() {
		List<Object[]> list = service.countByAccount_Id();

		return new ResponseEntity <List<Object[]>>(list, new HttpHeaders(), HttpStatus.OK);
	}
	
	@GetMapping(path = "/account_Id_Filter_Severity")
	public ResponseEntity <List<Object[]>> accountIdFilterSeverity() {
		List<Object[]> list = service.accountIdFilterSeverity();

		return new ResponseEntity <List<Object[]>>(list, new HttpHeaders(), HttpStatus.OK);
	}
	

}