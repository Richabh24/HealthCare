package com.springapp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.springapp.model.Examination;
import com.springapp.model.User;

@Repository
public interface ExaminationRepository extends JpaRepository<Examination, Long> {

	Examination findByName(String name);

}
