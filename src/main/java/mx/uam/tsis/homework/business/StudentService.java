package mx.uam.tsis.homework.business;

import java.util.List;

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
		Student student = studentRepository.findByRegistryNumber(newStudent.getRegistryNumber());
		if(student == null) {
			return studentRepository.save(newStudent);
		} else {
			return null;
		}
	}

	public List<Student> retrieveAll() {
		// TODO Auto-generated method stub
		return studentRepository.find();
	}

	public Student retrieveByRegistrationNumber(Integer registryNumber) {
		// TODO Auto-generated method stub
		return studentRepository.findByRegistryNumber(registryNumber);
	}

	public Student update(Student student, Integer registryNumber) {
		// TODO Auto-generated method stub
		return studentRepository.update(student, registryNumber);
	}

	public boolean delete(Integer registryNumber) {
		// TODO Auto-generated method stub
		return studentRepository.delete(registryNumber);
	}

}
