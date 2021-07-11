//package com.fajar.employeedataapi;
//
//import java.util.List;
//
//import org.junit.Before;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.core.io.Resource;
//import org.springframework.http.MediaType;
//import org.springframework.test.context.TestPropertySource;
//import org.springframework.test.context.junit4.SpringRunner;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
//import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
//import org.springframework.test.web.servlet.setup.MockMvcBuilders;
//import org.springframework.web.context.WebApplicationContext;
//
//import com.fajar.employeedataapi.constant.ShipmentStatus;
//import com.fajar.employeedataapi.entities.OrderItem;
//import com.fajar.employeedataapi.entities.Product;
//import com.fajar.employeedataapi.entities.Shipment;
//import com.fajar.employeedataapi.entities.employeedataapi;
//import com.fajar.employeedataapi.entities.User;
//import com.fajar.employeedataapi.model.EmployeeModel;
//import com.fajar.employeedataapi.repository.OrderItemRepository;
//import com.fajar.employeedataapi.repository.OrderRepository;
//import com.fajar.employeedataapi.repository.EmployeeRepository;
//import com.fajar.employeedataapi.repository.ShipmentRepository;
//import com.fajar.employeedataapi.repository.RoleRepository;
//import com.fajar.employeedataapi.service.FileService;
//import com.fasterxml.jackson.databind.ObjectMapper;
//
//import lombok.extern.slf4j.Slf4j;
//
//@RunWith(SpringRunner.class)
//@SpringBootTest(classes = App.class )
//@TestPropertySource(locations = { "classpath:application.properties" })
//@Slf4j
//public class IntegrationTestOrderManagementApi {
//	private static final Object USER_TOKEN = "admintoken";
//	@Autowired
//	private OrderRepository orderRepository;
//	@Autowired
//	private OrderItemRepository orderItemRepository;
//	@Autowired
//	private EmployeeRepository productRepository;
//	@Autowired
//	private RoleRepository userRepository;
//	@Autowired
//	private ShipmentRepository shipmentRepository;
//	
//	@Autowired
//	private ObjectMapper objectMapper;
//	@Autowired
//	private WebApplicationContext webApplicationContext;
//	 
//	
//	private MockMvc mvc; 
//	private List<Product> products;
//	private employeedataapi order;
//	
//	@Before
//	public void setup() { 
//		mvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
//		 
//		User user = userRepository.findTop1ByToken(User.sampleAdmin().getToken());
//		if (user == null) {
//			user = userRepository.save(User.sampleAdmin());
//		}
//
//		products = productRepository.saveAll(ProductServiceTest.products());
//		Product product = products.get(0);
//
//		order = ProductServiceTest.sampleOrder(product);
//		order.setUser(user);
//		order = orderRepository.save(order);
//		OrderItem orderItem = order.getItems().get(0);
//		orderItem.setOrder(order);
//
//		orderItemRepository.save(orderItem);
//	}
//	
//	@Test
//    public void getOrdersTest() throws Exception { 
//		 mvc.perform(MockMvcRequestBuilders.post("/admin/order/list")
//				 .header("token", USER_TOKEN)
//		         .contentType(MediaType.APPLICATION_JSON))
//		 .andExpect(MockMvcResultMatchers.status().isOk());
//    }
//	@Test
//    public void getOrderDetailTest() throws Exception { 
//		 mvc.perform(MockMvcRequestBuilders.post("/admin/order/detail/"+order.getId())
//				 .header("token", USER_TOKEN)
//		         .contentType(MediaType.APPLICATION_JSON))
//		 .andExpect(MockMvcResultMatchers.status().isOk());
//    }
//	@Test
//	public void getOrderShipTest() throws Exception { 
//		order.setShipmentId(null);
//		orderRepository.save(order);
//		mvc.perform(MockMvcRequestBuilders.post("/admin/order/ship/"+order.getId())
//				.header("token", USER_TOKEN)
//				.contentType(MediaType.APPLICATION_JSON))
//		.andExpect(MockMvcResultMatchers.status().isOk());
//	}
//	@Test
//	public void getOrderCancelTest() throws Exception { 
//		order.setShipmentId(null);
//		orderRepository.save(order);
//		mvc.perform(MockMvcRequestBuilders.post("/admin/order/cancel/"+order.getId())
//				.header("token", USER_TOKEN)
//				.contentType(MediaType.APPLICATION_JSON))
//		.andExpect(MockMvcResultMatchers.status().isOk());
//	}
//	@Test
//    public void getShipmentListTest() throws Exception { 
//		 mvc.perform(MockMvcRequestBuilders.post("/admin/order/shipment/statuslist")
//				 .header("token", USER_TOKEN))
//		 .andExpect(MockMvcResultMatchers.status().isOk());
//    }
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
//
//}
