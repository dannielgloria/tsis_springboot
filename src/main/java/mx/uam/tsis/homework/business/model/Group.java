package mx.uam.tsis.homework.business.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotBlank;

import lombok.Builder;

public class Group {
	@Id
	@GeneratedValue
	private Integer id;
	
	@NotBlank
	private String code;
	
	@NotBlank
	private String subjectName;
	
	@Builder.Default
	@OneToMany(targetEntity=Student.class, fetch= FetchType.LAZY,cascade = CascadeType.MERGE)
	private List<Student> alumnos =new ArrayList <> ();
	
	public boolean addStudent(Student student)
	{
		return students.add(student);
	}

}
