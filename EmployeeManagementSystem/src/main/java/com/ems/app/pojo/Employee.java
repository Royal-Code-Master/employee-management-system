package com.ems.app.pojo;

import org.springframework.data.annotation.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class Employee {
	
	@Id
	private String id;
	private String employeeName;
	private String employeeEmail;
	private Long employeePhone;
	private String employeeGender;
	private String employeeSalary;
	private String employeeRole;
}
