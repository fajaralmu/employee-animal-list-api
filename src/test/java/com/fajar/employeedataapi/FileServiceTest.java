//package com.fajar.employeedataapi;
//
//import static org.junit.Assert.assertNotNull;
//
//import java.io.IOException;
//
//import org.apache.tomcat.util.http.fileupload.FileItem;
//import org.apache.tomcat.util.http.fileupload.disk.DiskFileItem;
//import org.junit.Before;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.mockito.Mockito;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.core.io.Resource;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.mock.web.MockMultipartFile;
//import org.springframework.test.context.TestPropertySource;
//import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
//import org.springframework.web.client.RestTemplate;
//import org.springframework.web.multipart.commons.CommonsMultipartFile;
//
//import com.fajar.employeedataapi.service.FileService;
//
//import lombok.extern.slf4j.Slf4j;
//
//@RunWith(SpringJUnit4ClassRunner.class)
//@SpringBootTest(classes = { App.class })
//@Slf4j
//@TestPropertySource(locations = { "classpath:application.properties" })
//public class FileServiceTest {
//
//	@Autowired
//	private FileService service;
//	
//	@MockBean
//	private RestTemplate restTemplate;
//	
//	@Value("classpath:react.png")
//	private Resource attachment;
//	
//	@Before
//	public void init() {
//		Mockito.when(restTemplate.postForEntity(Mockito.anyString(), Mockito.any(), Mockito.eq(String.class)))
//			.thenReturn(new ResponseEntity<String>(HttpStatus.OK));
//		
//	}
//	@Test
//	public void testUpload() throws IOException {
//		 
//		MockMultipartFile multipartFile = new MockMultipartFile("react.png", attachment.getInputStream());
//		String response = service.uploadDocument(multipartFile);
//		assertNotNull(response);
//	}
//}
