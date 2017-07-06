package com.springapp.service;


import java.util.List;

import com.springapp.model.Examination;

public interface ExaminationService {
	
	Examination findById(Long id);

	Examination findByName(String name);

	void saveExamination(Examination examination);

	void updateExamination(Examination examination);

	void deleteExaminationById(Long id);

	void deleteAllExaminations();

	List<Examination> findAllExaminations();

	boolean isExaminationExist(Examination examination);
}