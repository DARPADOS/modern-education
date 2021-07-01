package pe.edu.upc.moderneducation.model.entity;

import java.io.Serializable;
import java.util.Objects;

public class AssistanceId implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private Integer videoconference;
	private Integer student;
	
	
	public AssistanceId() {
		super();
	}

	public AssistanceId(Integer videoconference, Integer student) {
		super();
		this.videoconference = videoconference;
		this.student = student;
	}

	public Integer getVideoconference() {
		return videoconference;
	}

	public void setVideoconference(Integer videoconference) {
		this.videoconference = videoconference;
	}

	public Integer getStudent() {
		return student;
	}

	public void setStudent(Integer student) {
		this.student = student;
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(videoconference, student);
	}
	
	@Override
	public boolean equals(Object obj) {
		if(this==obj)
			return true;
		if(this==null || getClass()!=obj.getClass())
			return false;
		
		AssistanceId assistanceId=(AssistanceId)obj;
		
		if(this.videoconference!=assistanceId.videoconference)
			return false;
		if (this.student!=assistanceId.student)
			return false;
		return true;			
	}
	
}
