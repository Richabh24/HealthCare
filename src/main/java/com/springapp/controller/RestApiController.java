package com.springapp.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.springapp.model.Examination;
import com.springapp.model.Institution;
import com.springapp.model.User;
import com.springapp.service.ExaminationService;
import com.springapp.service.InstitutionService;
import com.springapp.service.UserService;
import com.springapp.util.CustomErrorType;

@RestController
@RequestMapping("/api")
public class RestApiController {

	public static final Logger logger = LoggerFactory.getLogger(RestApiController.class);

	@Autowired
	UserService userService; //Service which will do all data retrieval/manipulation work
	@Autowired
	ExaminationService examinationService; 
	@Autowired
	InstitutionService institutionService; 
	// -------------------Retrieve All Users---------------------------------------------

	@RequestMapping(value = "/user/", method = RequestMethod.GET)
	public ResponseEntity<List<User>> listAllUsers() {
		List<User> users = userService.findAllUsers();
		
		if (users.isEmpty()) {
			return new ResponseEntity(HttpStatus.NO_CONTENT);
			// You many decide to return HttpStatus.NOT_FOUND
		}
		return new ResponseEntity<List<User>>(users, HttpStatus.OK);
	}

	// -------------------Retrieve Single User------------------------------------------

	@RequestMapping(value = "/user/{id}", method = RequestMethod.GET)
	public ResponseEntity<?> getUser(@PathVariable("id") long id) {
		logger.info("Fetching User with id {}", id);
		User user = userService.findById(id);
		if (user == null) {
			logger.error("User with id {} not found.", id);
			return new ResponseEntity(new CustomErrorType("User with id " + id 
					+ " not found"), HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<User>(user, HttpStatus.OK);
	}

	// -------------------Create a User-------------------------------------------

	@RequestMapping(value = "/user/", method = RequestMethod.POST)
	public ResponseEntity<?> createUser(@RequestBody User user, UriComponentsBuilder ucBuilder) {
		logger.info("Creating User : {}", user);

		if (userService.isUserExist(user)) {
			logger.error("Unable to create. A User with name {} already exist", user.getName());
			return new ResponseEntity(new CustomErrorType("Unable to create. A User with name " + 
			user.getName() + " already exist."),HttpStatus.CONFLICT);
		}
		userService.saveUser(user);

		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(ucBuilder.path("/api/user/{id}").buildAndExpand(user.getId()).toUri());
		return new ResponseEntity<String>(headers, HttpStatus.CREATED);
	}

	// ------------------- Update a User ------------------------------------------------

	@RequestMapping(value = "/user/{id}", method = RequestMethod.PUT)
	public ResponseEntity<?> updateUser(@PathVariable("id") long id, @RequestBody User user) {
		logger.info("Updating User with id {}", id);

		User currentUser = userService.findById(id);

		if (currentUser == null) {
			logger.error("Unable to update. User with id {} not found.", id);
			return new ResponseEntity(new CustomErrorType("Unable to upate. User with id " + id + " not found."),
					HttpStatus.NOT_FOUND);
		}

		currentUser.setName(user.getName());
		currentUser.setGender(user.getGender());
		currentUser.setDob(user.getDob());

		userService.updateUser(currentUser);
		return new ResponseEntity<User>(currentUser, HttpStatus.OK);
	}

	// ------------------- Delete a User-----------------------------------------

	@RequestMapping(value = "/user/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<?> deleteUser(@PathVariable("id") long id) {
		logger.info("Fetching & Deleting User with id {}", id);

		User user = userService.findById(id);
		if (user == null) {
			logger.error("Unable to delete. User with id {} not found.", id);
			return new ResponseEntity(new CustomErrorType("Unable to delete. User with id " + id + " not found."),
					HttpStatus.NOT_FOUND);
		}
		userService.deleteUserById(id);
		return new ResponseEntity<User>(HttpStatus.NO_CONTENT);
	}

	// ------------------- Delete All Users-----------------------------

	@RequestMapping(value = "/user/", method = RequestMethod.DELETE)
	public ResponseEntity<User> deleteAllUsers() {
		logger.info("Deleting All Users");

		userService.deleteAllUsers();
		return new ResponseEntity<User>(HttpStatus.NO_CONTENT);
	}

	//--------------------list all examination-----------------------
	@RequestMapping(value = "/examination/", method = RequestMethod.GET)
	public ResponseEntity<List<Examination>> listAllExaminations() {
		List<Examination> examinations = examinationService.findAllExaminations();
		if (examinations.isEmpty()) {
			return new ResponseEntity(HttpStatus.NO_CONTENT);
			// You many decide to return HttpStatus.NOT_FOUND
		}
		return new ResponseEntity<List<Examination>>(examinations, HttpStatus.OK);
	}

	// -------------------Retrieve Single examination------------------------------------------

	@RequestMapping(value = "/examination/{id}", method = RequestMethod.GET)
	public ResponseEntity<?> getExam(@PathVariable("id") long id) {
		logger.info("Fetching User with id {}", id);
		Examination examination = examinationService.findById(id);
		if (examination == null) {
			logger.error("examination with id {} not found.", id);
			return new ResponseEntity(new CustomErrorType("User with id " + id 
					+ " not found"), HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Examination>(examination, HttpStatus.OK);
	}

	// -------------------Create a examination-------------------------------------------

	@RequestMapping(value = "/examination/", method = RequestMethod.POST)
	public ResponseEntity<?> createExam(@RequestBody Examination examination, UriComponentsBuilder ucBuilder) {
		logger.info("Creating Examination : {}", examination);

		/*if (examinationService.isExaminationExist(examination)) {
			logger.error("Unable to create. A examination with name {} already exist", examination.getName());
			return new ResponseEntity(new CustomErrorType("Unable to create. A User with name " + 
			user.getName() + " already exist."),HttpStatus.CONFLICT);
		}*/
		examinationService.saveExamination(examination);

		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(ucBuilder.path("/api/user/{id}").buildAndExpand(examination.getId()).toUri());
		return new ResponseEntity<String>(headers, HttpStatus.CREATED);
	}

	// ------------------- Update a examination ------------------------------------------------

	@RequestMapping(value = "/examination/{id}", method = RequestMethod.PUT)
	public ResponseEntity<?> updateExam(@PathVariable("id") long id, @RequestBody Examination examination) {
		logger.info("Updating User with id {}", id);

		Examination currentexamination = examinationService.findById(id);

		if (currentexamination == null) {
			logger.error("Unable to update. examination with id {} not found.", id);
			return new ResponseEntity(new CustomErrorType("Unable to upate. User with id " + id + " not found."),
					HttpStatus.NOT_FOUND);
		}

		currentexamination.setName(examination.getName());
		

		examinationService.updateExamination(currentexamination);
		return new ResponseEntity<Examination>(currentexamination, HttpStatus.OK);
	}

	// ------------------- Delete a examination-----------------------------------------

	@RequestMapping(value = "/examination/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<?> deleteExam(@PathVariable("id") long id) {
		logger.info("Fetching & Deleting examination with id {}", id);

		Examination examination = examinationService.findById(id);
		if (examination == null) {
			logger.error("Unable to delete. examination with id {} not found.", id);
			return new ResponseEntity(new CustomErrorType("Unable to delete. examination with id " + id + " not found."),
					HttpStatus.NOT_FOUND);
		}
		examinationService.deleteExaminationById(id);
		return new ResponseEntity<User>(HttpStatus.NO_CONTENT);
	}

	// ------------------- Delete All examination-----------------------------

	@RequestMapping(value = "/examination/", method = RequestMethod.DELETE)
	public ResponseEntity<User> deleteAllExaminations() {
		logger.info("Deleting All Examinations");

		examinationService.deleteAllExaminations();
		return new ResponseEntity<User>(HttpStatus.NO_CONTENT);
	}


	//--------------------list all Institution-----------------------
	@RequestMapping(value = "/institution/", method = RequestMethod.GET)

	public ResponseEntity<List<Institution>> listAllInstitutions() {
		List<Institution> Institutions = institutionService.findAllInstitutions();
		if (Institutions.isEmpty()) {
			return new ResponseEntity(HttpStatus.NO_CONTENT);
			// You many decide to return HttpStatus.NOT_FOUND
		}
		return new ResponseEntity<List<Institution>>(Institutions, HttpStatus.OK);
	}

	// -------------------Retrieve Single Institution------------------------------------------

	@RequestMapping(value = "/institution/{id}", method = RequestMethod.GET)
	public ResponseEntity<?> getInstitution(@PathVariable("id") long id) {
		logger.info("Fetching User with id {}", id);
		Institution Institution = institutionService.findById(id);
		if (Institution == null) {
			logger.error("Institution with id {} not found.", id);
			return new ResponseEntity(new CustomErrorType("User with id " + id 
					+ " not found"), HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Institution>(Institution, HttpStatus.OK);
	}

	// -------------------Create a Institution-------------------------------------------

	@RequestMapping(value = "/institution/", method = RequestMethod.POST)
	public ResponseEntity<?> createExam(@RequestBody Institution institution, UriComponentsBuilder ucBuilder) {
		logger.info("Creating Institution : {}", institution);

		/*if (institutionService.isInstitutionExist(Institution)) {
			logger.error("Unable to create. A Institution with name {} already exist", Institution.getName());
			return new ResponseEntity(new CustomErrorType("Unable to create. A User with name " + 
			user.getName() + " already exist."),HttpStatus.CONFLICT);
		}*/
		institutionService.saveInstitution(institution);

		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(ucBuilder.path("/api/user/{id}").buildAndExpand(institution.getId()).toUri());
		return new ResponseEntity<String>(headers, HttpStatus.CREATED);
	}

	// ------------------- Update a Institution ------------------------------------------------

	@RequestMapping(value = "/Institution/{id}", method = RequestMethod.PUT)
	public ResponseEntity<?> updateExam(@PathVariable("id") long id, @RequestBody Institution institution) {
		logger.info("Updating User with id {}", id);

		Institution currentInstitution = institutionService.findById(id);

		if (currentInstitution == null) {
			logger.error("Unable to update. Institution with id {} not found.", id);
			return new ResponseEntity(new CustomErrorType("Unable to upate. User with id " + id + " not found."),
					HttpStatus.NOT_FOUND);
		}

		currentInstitution.setName(institution.getName());
		

		institutionService.updateInstitution(currentInstitution);
		return new ResponseEntity<Institution>(currentInstitution, HttpStatus.OK);
	}

	// ------------------- Delete a Institution-----------------------------------------

	@RequestMapping(value = "/institution/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<?> deleteInstitution(@PathVariable("id") long id) {
		logger.info("Fetching & Deleting Institution with id {}", id);

		Institution Institution = institutionService.findById(id);
		if (Institution == null) {
			logger.error("Unable to delete. Institution with id {} not found.", id);
			return new ResponseEntity(new CustomErrorType("Unable to delete. Institution with id " + id + " not found."),
					HttpStatus.NOT_FOUND);
		}
		institutionService.deleteInstitutionById(id);
		return new ResponseEntity<User>(HttpStatus.NO_CONTENT);
	}

	// ------------------- Delete All Institution-----------------------------

	@RequestMapping(value = "/institution/", method = RequestMethod.DELETE)
	public ResponseEntity<User> deleteAllInstitutions() {
		logger.info("Deleting All Institutions");

		institutionService.deleteAllInstitutions();
		return new ResponseEntity<User>(HttpStatus.NO_CONTENT);
	}
}