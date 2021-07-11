package com.fajar.employeedataapi.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Builder.Default;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(value = Include.NON_NULL)
public class SubBreed implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -4421643142058658663L;

	private String breed;
	@JsonProperty("sub_breed")
	@Default
	private List<SubBreed> subBreeds = new ArrayList<>();
	
	private List<String> images;

	public static SubBreed breedDetail(String breed, DogResponse subBreedListResponse, DogResponse imageListResponse) {
		SubBreed subBreed = new SubBreed();
		subBreed.setBreed(breed);
		subBreed.setSubBreeds(extractSubBreedList(subBreedListResponse));
		if (imageListResponse != null)
			subBreed.setImages(imageListResponse.getMessageAsStringList());
		return subBreed;
	}

	private static List<SubBreed> extractSubBreedList(DogResponse subBreedList) {
		List<SubBreed> subBreeds = new ArrayList<>();
		if (null == subBreedList)
			return subBreeds;
		List<String> names = subBreedList.getMessageAsStringList();
		for (String string : names) {
			subBreeds.add(SubBreed.builder()
					.breed(string)
					.subBreeds(null)
					.build()
					);
		}
		return subBreeds;
	}
	
	
}
