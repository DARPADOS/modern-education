package pe.edu.upc.moderneducation.model.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "Detail_Course_Student")
@IdClass(value = DetailCourseStudentId.class)
public class DetailCourseStudent {
	
	@Id
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "course_id", nullable = false)
	private Course course;
	
	@Id
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "student_id", nullable = false)
	private Student student;
	
	@Column(name = "date_start", nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date dateStart;
	
	@Column(name = "date_end")
	@Temporal(TemporalType.TIMESTAMP)
	private Date dateEnd;
	
	@Column(name = "qualification", columnDefinition = "NUMERIC(6)")
	private Integer qualification;
	
	@Column(name = "opinion", length = 250)
	private String opinion;

	public DetailCourseStudent(Course course, Student student, Date dateStart, Date dateEnd, Integer qualification,
			String opinion) {
		super();
		this.course = course;
		this.student = student;
		this.dateStart = dateStart;
		this.dateEnd = dateEnd;
		this.qualification = qualification;
		this.opinion = opinion;
	}

	public DetailCourseStudent() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Course getCourse() {
		return course;
	}

	public void setCourse(Course course) {
		this.course = course;
	}

	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}

	public Date getDateStart() {
		return dateStart;
	}

	public void setDateStart(Date dateStart) {
		this.dateStart = dateStart;
	}

	public Date getDateEnd() {
		return dateEnd;
	}

	public void setDateEnd(Date dateEnd) {
		this.dateEnd = dateEnd;
	}

	public Integer getQualification() {
		return qualification;
	}

	public void setQualification(Integer qualification) {
		this.qualification = qualification;
	}

	public String getOpinion() {
		return opinion;
	}

	public void setOpinion(String opinion) {
		this.opinion = opinion;
	} 
	
	
	
}
