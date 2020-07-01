package mx.uam.tsis.homework.business.model;

import javax.persistence.Entity;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
/**
 * Student object creation
 * 
 * @author danielgloriaflorencio
 *
 */
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
@Entity // Indica que hay que persistir en BD
public class Student {
	
	@NotNull
	@ApiModelProperty(notes = "Student registry number", required = true)
	private Integer registryNumber;
	
	@NotBlank
	@ApiModelProperty(notes = "Student's name", required = true)
    private String name;

	@NotBlank
	@ApiModelProperty(notes = "Student Division", required = true)
    private String division;

	@NotBlank
	@ApiModelProperty(notes = "student career", required = true)
    private String career;
}
