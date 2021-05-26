package pe.edu.upc.moderneducation.model.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="assistance")
@IdClass(value=AssistanceId.class)
public class Assistance {
	
	@Id
	@ManyToOne
	@JoinColumn(name="videconference_id",nullable=false)
	private Videoconference videoconference;
	
	@Id
	@ManyToOne
	@JoinColumn(name="student_id",nullable=false)
	private Student student;


	public Assistance(Videoconference videoconference, Student student) {
		super();
		this.videoconference = videoconference;
		this.student = student;
	}

	public Assistance() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Videoconference getVideoconference() {
		return videoconference;
	}

	public void setVideoconference(Videoconference videoconference) {
		this.videoconference = videoconference;
	}

	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}
	
	
	
}
