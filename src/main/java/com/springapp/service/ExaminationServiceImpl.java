package com.springapp.service;

import java.util.List;

import com.springapp.model.Examination;
import com.springapp.repositories.ExaminationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;



@Service("examinationService")
@Transactional
public class ExaminationServiceImpl implements ExaminationService{

	@Autowired
	private ExaminationRepository examinationRepository;

	public Examination findById(Long id) {
		return examinationRepository.findOne(id);
	}

	public Examination findByName(String name) {
		return examinationRepository.findByName(name);
	}

	public void saveExamination(Examination Examination) {
		examinationRepository.save(Examination);
	}

	public void updateExamination(Examination Examination){
		saveExamination(Examination);
	}

	public void deleteExaminationById(Long id){
		examinationRepository.delete(id);
	}

	public void deleteAllExaminations(){
		examinationRepository.deleteAll();
	}

	public List<Examination> findAllExaminations(){
		return examinationRepository.findAll();
	}

	public boolean isExaminationExist(Examination Examination) {
		return findByName(Examination.getName()) != null;
	}

}
