package mx.uam.tsis.homework.data;

import org.springframework.data.repository.CrudRepository;

import mx.uam.tsis.homework.business.model.Student;

/**
 * 
 * @author danielgloriaflorencio
 *
 */

public interface StudentRepository extends CrudRepository <Student, Integer> {
	
	
	
	/*
	// "Database"
	private Map <Integer, Student> studentRepository = new HashMap <>();
	
	//Method Create
	public Student save(Student newStudent) {
		studentRepository.put(newStudent.getRegistryNumber(), newStudent);
		return newStudent;
	}
	
	//Method Retrieve
	public List <Student> find() {
		return new ArrayList <> (studentRepository.values());
	}
	
	//Method Retrieve by
	public Student findByRegistryNumber(Integer registryNumber) {
		return studentRepository.get(registryNumber);
	}
	
	//Method Update
	public Student update(Student student, Integer registryNumber) {
		
		if (studentRepository.containsKey(registryNumber)) {
			studentRepository.replace(registryNumber, student);
			return student;
		} else {
			return null;
		}
		
	}
	
	public boolean delete(Integer registryNumber) {
		if (studentRepository.containsKey(registryNumber)) {
			studentRepository.remove(registryNumber);
			return true;
		} else {
			return false;
		}
	}*/
}
