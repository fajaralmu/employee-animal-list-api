package com.fajar.employeedataapi;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.fajar.employeedataapi.entities.Employee;
import com.fajar.employeedataapi.entities.Role;
import com.fajar.employeedataapi.entities.Salary;
import com.fajar.employeedataapi.model.EmployeeModel;
import com.fajar.employeedataapi.repository.EmployeeRepository;
import com.fajar.employeedataapi.repository.RoleRepository;
import com.fajar.employeedataapi.repository.SalaryRepository;
import com.fajar.employeedataapi.service.EmployeeService;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = { App.class })
@Slf4j
@TestPropertySource(locations = { "classpath:application.properties" })
@Data
public class EmployeeServiceTest {

	private static final Long SALARY = 1500000L;
	@Autowired
	private EmployeeRepository employeeRepository;
	
	@Autowired
	private RoleRepository roleRepository;
	
	@Autowired
	private SalaryRepository salaryRepository;
	
	@Autowired
	private EmployeeService employeeService;
	 
	private Employee sampleEmployee;
	private Salary sampleSalary;
	private Role sampleRole;
	
	
	
	@Before
	public void init() {
		if (null == sampleRole) {
			sampleRole = roleRepository.saveAll(roles()).get(0);
		}		
		if (null == sampleEmployee) {
			sampleEmployee = employeeRepository.save(employee());
		}
		if (null == sampleSalary) {
			sampleSalary = salaryRepository.save(salary());
		}
	}
	
	@Test
	public void insertTest() {
		EmployeeModel result = employeeService.insert(employeeModel());
		assertNotNull(result);
	}
	
	@Test
	public void list() {
		List<EmployeeModel> list = employeeService.list();
		assertTrue(list.size() > 0);
	}
	
	@Test
	public void getById() {
		EmployeeModel result = employeeService.getById(sampleEmployee.getId());
		assertNotNull(result);
	}
	@Test
	public void delete() {
		boolean result = employeeService.deleteById(sampleEmployee.getId());
		assertTrue(result);
	}
	@Test
	public void update() {
		EmployeeModel result = employeeService.update(sampleEmployee.getId(), employeeModel());
		assertNotNull(result);
	}

	private Salary salary() {
		
		Salary s = new Salary(SALARY);
		s.setId(sampleEmployee.getId());
		return s;		
	}

	private Employee employee() {
		 
		Employee employee = new Employee();
		employee.setAddress("Address");
		employee.setFullName("Full Name");
		return employee ;
	}

	
	EmployeeModel employeeModel() {
		 
		return EmployeeModel.builder()
				.fullName("Employee Name")
				.address("Address")
				.dob(new Date())
				.roleId(1)
				.salary(2000000L)
				.build()
				;
	}


	private List<Role> roles() {
		List<Role> roles = new ArrayList<>();
		Role role1 = new Role(1);
		role1.setName("role 1");
		roles.add(role1 );
		Role role2 = new Role(2);
		role2.setName("role 1");
		roles.add(role2 );
		return roles ;
	}
}
