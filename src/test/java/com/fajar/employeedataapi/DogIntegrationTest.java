package com.fajar.employeedataapi;

import java.net.URISyntaxException;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.context.WebApplicationContext;

import lombok.extern.slf4j.Slf4j;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = App.class )
@TestPropertySource(locations = { "classpath:application.properties" })
@Slf4j
public class DogIntegrationTest {
	@MockBean
	private RestTemplate restTemplate;
	
	@Autowired
	private WebApplicationContext webApplicationContext;
	 
	
	private MockMvc mvc; 
	DogServiceTest test = new DogServiceTest();
			
	@Before
	public void setup() throws RestClientException, URISyntaxException { 
		mvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
		test.setRestTemplate(restTemplate);
		test.init();
	}
	
	@Test
    public void allBreeds() throws Exception { 
		 mvc.perform(MockMvcRequestBuilders.get("/dogs") 
		         .contentType(MediaType.APPLICATION_JSON))
		 .andExpect(MockMvcResultMatchers.status().isOk());
    }
	@Test
	public void breedDetail() throws Exception { 
		mvc.perform(MockMvcRequestBuilders.get("/dogs/"+DogServiceTest.SAMPLE_BREED) 
				.contentType(MediaType.APPLICATION_JSON))
		.andExpect(MockMvcResultMatchers.status().isOk());
	}
	 

}
