package pe.edu.upc.moderneducation.model.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.MapsId;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


@Entity
@Table(name="Student")
public class Student {
	
	@Id
	//@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="genStudent")
	//@Column(name="student_id",columnDefinition = "NUMERIC(6)")
	private Integer id;
	
	@Column(name="premium")
	private Boolean premium;
	
	@Column(name="pay_day")
	private Integer payDay;
	
	@Column(name="date_expiration")
	@Temporal(TemporalType.TIMESTAMP)
	private Date dateExpiration;
	
	@OneToOne
	@MapsId
	@JoinColumn(name="id")
	private User user;
	
	@OneToMany(mappedBy="student", fetch=FetchType.LAZY)
	private List<Assistance> assistances;
	
	@OneToMany(mappedBy="student")
	private List<DetailCourseStudent> detailcourseStudents;
	
	@OneToMany(mappedBy="student")
	private List<Payment> payments;



	public Student() {
		super();
		// TODO Auto-generated constructor stub
		assistances=new ArrayList<Assistance>();
		detailcourseStudents=new ArrayList<DetailCourseStudent>();
		payments=new ArrayList<Payment>();
	}

	public Student(Integer id, Boolean premium, Integer payDay, Date dateExpiration, User user,
			List<Assistance> assistances, List<DetailCourseStudent> detailcourseStudents, List<Payment> payments) {
		super();
		this.id = id;
		this.premium = premium;
		this.payDay = payDay;
		this.dateExpiration = dateExpiration;
		this.user = user;
		this.assistances = assistances;
		this.detailcourseStudents = detailcourseStudents;
		this.payments = payments;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Boolean getPremium() {
		return premium;
	}

	public void setPremium(Boolean premium) {
		this.premium = premium;
	}

	public Integer getPayDay() {
		return payDay;
	}

	public void setPayDay(Integer payDay) {
		this.payDay = payDay;
	}

	public Date getDateExpiration() {
		return dateExpiration;
	}

	public void setDateExpiration(Date dateExpiration) {
		this.dateExpiration = dateExpiration;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public List<Assistance> getAssistances() {
		return assistances;
	}

	public void setAssistances(List<Assistance> assistances) {
		this.assistances = assistances;
	}

	public List<DetailCourseStudent> getDetailcourseStudents() {
		return detailcourseStudents;
	}

	public void setDetailcourseStudents(List<DetailCourseStudent> detailcourseStudents) {
		this.detailcourseStudents = detailcourseStudents;
	}

	public List<Payment> getPayments() {
		return payments;
	}

	public void setPayments(List<Payment> payments) {
		this.payments = payments;
	}
	
	
	
	
}
