package com.fajar.employeedataapi.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.fajar.employeedataapi.model.EmployeeModel;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Table(name="employee")
@Entity
@Data
public class Employee implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -6402717609716072112L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	@Column(name="full_name")
	private String fullName;
	@Column
	private String address;
	@Column
	private Date dob;
	
	@ManyToOne
	@JoinColumn(name="role_id")
	@JsonIgnore
	private Role role;
	
	@Transient
	private Salary salary;
	public Employee() {
		
	}

	public EmployeeModel toModel() {
		Role _role = getRole();
		if (role == null) {
			_role = new Role();
		}
		return EmployeeModel.builder()
				.id(id)
				.address(address)
				.dob(dob)
				.fullName(fullName)
				.roleId(_role.getId())
				.roleName(_role.getName())
				.salary(salary==null?null:salary.getSalary())
				.build();
	}

	public void setUpdatedField(EmployeeModel model) {
		if (null != model.getFullName()) {
			setFullName(model.getFullName());
		}
		if (null != model.getDob()) {
			setDob(model.getDob());
		}
		if (null != model.getRoleId()) {
			setRole(new Role(model.getRoleId()));
		}
		if (null != model.getAddress()) {
			setAddress(model.getAddress());
		}
	}
	
	 
}
