package com.fajar.employeedataapi.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Table(name="role")
@Entity
@Data
public class Role implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -6832342883052590348L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="role_id")
	private Integer id;
	@Column(name="role_name")
	private String name;

	public Role() {}
	public Role(Integer id) {
		this.id = id;
	}
}
