package mx.uam.tsis.homework.services;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;
import mx.uam.tsis.homework.business.StudentService;
import mx.uam.tsis.homework.business.model.Student;
/**
 * API Rest Controller
 * 
 * @author danielgloriaflorencio
 *
 */
@RestController
@RequestMapping("/v1")
@Slf4j
public class StudentController {
	
	@Autowired
	private StudentService studentService;
	
	/*
	 * Create the Student object
	 */
	@PostMapping(path = "/students", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity <?> create(@RequestBody @Valid Student newStudent) {
		log.info("I received a call to create with" + newStudent);
		Student student = studentService.create(newStudent);
		if (student != null) {
			return ResponseEntity.status(HttpStatus.CREATED).body(student);
		} else {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Cannot create student object");
		}
		
	}
	
	/*
	 * Retrieve all Students
	 */
	@GetMapping(path = "/students", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity <?> retriveAll() {
		log.info("Retrieve all Students");
		List <Student> result = studentService.retrieveAll();
		return ResponseEntity.status(HttpStatus.OK).body(result);
	}
	
	/*
	 * Retrieve the student with a specific registration number
	 */
	@GetMapping(path = "/students/{registryNumber}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity <?> retrieve(@PathVariable("registryNumber") Integer registryNumber) {
		log.info("Retrieve the student with the registration number: " + registryNumber);
		Student student = studentService.retrieveByRegistrationNumber(registryNumber);
		if(student != null) {
			return ResponseEntity.status(HttpStatus.OK).body(student);
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
		
	}
	
	/*
	 * Update student information with a specific registration number
	 */
	@PatchMapping(path = "/students/{registryNumber}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity <?> patch(@RequestBody Student student,@PathVariable("registryNumber") Integer registryNumber) {
		log.info("Update the student with the registration number: " + registryNumber);
		Student studentUpdate = studentService.update(student,registryNumber);
		if(studentUpdate != null) {
			return ResponseEntity.status(HttpStatus.OK).body(student);
		} else {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("The student with the registration number: " + registryNumber +" was not found");
		}
	}

	//To use the method @DeleteMapping it's necessary to enter the registration 
	//number of the address http://localhost:8080/students/
	@DeleteMapping(path = "/students/{registryNumber}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity <?> delete(@PathVariable("registryNumber") Integer registryNumber) {
		log.info("Delete the student with the registration number: " + registryNumber);
		boolean statusStudent = studentService.delete(registryNumber);
		if(statusStudent) {
			return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("The student with the registration number: " + registryNumber +" was not found");
		}
	
	}
	
}
