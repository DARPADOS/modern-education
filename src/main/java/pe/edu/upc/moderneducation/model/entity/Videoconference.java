package pe.edu.upc.moderneducation.model.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="Videoconference")
@SequenceGenerator(name="Videoconference_videoconference_id_seq",initialValue=1,allocationSize = 1)
public class Videoconference {
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="Videoconference_videoconference_id_seq")
	@Column(name="videoconference_id",columnDefinition="NUMERIC(6)")
	private Integer id;
	
	@Column(name="name",length=100,nullable=false)
	private String name;
	
	@Column(name="description",length=250)
	private String description;
	
	@Column(name="meet_link",length=100)
	private String meetLink;
	
	@Column(name="date_start")
	@Temporal(TemporalType.TIMESTAMP)
	private Date dateStart;
	
	@Column(name="date_end")
	@Temporal(TemporalType.TIMESTAMP)	
	private Date dateEnd;
	
	@ManyToOne
	@JoinColumn(name="teacher_id",nullable=false)
	private Teacher teacher;
	
	@OneToMany(mappedBy="videoconference",fetch=FetchType.LAZY)
	private List<Assistance> assistances;
	
	public Videoconference() {
		super();
		// TODO Auto-generated constructor stub
		assistances=new ArrayList<Assistance>();
	}

	public Videoconference(Integer id, String name, String description, String meetLink, Date dateStart, Date dateEnd,
			Teacher teacher, List<Assistance> assistances) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		this.meetLink = meetLink;
		this.dateStart = dateStart;
		this.dateEnd = dateEnd;
		this.teacher = teacher;
		this.assistances = assistances;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getMeetLink() {
		return meetLink;
	}

	public void setMeetLink(String meetLink) {
		this.meetLink = meetLink;
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

	public Teacher getTeacher() {
		return teacher;
	}

	public void setTeacher(Teacher teacher) {
		this.teacher = teacher;
	}

	public List<Assistance> getAssistances() {
		return assistances;
	}

	public void setAssistances(List<Assistance> assistances) {
		this.assistances = assistances;
	}
	
	
	
}
