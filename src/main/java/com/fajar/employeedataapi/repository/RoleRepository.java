package com.fajar.employeedataapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fajar.employeedataapi.entities.Role;
@Repository
public interface RoleRepository extends JpaRepository<Role, Integer> {

	

}
