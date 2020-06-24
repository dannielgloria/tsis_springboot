package mx.uam.tsis.homework.services;


import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;
import mx.uam.tsis.homework.business.model.Student;
/**
 * API Rest Controller
 * 
 * @author danielgloriaflorencio
 *
 */
@RestController
@Slf4j
public class StudentController {
	
	//Database to quickly use
	private Map <Integer, Student> studentRepository = new HashMap <>();
	
	@PostMapping(path = "/students", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity <?> create(@RequestBody Student newStudent) {
		log.info("I received a call to create with" + newStudent);
		studentRepository.put(newStudent.getRegistryNumber(), newStudent);
		return ResponseEntity.status(HttpStatus.CREATED).build();
	}
	
	@GetMapping(path = "/students", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity <?> retriveAll() {
		log.info("Retrieve all Students");
		return ResponseEntity.status(HttpStatus.OK).body(studentRepository.values());
	}
	
	@GetMapping(path = "/students/{registryNumber}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity <?> retrieve(@PathVariable("registryNumber") Integer registryNumber) {
		log.info("Retrieve the student with the registration number: " + registryNumber);
		Student student = studentRepository.get(registryNumber);
		if(student != null) {
			return ResponseEntity.status(HttpStatus.OK).body(student);
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
		
	}
	
	/*
	public update() {
		
	}
	
	public delete() {
	
	}
	*/
}
