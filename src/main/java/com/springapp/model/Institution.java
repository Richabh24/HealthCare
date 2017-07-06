package com.springapp.model;

import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
import java.util.Set;

@Entity
@Table(name="Institution")
public class Institution implements Serializable{

	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private Long id;

	@NotEmpty
	@Column(name="NAME", nullable=false)
	private String name;

	@NotEmpty
	@Column(name="DESCRIPTION", nullable=false)
	private String description;

	public Institution() {
	}

	 @OneToMany
	  @JoinTable
	  (
	      name="InstitutionPatient",
	      joinColumns={ @JoinColumn(name="institution", referencedColumnName="id") },
	      inverseJoinColumns={ @JoinColumn(name="patient_id", referencedColumnName="id", unique=true) }
	  )	  private List<User> users;
    

	public List<User> getUsers() {
		return users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}




	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name 
				+ ", description=" + description + "]";
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




	public String getDescription() {
		return description;
	}




	public void setDescription(String description) {
		this.description = description;
	}


}
