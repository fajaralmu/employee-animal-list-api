//package com.fajar.employeedataapi;
//
//import java.util.ArrayList;
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
//
//import com.fajar.employeedataapi.entities.OrderItem;
//import com.fajar.employeedataapi.entities.Product;
//import com.fajar.employeedataapi.entities.employeedataapi;
//import com.fajar.employeedataapi.entities.User;
//import com.fajar.employeedataapi.model.EmployeeModel;
//import com.fajar.employeedataapi.model.WebResponse;
//import com.fajar.employeedataapi.repository.EmployeeRepository;
//import com.fajar.employeedataapi.repository.RoleRepository;
//import com.fajar.employeedataapi.service.OrderService;
//import com.fajar.employeedataapi.service.ProductService;
//import com.fajar.employeedataapi.service.UserService;
//
//import junit.framework.Assert;
//import lombok.extern.slf4j.Slf4j;
//
////@RunWith(SpringRunner.class)
////@SpringBootTest(classes = {App.class}, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
//@RunWith(SpringJUnit4ClassRunner.class)
//@SpringBootTest(classes = { App.class })
//@Slf4j
//@TestPropertySource(locations = { "classpath:application.properties" })
//public class ProductServiceTest {
//	
//	@Autowired
//	private EmployeeRepository productRepository;
//	@Autowired
//	private RoleRepository userRepository;
//	@MockBean
//	private UserService userService;
//	
//	@Autowired
//	private ProductService service;
//	
//	static final String ProductCode = "1";
//	@Before
//	public void setup() {
//		User user = userRepository.findTop1ByToken(User.sampleUser().getToken());
//		if (user == null) {
//			user = userRepository.save(User.sampleUser());
//		}
//		Mockito.when(userService.getLoggedUser(Mockito.any(HttpServletRequest.class)))
//		.thenReturn(user);
//		productRepository.saveAll(products());
//	}
//	 
//	static User user() {
//		return User.sampleUser();
//	}
//	static List<Product> products() {
//		List<Product> products = new ArrayList<>();
//		products.add(Product.builder().code(ProductCode).count(4).name("Product").price(500).build());
//		return products ;
//	} 
//	@Test
//	public void testGetProducts() {
//		WebResponse response = service.getProducts();
//		Assert.assertTrue(!response.getItems().isEmpty());
//	}
//	@Test
//	public void testSubmitOrder() throws Exception {
//		
//		WebResponse response = service.order(orderRequest(), httpRequest());
//		log.info("response: {}", response);
//		Assert.assertNotNull(response.getOrder());
//	}
//	private HttpServletRequest httpRequest() {
//		return Mockito.mock(HttpServletRequest.class);
//	}
//	private EmployeeModel orderRequest() {
//		EmployeeModel request = new EmployeeModel();
//		request.setOrder(sampleOrder(productRepository.findTop1ByCode(ProductCode)));
//		return request ;
//	}
//	public static employeedataapi sampleOrder(Product product1) {
//		employeedataapi order = new employeedataapi();
//		order.setAddress("address");
//		order.setEmail("email@mail.com");
//		order.setName("Customer ABC");
//		order.setPhone("093849580");
//		
//		List<OrderItem> items = new ArrayList<>();
//		OrderItem item= new OrderItem();
//		item.setCount(1);
//		item.setProduct(product1);
//		item.setPrice(product1.getPrice());
//		items.add(item);
//		order.setItems(items );
//		return order;
//	}
//
//}
