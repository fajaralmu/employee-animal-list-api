package com.fajar.employeedataapi.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.fajar.employeedataapi.model.SubBreed;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Data
@Slf4j
public class DogResponse implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -6732331666157044489L;
	private Object message;
	private String status;

	public List<String> getMessageAsStringList(){
		return (List<String>) message;
	}
	public List<SubBreed> getAllBreedsResponse() {
		List<SubBreed> breeds = new ArrayList<SubBreed>();
		try {
			Map<String, List<String>> map = (Map<String, List<String>>) message;
			
			Set<String> keys = map.keySet();
			for (String key : keys) {
				breeds.add(SubBreed.builder()
								.breed(key)
								.subBreeds(subBreeds(map.get(key)))
								.build()
						);
			}
		} catch (Exception e) {
			log.error("Error converting message");
		}
		
		return breeds ;
	}

	private List<SubBreed> subBreeds(List<String> list) {
		
		List<SubBreed> result = new ArrayList<>();
		if (null == list) return result ;
		for (String breed : list) {
			result.add(SubBreed.builder().breed(breed).build());
		}
		return result;
	}
}
