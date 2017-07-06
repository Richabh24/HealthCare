package com.springapp.model;

import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name="Examination")
public class Examination implements Serializable{

	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private Long id;

	@NotEmpty
	@Column(name="NAME", nullable=false)
	private String name;
	@NotEmpty
	@Column(name="ExamDate", nullable=false)
	private String examDate;

	@NotEmpty
	@Column(name="Description", nullable=false)
	private String description;
	@NotEmpty
	@Column(name="Weight", nullable=false)
	private Double weight;
	@NotEmpty
	@Column(name="Height", nullable=false)
	private Double height;
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
	public String getExamDate() {
		return examDate;
	}
	public void setExamDate(String examDate) {
		this.examDate = examDate;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Double getWeight() {
		return weight;
	}
	public void setWeight(Double weight) {
		this.weight = weight;
	}
	public Double getHeight() {
		return height;
	}
	public void setHeight(Double height) {
		this.height = height;
	}
	@Override
	public String toString() {
		return "Examination [id=" + id + ", name=" + name + ", examDate=" + examDate + ", description=" + description
				+ ", weight=" + weight + ", height=" + height + "]";
	}

	
	
	



}
