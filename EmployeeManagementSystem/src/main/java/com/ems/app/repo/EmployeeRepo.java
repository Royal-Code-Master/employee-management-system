package com.ems.app.repo;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.ems.app.pojo.Employee;

@Repository
public interface EmployeeRepo extends MongoRepository<Employee, String>{

	boolean existsByIdAndEmployeeEmail(String id, String employeeEmail);

}
