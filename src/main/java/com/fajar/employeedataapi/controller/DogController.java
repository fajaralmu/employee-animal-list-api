package com.fajar.employeedataapi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.fajar.employeedataapi.model.SubBreed;
import com.fajar.employeedataapi.service.DogService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController 
public class DogController {

	@Autowired
	private DogService dogService;
	 @GetMapping("/dogs")
	 public List<SubBreed> allBreeds(){
		 return dogService.getAllBreeds();
		 
	 }
	 @GetMapping("/dogs/{breed}")
	 public  SubBreed getSubBreed(@PathVariable(name="breed") String breed){
		 return dogService.getSubBreed(breed);
		 
	 }
	
}
