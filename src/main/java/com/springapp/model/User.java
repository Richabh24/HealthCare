package com.springapp.model;

import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Entity
@Table(name="Patient")
public class User implements Serializable{

	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private Long id;

	@NotEmpty
	@Column(name="NAME", nullable=false)
	private String name;
	@Column(name="DOB", nullable=false)
	private String dob;

	@Column(name="Gender", nullable=false)
	private String gender;
	 @OneToMany
	  @JoinTable
	  (
	      name="PatientExamination",
	      joinColumns={ @JoinColumn(name="patientId", referencedColumnName="id") },
	      inverseJoinColumns={ @JoinColumn(name="ExaminationId", referencedColumnName="id", unique=true) }
	  )	  private List<Examination> examinations;
   

	
	public List<Examination> getExaminations() {
		return examinations;
	}

	public void setExaminations(List<Examination> examinations) {
		this.examinations = examinations;
	}

	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDob() {
		return dob;
	}

	public void setDob(String dob) {
		this.dob = dob;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", dob=" + dob + ", gender=" + gender + "]";
	}
	public User() {
	}
	
	

	

	

	


}
