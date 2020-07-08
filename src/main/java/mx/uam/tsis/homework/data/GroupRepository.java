package mx.uam.tsis.homework.data;

import org.springframework.data.repository.CrudRepository;

import mx.uam.tsis.homework.business.model.Group;

public interface GroupRepository extends CrudRepository <Group, Integer>{ 

}
