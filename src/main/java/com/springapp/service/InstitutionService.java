package com.springapp.service;


import java.util.List;

import com.springapp.model.Institution;
import com.springapp.model.User;

public interface InstitutionService {
	
Institution findById(Long id);

Institution findByName(String name);

	void saveInstitution(Institution Institution);

	void updateInstitution(Institution Institution);

	void deleteInstitutionById(Long id);

	void deleteAllInstitutions();

	List<Institution> findAllInstitutions();

	boolean isInstitutionExist(Institution Institution);
	void saveInstitutionUser(Institution Institution,List<User>  users);

}