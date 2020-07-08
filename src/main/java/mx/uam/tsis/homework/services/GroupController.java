package mx.uam.tsis.homework.services;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import mx.uam.tsis.homework.business.GroupService;
import mx.uam.tsis.homework.business.model.Group;

@RestController
@Slf4j
public class GroupController {
	
	@Autowired
	private GroupService groupService;
	
	
	@ApiOperation(
			value = "Create gropu",
			notes = "Permite crear un nuevo grupo"
			) // Documentacion del api
	@PostMapping(path = "/groups", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity <?> create(@RequestBody @Valid Group newGroup) { // Validaciones
				
		log.info("Recibí llamada a create con "+newGroup); // Logging
		
		Group group = groupService.create(newGroup);
		
		if(group != null) {
			return ResponseEntity.status(HttpStatus.CREATED).body(group);			
		} else {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Cannot create Student");
		}
	

	}
	

	@ApiOperation(
			value = "Retrive all groups",
			notes = "Permite recuperar todos los grupos existentes"
			) 
	@GetMapping(path = "/groups", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity <?> retrieveAll() {
		
		Iterable <Group> result = groupService.retrieveAll();
		
		return ResponseEntity.status(HttpStatus.OK).body(result); 
		
	}
	
	/**
	 * 
	 * POST /group/{id}/students?registryNumber=1234
	 * 
	 * PROBAR ESTE!!!
	 * 
	 * @return
	 */
	@PostMapping(path = "/groups/{id}/students", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity <?> addStudentToGroup(
			@PathVariable("id") Integer id,
			@RequestParam("registryNumber") Integer matricula) {
		
		boolean result = groupService.addStudentToGroup(id, matricula);
		
		if(result) {
			return ResponseEntity.status(HttpStatus.OK).build(); 
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build(); 
		}
		
	
	}
}
