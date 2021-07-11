package com.fajar.employeedataapi.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.Cache.ValueWrapper;
import org.springframework.cache.CacheManager;
import org.springframework.stereotype.Service;

import com.fajar.employeedataapi.model.DogResponse;
import com.fajar.employeedataapi.model.SubBreed;

@Service
public class TempCacheService {

	@Autowired
	private CacheManager cacheManager;
	
	public DogResponse getAllBreed(){
		ValueWrapper value = cacheManager.getCache("breed").get("all_breed");
		if (null == value) return null;
		Object saved = value.get();
		if (null == saved || !(saved instanceof DogResponse )) {
			return null;
		}
		return (DogResponse)saved;
	}
	public SubBreed getBreedDetail(String breed){
		ValueWrapper value = cacheManager.getCache("breed").get("detail-"+breed);
		if (null == value) return null;
		Object saved = value.get();
		if (null == saved || !(saved instanceof SubBreed )) {
			return null;
		}
		return (SubBreed)saved;
	} 
	public void putAllBreed(DogResponse body) { 
		cacheManager.getCache("breed").put("all_breed", body);
	}
	public void putDetailBreed(SubBreed subBreed) { 
		cacheManager.getCache("breed").put("detail-"+subBreed.getBreed(), subBreed);
	}
	
}
