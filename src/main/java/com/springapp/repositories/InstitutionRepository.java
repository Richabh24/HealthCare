package com.springapp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.springapp.model.Institution;
import com.springapp.model.User;

@Repository
public interface InstitutionRepository extends JpaRepository<Institution, Long> {

	Institution findByName(String name);
	
}
