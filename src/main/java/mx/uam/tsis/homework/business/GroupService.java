package mx.uam.tsis.homework.business;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;
import mx.uam.tsis.homework.business.model.Group;
import mx.uam.tsis.homework.business.model.Student;
import mx.uam.tsis.homework.data.GroupRepository;

@Service
@Slf4j
public class GroupService {
	@Autowired
	private GroupRepository groupRepository;
	
	@Autowired
	private StudentService studentService;
	
	public Group create(Group nuevo) {
		
		// Validar reglas de negocio previas a la creación
		
		return groupRepository.save(nuevo);
	}
	
	public Iterable <Group> retrieveAll() {
		return groupRepository.findAll();
	}
	
	/**
	 * 
	 * Método que permite agregar un alumno a un grupo
	 * 
	 * @param groupId el id del grupo
	 * @param registryNumber
	 * @return true si se agregó correctamente, false si no
	 */
	public boolean addStudentToGroup(Integer groupId, Integer registryNumber) {
		
		log.info("Add student with regisrty number "+registryNumber+" to "+groupId);
		
		// 1.- Recuperamos primero al alumno
		Student student = studentService.findByRegistryNumber(registryNumber);
		
		// 2.- Recuperamos el grupo
		Optional <Group> groupOpt = groupRepository.findById(groupId);
		
		// 3.- Verificamos que el alumno y el grupo existan
		if(!groupOpt.isPresent() || student == null) {
			
			log.info("No se encontró alumno o grupo");
			
			return false;
		}
		
		// 4.- Agrego el alumno al grupo
		Group group = groupOpt.get();
		group.addStudent(student);
		
		// 5.- Persistir el cambio
		groupRepository.save(group);
		
		return true;
	}
	
	

}
