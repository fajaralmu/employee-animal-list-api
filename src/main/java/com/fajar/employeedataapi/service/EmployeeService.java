package com.fajar.employeedataapi.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fajar.employeedataapi.entities.Employee;
import com.fajar.employeedataapi.entities.Salary;
import com.fajar.employeedataapi.exception.ApplicationException;
import com.fajar.employeedataapi.exception.NotFoundException;
import com.fajar.employeedataapi.model.EmployeeModel;
import com.fajar.employeedataapi.repository.EmployeeRepository;
import com.fajar.employeedataapi.repository.SalaryRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class EmployeeService {

	@Autowired
	private SessionFactory sessionFactory;
	@Autowired
	private EmployeeRepository employeeRepository;
	@Autowired
	private SalaryRepository salaryRepository; 

	public EmployeeModel insert(EmployeeModel employeeModel) {
		log.info("insert data : {}", employeeModel);
		
		employeeModel.setId(null);
		
		Transaction tx = null;
		Session s = sessionFactory.openSession();
		try {
			tx = s.beginTransaction();
			Employee employee = employeeModel.toEntity();
			int employeeId = (Integer) s.save(employee);
			
			Salary salary = employee.getSalary();
			salary.setId(employeeId);
			s.save(salary);

			tx.commit();
			
			log.info("Success inserting data");
			employee.setSalary(salary);
			return employee.toModel();
		} catch (Exception e) {
			e.printStackTrace();
			if (null != tx) {
				tx.rollback();
			}
			throw new ApplicationException(e);

		} finally {
			if (null != s)
				s.close();
		}

	}

	public List<EmployeeModel> list() {
		List<Employee> employees = employeeRepository.findByOrderById();
		List<Salary> salaries = new ArrayList<>();
		if (employees.size() > 0) {
			salaries = salaryRepository.findByIdIn(idList(employees));
			mapEmployeesAndSalaries(employees, salaries);
		}
		return toModel(employees);
	}

	private List<EmployeeModel> toModel(List<Employee> employees) {
		List<EmployeeModel> list = new ArrayList<>();
		for (Employee employee : employees) {
			list.add(employee.toModel());
		}
		return list;
	}

	private static void mapEmployeesAndSalaries(List<Employee> employees, List<Salary> salaries) {
		Map<Integer, Salary> map = new HashMap<Integer, Salary>() {
			{
				for (Salary salary : salaries) {
					put(salary.getId(), salary);
				}
			}
		};
		for (Employee employee : employees) {
			employee.setSalary(map.get(employee.getId()));
		}

	}

	private List<Integer> idList(List<Employee> employees) {
		List<Integer> list = new ArrayList<>();
		for (Employee employee : employees) {
			list.add(employee.getId());
		}
		return list;
	}

	public EmployeeModel getById(int id) {
		Employee employee = getEmployeeRecord(id);
		if (employee != null)
			return employee.toModel();
		
		throw new NotFoundException("Employee with id: " + id + " not found");
	}

	private Employee getEmployeeRecord(int id) {
		Optional<Employee> employee = employeeRepository.findById(id);
		if (employee.isPresent()) {
			Optional<Salary> salary = salaryRepository.findById(employee.get().getId());
			if (salary.isPresent()) {
				employee.get().setSalary(salary.get());
			}
			return employee.get();
		}
		return null;
	}

	public boolean deleteById(int id) {
		Transaction tx = null;
		Session s = sessionFactory.openSession();
		try {
			tx = s.beginTransaction();

			Employee employee 	= s.get(Employee.class, id);
			Salary salary 		= s.get(Salary.class, id);
			
			s.delete(employee);
			s.delete(salary);

			tx.commit();
			return true;
		} catch (Exception e) {
			if (null != tx) {
				tx.rollback();
			}
			throw new ApplicationException(e);

		} finally {
			if (null != s)
				s.close();
		}
	}

	public EmployeeModel update(int id, EmployeeModel model) {
		
		Transaction tx = null;
		Session s = sessionFactory.openSession();
		try {
			tx = s.beginTransaction();

			Employee employee 	= s.get(Employee.class, id);
			Salary salary 		= s.get(Salary.class, id);
			
			if (null == salary || null == employee) {
				throw new NotFoundException("Record not found");
			}
			
			employee.setUpdatedField(model);
			if (null !=model.getSalary()) {
				salary.setSalary(model.getSalary());
			}
			
			s.merge(employee);
			s.merge(salary);

			tx.commit();
			
			employee.setSalary(salary);
			return employee.toModel();
		} catch (Exception e) {
			if (null != tx) {
				tx.rollback();
			}
			throw new ApplicationException(e);

		} finally {
			if (null != s)
				s.close();
		}
		
	}

}
