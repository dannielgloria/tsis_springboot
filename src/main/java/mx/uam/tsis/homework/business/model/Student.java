package mx.uam.tsis.homework.business.model;

import lombok.Data;
/**
 * Student object creation
 * 
 * @author danielgloriaflorencio
 *
 */

@Data
public class Student {
	private Integer registryNumber;

    private String name;
    
    private String divicion;

    private String carrer;
}
