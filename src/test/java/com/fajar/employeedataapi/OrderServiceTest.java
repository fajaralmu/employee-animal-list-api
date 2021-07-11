//package com.fajar.employeedataapi;
//
//import static org.junit.Assert.assertNotNull;
//import static org.junit.Assert.assertTrue;
//
//import java.util.List;
//
//import javax.servlet.http.HttpServletRequest;
//
//import org.junit.Before;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.mockito.Mockito;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.test.context.TestPropertySource;
//import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
//import org.springframework.web.multipart.MultipartFile;
//
//import com.fajar.employeedataapi.constant.ShipmentStatus;
//import com.fajar.employeedataapi.entities.OrderItem;
//import com.fajar.employeedataapi.entities.Product;
//import com.fajar.employeedataapi.entities.Shipment;
//import com.fajar.employeedataapi.entities.employeedataapi;
//import com.fajar.employeedataapi.entities.User;
//import com.fajar.employeedataapi.model.EmployeeModel;
//import com.fajar.employeedataapi.model.WebResponse;
//import com.fajar.employeedataapi.repository.OrderItemRepository;
//import com.fajar.employeedataapi.repository.OrderRepository;
//import com.fajar.employeedataapi.repository.EmployeeRepository;
//import com.fajar.employeedataapi.repository.ShipmentRepository;
//import com.fajar.employeedataapi.repository.RoleRepository;
//import com.fajar.employeedataapi.service.FileService;
//import com.fajar.employeedataapi.service.OrderService;
//import com.fajar.employeedataapi.service.UserService;
//
//import lombok.extern.slf4j.Slf4j;
//
//@RunWith(SpringJUnit4ClassRunner.class)
//@SpringBootTest(classes = { App.class })
//@Slf4j
//@TestPropertySource(locations = { "classpath:application.properties" })
//public class OrderServiceTest {
//	
//	@Autowired
//	private OrderService service;
//
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
//	@MockBean
//	private FileService fileService;
//	@MockBean
//	private UserService userService;
//	
//	employeedataapi order ;
//	@Before
//	public void setup() {
//		User user = userRepository.findTop1ByToken(User.sampleUser().getToken());
//		if (user == null) {
//			user = userRepository.save(User.sampleUser());
//		}
//		
//		Mockito.when(userService.getLoggedUser(Mockito.any(HttpServletRequest.class)))
//		.thenReturn(user);
//		
//		List<Product> products = productRepository.saveAll(ProductServiceTest.products());
//		Product product = products.get(0);
//		
//		order = ProductServiceTest.sampleOrder(product);
//		order.setUser(user);
//		order = orderRepository.save(order );
//		OrderItem orderItem = order.getItems().get(0);
//		orderItem.setOrder(order);
//		
//		orderItemRepository.save(orderItem);
//	}
//	
//	@Test
//	public void testGetFullOrder() throws Exception {
//		employeedataapi response = service.getFullOrder(1L);
//		assertNotNull(response);
//	}
//	
//	@Test
//	public void testGetOrders() {
//		WebResponse response = service.getOrderList();
//		assertTrue(response.getItems().size() > 0);
//	}
//	@Test
//	public void testGetUserOrders() {
//		WebResponse response = service.getUserOrderList(Mockito.mock(HttpServletRequest.class));
//		assertTrue(response.getItems().size() > 0);
//	}
//	@Test
//	public void testShipOrder() throws Exception {
//		order.setShipmentId(null);
//		orderRepository.save(order);
//		WebResponse response = service.shipOrder(order.getId());
//		assertNotNull(response);
//	}
//	@Test
//	public void cancelOrder() throws Exception {
//		order.setShipmentId(null);
//		orderRepository.save(order);
//		WebResponse response = service.cancelOrder(order.getId());
//		assertNotNull(response);
//	}
//	@Test
//	public void getShipmentStatusList() throws Exception {
//		WebResponse response = service.getShipmentStatusList();
//		assertNotNull(response);
//	}
//	
//	@Test
//	public void updateShipmentStatus() throws Exception {
//		Shipment shipment = shipmentRepository.save(Shipment.newShipment());
//		order.setShipmentId(shipment.getId());
//		orderRepository.save(order);
//		EmployeeModel request = EmployeeModel.builder().shipmentStatus(ShipmentStatus.DELIVERED).order(order).build();
//		WebResponse response = service.updateShipmentStatus(request );
//		assertNotNull(response);
//	}
//	
//	@Test
//	public void testGetShipmentStatus() throws Exception {
//		Shipment shipment = shipmentRepository.save(Shipment.newShipment());
//		order.setShipmentId(shipment.getId());
//		orderRepository.save(order);
//		WebResponse response = service.getShipmentStatus(shipment.getId(), Mockito.mock(HttpServletRequest.class) );
//		assertNotNull(response);
//	}
//	
//	@Test
//	public void testSubmitPaymentProof() throws Exception {
//		
//		Mockito.when(fileService.uploadDocument(Mockito.any(MultipartFile.class))).thenReturn("document.pdf");
//		WebResponse response = service.submitPaymentProof(order.getId(), Mockito.mock(MultipartFile.class),Mockito.mock(HttpServletRequest.class));
//		assertNotNull(response.getExtras());
//	}
//}
