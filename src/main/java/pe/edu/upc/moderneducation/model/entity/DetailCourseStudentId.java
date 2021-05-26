package pe.edu.upc.moderneducation.model.entity;

import java.io.Serializable;
import java.util.Objects;

public class DetailCourseStudentId implements Serializable {

	private static final long serialVersionUID = 1L;

	private Integer course;
	private Integer student;
	
	// Constructor
	public DetailCourseStudentId(Integer course, Integer student) {
		super();
		this.course = course;
		this.student = student;
	}

	public DetailCourseStudentId() {
		super();
	}

	// Getters and Setters
	public Integer getCourse() {
		return course;
	}

	public void setCourse(Integer course) {
		this.course = course;
	}

	public Integer getstudent() {
		return student;
	}

	public void setstudent(Integer student) {
		this.student = student;
	}
	

	@Override
	public int hashCode() {
		return Objects.hash(course, student);
	}
	
	
	@Override
	public boolean equals(Object obj) {
		if(this == obj)
			return true;
		if(obj == null || getClass() != obj.getClass())
			return false;
		
		DetailCourseStudentId detailCourseStudentId = (DetailCourseStudentId)obj;
		
		if(this.student != detailCourseStudentId.student)
			return false;
		if(this.course != detailCourseStudentId.course)
			return false;
		return true;
	}	
	
	
	
	
}
