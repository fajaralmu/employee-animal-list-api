package com.fajar.employeedataapi;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import com.fajar.employeedataapi.model.DogResponse;
import com.fajar.employeedataapi.model.SubBreed;
import com.fajar.employeedataapi.service.DogService;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = { App.class })
@Slf4j
@Data
@TestPropertySource(locations = { "classpath:application.properties" })
public class DogServiceTest {

	public static final String SAMPLE_BREED = "breed1";
	@MockBean
	private RestTemplate restTemplate;
	@Autowired
	private DogService dogService;

	@Before
	public void init() throws RestClientException, URISyntaxException {

		when(restTemplate.getForEntity(new URI(DogService.ALL_BREED_LIST_URL), DogResponse.class))
			.thenReturn(new ResponseEntity<DogResponse>(allBreedListResponse(), HttpStatus.OK));
		
		when(restTemplate.getForEntity(new URI(DogService.BREED_IMAGE_URL.replace("{breed}", SAMPLE_BREED)), DogResponse.class))
			.thenReturn(new ResponseEntity<DogResponse>(imageListResponse(), HttpStatus.OK));
		
		when(restTemplate.getForEntity(new URI(DogService.BREED_LIST_URL.replace("{breed}", SAMPLE_BREED)), DogResponse.class))
			.thenReturn(new ResponseEntity<DogResponse>(breedListResponse(), HttpStatus.OK));
	}

	private DogResponse breedListResponse() { 
		DogResponse response = new DogResponse();
		response.setMessage(Arrays.asList("breed1", "breed2", "breed3"));
		return response ;
	}

	private DogResponse imageListResponse() { 
		DogResponse response = new DogResponse();
		response.setMessage(Arrays.asList("www.imageurl.com/image.jpg", "www.imageurl.com/image2.jpg", "www.imageurl.com/image3.jpg"));
		return response ;
	}

	private DogResponse allBreedListResponse() { 
		DogResponse response = new DogResponse();
		response.setMessage(new HashMap<String, List<String>>() {
			{
				put("breed1", Arrays.asList("b1", "b2", "b3")); 
				put("breed4", Arrays.asList("b4", "b5", "b6"));
				put("breed2", Arrays.asList("b7", "b8", "b9"));
			}
		});
		return response ;
	}
	
	@Test
	public void allBreed() {
		List<SubBreed> result = dogService.getAllBreeds();
		assertTrue(result.size() > 0);
	}

	@Test
	public void detail() {
		SubBreed result = dogService.getSubBreed(SAMPLE_BREED);
		assertNotNull(result);
	}
}
