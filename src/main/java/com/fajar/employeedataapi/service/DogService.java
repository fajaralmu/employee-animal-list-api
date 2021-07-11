package com.fajar.employeedataapi.service;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import com.fajar.employeedataapi.exception.ApplicationException;
import com.fajar.employeedataapi.model.DogResponse;
import com.fajar.employeedataapi.model.SubBreed;

@Service
public class DogService {
	
	@Autowired
	private RestTemplate restTemplate;
	
	static final String ALL_BREED_LIST_URL 		= "https://dog.ceo/api/breeds/list/all";
	static final String BREED_IMAGE_URL 		=  "https://dog.ceo/api/breed/{breed}/images";
	static final String BREED_LIST_URL 			=  "https://dog.ceo/api/breed/{breed}/list";

	
	private DogResponse allBreeds() throws RestClientException, URISyntaxException {
		
		ResponseEntity<DogResponse> response = restTemplate.getForEntity(new URI(ALL_BREED_LIST_URL), DogResponse.class);
		return response.getBody();
	}
	
	private DogResponse breedImageList(String breed) throws RestClientException, URISyntaxException {
		
		ResponseEntity<DogResponse> response = restTemplate.getForEntity(
				new URI(BREED_IMAGE_URL.replace("{breed}", breed)), 
				DogResponse.class);
		return response.getBody();
	}
	private DogResponse subBreedList(String breed) throws RestClientException, URISyntaxException {
		
		ResponseEntity<DogResponse> response = restTemplate.getForEntity(
				new URI(BREED_LIST_URL.replace("{breed}", breed)), 
				DogResponse.class);
		return response.getBody();
	}
	
	
	public static void main(String[] args) throws RestClientException, URISyntaxException {
		DogService ds 		= new DogService();
		ds.restTemplate 	= new RestTemplate();
		System.out.println(ds.allBreeds());
	}


	public List<SubBreed> getAllBreeds() {
		try {
			DogResponse breeds = allBreeds();
			return breeds.getAllBreedsResponse();
		} catch ( Exception e) {
			e.printStackTrace();
			throw new ApplicationException(e);
		}
	}


	public SubBreed getSubBreed(String breed)  {
		try {
			DogResponse imageList 		= breedImageList(breed);
			DogResponse subBreedList 	= subBreedList(breed);
			
			return SubBreed.breedDetail(breed, subBreedList, imageList);
		} catch (Exception e) {
			e.printStackTrace();
			throw new ApplicationException(e);
		}
	}

}
