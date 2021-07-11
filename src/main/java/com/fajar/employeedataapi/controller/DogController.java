package com.fajar.employeedataapi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.fajar.employeedataapi.model.SubBreed;
import com.fajar.employeedataapi.service.DogService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController 
public class DogController {

	@Autowired
	private DogService dogService;
	@ApiOperation(value = "Get All dogs from dog.ceo api")
	@ApiResponses(value = {
	        @ApiResponse(code = 200, message = "Successfully retrieved list"),
	        @ApiResponse(code = 400, message = "Bad Request"),
	        @ApiResponse(code = 500, message = "Server Error"), 
	}
	)
	 @GetMapping("/dogs")
	 public List<SubBreed> allBreeds(){
		 return dogService.getAllBreeds();
		 
	 }
	
	@ApiOperation(value = "Get specific dog details. details included are sub breed and images of the dog", response = SubBreed.class)
	@ApiResponses(value = {
	        @ApiResponse(code = 200, message = "Successfully retrieved list"),
	        @ApiResponse(code = 400, message = "Bad Request"),
	        @ApiResponse(code = 500, message = "Server Error"), 
	}
	)
	 @GetMapping("/dogs/{breed}")
	 public  SubBreed getSubBreed(@PathVariable(name="breed") String breed){
		 return dogService.getSubBreed(breed);
		 
	 }
	
}
