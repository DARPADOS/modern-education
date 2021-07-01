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
import javax.persistence.Index;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

@Entity
@Table(name="Videoconference", indexes= {@Index(columnList = "name", name="videoconference_index_name")})
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

	@Column(name="date")
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(iso = ISO.DATE)
	private Date date;
	
	@Column(name="hora_start")
	@Temporal(TemporalType.TIME)
	@DateTimeFormat(pattern = "HH:mm:ss")
	private Date hourStart;

	@Column(name="hora_end")
	@Temporal(TemporalType.TIME)
	@DateTimeFormat(pattern = "HH:mm:ss")
	private Date hourEnd;
	
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

	public Videoconference(Integer id, String name, String description, String meetLink, Date date, Date hourEnd,Date hourStart,
			Teacher teacher, List<Assistance> assistances) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		this.meetLink = meetLink;
		this.date = date;
		this.hourEnd = hourEnd;
		this.hourStart=hourStart;
		//this.date = date;
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

	/*public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}*/
	
	public String getMeetLink() {
		return meetLink;
	}


	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Date getHourStart() {
		return hourStart;
	}

	public void setHourStart(Date horaStart) {
		this.hourStart = horaStart;
	}

	public Date getHourEnd() {
		return hourEnd;
	}

	public void setHourEnd(Date horaEnd) {
		this.hourEnd = horaEnd;
	}

	public void setMeetLink(String meetLink) {
		this.meetLink = meetLink;
	}

	

	//public Date getDateEnd() {
	//	return dateEnd;
	//}

	/*public void setDateEnd(Date dateEnd) {
		this.dateEnd = dateEnd;
	}*/

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
