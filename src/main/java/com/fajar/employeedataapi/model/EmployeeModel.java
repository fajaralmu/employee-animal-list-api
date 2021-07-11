package com.fajar.employeedataapi.model;

import java.io.Serializable;
import java.util.Date;

import com.fajar.employeedataapi.entities.Employee;
import com.fajar.employeedataapi.entities.Role;
import com.fajar.employeedataapi.entities.Salary;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
@Data 
@Builder
@AllArgsConstructor 
public class EmployeeModel implements Serializable {/**
	 * 
	 */
	private static final long serialVersionUID = -383594993483047658L; 

	private Integer id; 
	@JsonProperty("full_name")
	private String fullName;
	@JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ssZ")
	private Date dob;
	private String address;
	@JsonProperty("role_id")
	private Integer roleId; 
	@JsonProperty("role_name")
	private String roleName;
	
	private Long salary;
	
	public EmployeeModel() { }
	@JsonIgnore
	public Employee toEntity() {
		Employee  employee = new Employee();
		employee.setDob(dob);
		employee.setFullName(fullName);
		employee.setAddress(address);
		employee.setRole(new Role(roleId));
		employee.setSalary(new Salary(salary));
		return employee;
	}
}
