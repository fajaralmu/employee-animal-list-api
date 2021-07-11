package com.fajar.employeedataapi;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.fajar.employeedataapi.repository.EmployeeRepository;
import com.fajar.employeedataapi.repository.RoleRepository;
import com.fajar.employeedataapi.repository.SalaryRepository;
import com.fajar.employeedataapi.service.EmployeeService;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = App.class )
@TestPropertySource(locations = { "classpath:application.properties" })
@Slf4j
public class EmployeeIntegrationTest {
	@Autowired
	private EmployeeRepository employeeRepository;
	@Autowired
	private RoleRepository roleRepository;
	@Autowired
	private SalaryRepository salaryRepository; 
	
	@Autowired
	private ObjectMapper objectMapper;
	@Autowired
	private WebApplicationContext webApplicationContext;
	 
	
	private MockMvc mvc; 
	EmployeeServiceTest test = new EmployeeServiceTest();
			
	@Before
	public void setup() { 
		mvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
		 test.setEmployeeRepository(employeeRepository);
		 test.setRoleRepository(roleRepository);
		 test.setSalaryRepository(salaryRepository);
		 test.init();
	}
	
	@Test
    public void employeeList() throws Exception { 
		 mvc.perform(MockMvcRequestBuilders.get("/employee") 
		         .contentType(MediaType.APPLICATION_JSON))
		 .andExpect(MockMvcResultMatchers.status().isOk());
    }
	@Test
	public void employeeById() throws Exception { 
		mvc.perform(MockMvcRequestBuilders.get("/employee/"+test.getSampleEmployee().getId()) 
				.contentType(MediaType.APPLICATION_JSON))
		.andExpect(MockMvcResultMatchers.status().isOk());
	}
	@Test
	public void insertEmployee() throws Exception { 
		  
		
		mvc.perform(MockMvcRequestBuilders.put("/employee")
				.content(objectMapper.writeValueAsString(test.employeeModel() ))
				.contentType(MediaType.APPLICATION_JSON))
		.andExpect(MockMvcResultMatchers.status().isCreated());
	}
	@Test
	public void updateEmployee() throws Exception { 
		
		
		mvc.perform(MockMvcRequestBuilders.post("/employee/"+test.getSampleEmployee().getId())
				.content(objectMapper.writeValueAsString(test.employeeModel() ))
				.contentType(MediaType.APPLICATION_JSON))
		.andExpect(MockMvcResultMatchers.status().isOk());
	}
	 
	 
	 
//	@Test
//	public void updateShipmentStatusTest() throws Exception { 
//		Shipment shipment = shipmentRepository.save(Shipment.newShipment());
//		order.setShipmentId(shipment.getId());
//		orderRepository.save(order);
//		EmployeeModel payload = new EmployeeModel();
//		payload.setOrder(order);
//		payload.setShipmentStatus(ShipmentStatus.MISSROUTE);
//		
//		mvc.perform(MockMvcRequestBuilders.post("/admin/order/shipment/updatestatus")
//				.header("token", USER_TOKEN)
//				.content(objectMapper.writeValueAsString(payload ))
//				.contentType(MediaType.APPLICATION_JSON))
//		.andExpect(MockMvcResultMatchers.status().isOk());
//	}

}
