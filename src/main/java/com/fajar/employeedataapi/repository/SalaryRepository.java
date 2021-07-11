package com.fajar.employeedataapi.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fajar.employeedataapi.entities.Salary;
@Repository
public interface SalaryRepository extends JpaRepository<Salary, Integer> {

	List<Salary> findByIdIn(List<Integer> idList);

	 

}
