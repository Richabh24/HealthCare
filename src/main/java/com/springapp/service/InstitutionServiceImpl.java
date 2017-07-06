package com.springapp.service;

import java.util.List;

import com.springapp.model.Institution;
import com.springapp.model.User;
import com.springapp.repositories.InstitutionRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;



@Service("institutionService")
@Transactional
public class InstitutionServiceImpl implements InstitutionService{

	@Autowired
	private InstitutionRepository institutionRepository;

	public Institution findById(Long id) {
		return institutionRepository.findOne(id);
	}

	public Institution findByName(String name) {
		return institutionRepository.findByName(name);
	}

	public void saveInstitution(Institution Institution) {
		institutionRepository.save(Institution);
	}

	public void updateInstitution(Institution Institution){
		saveInstitution(Institution);
	}

	public void deleteInstitutionById(Long id){
		institutionRepository.delete(id);
	}

	public void deleteAllInstitutions(){
		institutionRepository.deleteAll();
	}

	public List<Institution> findAllInstitutions(){
		return institutionRepository.findAll();
	}

	public boolean isInstitutionExist(Institution Institution) {
		return findByName(Institution.getName()) != null;
	}

	@Override
	public void saveInstitutionUser(Institution institution,List<User> users) {
		institution.setUsers(users);
		institutionRepository.save(institution);
	}

}
