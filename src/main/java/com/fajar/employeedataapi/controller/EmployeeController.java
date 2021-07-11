package com.fajar.employeedataapi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.fajar.employeedataapi.model.EmployeeModel;
import com.fajar.employeedataapi.service.EmployeeService;

import io.swagger.annotations.ApiResponse;
import lombok.extern.slf4j.Slf4j;
 
@RestController
@Slf4j
public class EmployeeController {
	
	@Autowired
	private EmployeeService employeeService;
	
	@PutMapping(value="/employee" )
	@ResponseStatus(value = HttpStatus.CREATED)
	public void insert(@RequestBody EmployeeModel body) {
		log.info("insert: {}", body);
		employeeService.insert(body); 
	}
	
	@GetMapping("/employee") 
	public List<EmployeeModel> list() {
		return employeeService.list();
	}
	@GetMapping("/employee/{id}") 
	public EmployeeModel getById(@PathVariable(name="id", required = true) int id) {
		return employeeService.getById(id);
	}
	
	@DeleteMapping("/employee/{id}") 
	public void deleteById(@PathVariable(name="id", required = true) int id) {
		employeeService.deleteById(id);
	}
	
	@PostMapping(value="/employee/{id}" ) 
	public void update(@RequestBody EmployeeModel body, @PathVariable(name="id", required = true) int id) {
		log.info("update: {}", body);
		employeeService.update(id, body);
	}
}
