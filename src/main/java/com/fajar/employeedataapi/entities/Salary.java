package com.fajar.employeedataapi.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Table(name="salary")
@Entity
@Data
public class Salary implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7881030534300526248L;
	
	@Id
	@Column(name="employee_id")
	private Integer id;
	private Long salary;

	public Salary() {}
	public Salary(Long salary) {
		this.salary = salary;
	}
}
