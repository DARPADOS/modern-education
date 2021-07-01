package pe.edu.upc.moderneducation.model.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.MapsId;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name="Teacher")
public class Teacher {
	
	@Id
	//@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="genTeacher")
	//@Column(name="teacher_id",columnDefinition="NUMERIC(6)")
	private Integer id;
	
	@Column(name="occupation",length=100, nullable=false)
	private String occupation;
	
	@Column(name="career", length=100, nullable=false)
	private String career;
	
	@Column(name="description",length=1024,nullable=false)
	private String description;

	@OneToOne(cascade = CascadeType.ALL)
	@MapsId("id")
	@JoinColumn(name="id")
	private User user;
	
	@OneToMany(mappedBy="teacher",fetch=FetchType.LAZY)
	private List<Course> courses;
	
	@OneToMany(mappedBy="teacher",fetch=FetchType.LAZY)
	private List<Videoconference> videoconferences;

	@Transient
	private float averageQualification;

	public Teacher() {
		super();
		// TODO Auto-generated constructor stub
		courses=new ArrayList<Course>();
		videoconferences=new ArrayList<Videoconference>();
	}

	public Teacher(Integer id, String occupation, String career, String description, User user, List<Course> courses,
			List<Videoconference> videoconferences) {
		super();
		this.id = id;
		this.occupation = occupation;
		this.career = career;
		this.description = description;
		this.user = user;
		this.courses = courses;
		this.videoconferences = videoconferences;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getOccupation() {
		return occupation;
	}

	public void setOccupation(String occupation) {
		this.occupation = occupation;
	}

	public String getCareer() {
		return career;
	}

	public void setCareer(String career) {
		this.career = career;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public List<Course> getCourses() {
		return courses;
	}

	public void setCourses(List<Course> courses) {
		this.courses = courses;
	}

	public List<Videoconference> getVideoconferences() {
		return videoconferences;
	}

	public void setVideoconferences(List<Videoconference> videoconferences) {
		this.videoconferences = videoconferences;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Teacher other = (Teacher) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	
}
