package mx.uam.tsis.homework.business.model;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.Builder;
import lombok.Data;
/**
 * Student object creation
 * 
 * @author danielgloriaflorencio
 *
 */
@Builder
@Data
public class Student {
	
	@NotNull
	private Integer registryNumber;
	
	@NotBlank
    private String name;

	@NotBlank
    private String divicion;

	@NotBlank
    private String carrer;
}
