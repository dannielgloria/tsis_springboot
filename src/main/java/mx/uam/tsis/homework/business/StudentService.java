package mx.uam.tsis.homework.business;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mx.uam.tsis.homework.business.model.Student;
import mx.uam.tsis.homework.data.StudentRepository;


/**
 * Student services
 * 
 * @author danielgloriaflorencio
 *  
 */
@Service
public class StudentService {
	
	@Autowired
	private StudentRepository studentRepository;
	
	
	public Student create(Student newStudent) {
		// TODO Auto-generated method stub
		Optional <Student> studentOpt = studentRepository.findById(newStudent.getRegistryNumber());
		if(!studentOpt.isPresent()) {
			return studentRepository.save(newStudent);
		} else {
			return null;
		}
	}

	public Iterable <Student> retrieveAll() {
		// TODO Auto-generated method stub
		return studentRepository.findAll();
	}

	public Optional<Student> retrieveByRegistrationNumber(Integer registryNumber) {
		// TODO Auto-generated method stub
		return studentRepository.findById(registryNumber);
	}

	public Student update(Student student, Integer registryNumber) {
		if (studentRepository.findById(registryNumber).isPresent())
			return studentRepository.save(student);
		else
			return null;
		
	}

	public boolean delete(Integer registryNumber) {
		// TODO Auto-generated method stub
		Optional<Student>  student =studentRepository.findById(registryNumber);
		if(student.isPresent()) {
			studentRepository.deleteById(registryNumber);
			return true;
		} else {
			return false;
		}
	}

}
