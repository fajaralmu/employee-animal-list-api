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

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.extern.slf4j.Slf4j;
 
@RestController
@Slf4j
public class EmployeeController {
	
	@Autowired
	private EmployeeService employeeService;
	
	@ApiOperation(value = "insert new employee", response = Void.class)
	@ApiResponses(value = {
	        @ApiResponse(code = 201, message = "Successfully inserted new employee"),
	        @ApiResponse(code = 400, message = "Bad Request"),
	        @ApiResponse(code = 500, message = "Server Error"), 
	}
	)
	@PutMapping(value="/employee" )
	@ResponseStatus(value = HttpStatus.CREATED)
	public void insert(@RequestBody EmployeeModel body) {
		log.info("insert: {}", body);
		employeeService.insert(body); 
	}
	
	@SuppressWarnings("unchecked")
	@ApiOperation(value = "Get All employee from database with role name being displayed and salary")
	@ApiResponses(value = {
	        @ApiResponse(code = 200, message = "Successfully retrieved list"),
	        @ApiResponse(code = 400, message = "Bad Request"),
	        @ApiResponse(code = 500, message = "Server Error"), 
	}
	)
	@GetMapping("/employee") 
	public List<EmployeeModel> list() {
		return employeeService.list();
	}
	
	@ApiOperation(value = "get employee by id")
	@ApiResponses(value = {
	        @ApiResponse(code = 200, message = "Successfully retrieved employee"),
	        @ApiResponse(code = 400, message = "Bad Request"),
	        @ApiResponse(code = 500, message = "Server Error"), 
	}
	)
	@GetMapping("/employee/{id}") 
	public EmployeeModel getById(@PathVariable(name="id", required = true) int id) {
		return employeeService.getById(id);
	}
	
	@ApiOperation(value = "delete employee by id", response = Void.class)
	@ApiResponses(value = {
	        @ApiResponse(code = 200, message = "Successfully deleted employee"),
	        @ApiResponse(code = 400, message = "Bad Request"),
	        @ApiResponse(code = 500, message = "Server Error"), 
	}
	)
	@DeleteMapping("/employee/{id}") 
	public void deleteById(@PathVariable(name="id", required = true) int id) {
		employeeService.deleteById(id);
	}
	
	@ApiOperation(value = "Update Employee data using id as path parameter and json as data that will be updated", notes="Only data passed will be updated. for example if we pass full_name then only full_name in database that being updated vice versa. parameter that can be updated are : full_name(string), address(string), dob(\"YYYY-MM-DD'T'hh:mm:ssZ\" example :\"2006-01-01T00:00:00Z\" ), role_id(int), salary:int", 
			response = Void.class)
	@ApiResponses(value = {
	        @ApiResponse(code = 200, message = "Successfully updated employee"),
	        @ApiResponse(code = 400, message = "Bad Request"),
	        @ApiResponse(code = 500, message = "Server Error"), 
	}
	)
	@PostMapping(value="/employee/{id}" ) 
	public void update(@RequestBody EmployeeModel body, @PathVariable(name="id", required = true) int id) {
		log.info("update: {}", body);
		employeeService.update(id, body);
	}
}
