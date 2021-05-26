package pe.edu.upc.moderneducation.model.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


@Entity
@Table(name="Payment")
@SequenceGenerator(name="Payment_payments_id_seq",initialValue=1,allocationSize = 1)
public class Payment {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="Payment_payments_id_seq")
	@Column(name = "payment_id")
	private Integer id;
	
	@Column(name = "amount")
	private Float amount;
	
	@Column(name = "date_time")
	@Temporal(TemporalType.TIMESTAMP)
	private Date dateTime;
	
	@ManyToOne
	@JoinColumn(name = "student_id", nullable=false)
	private Student student;

	public Payment(Integer id, Float amount, Date dateTime, Student student) {
		super();
		this.id = id;
		this.amount = amount;
		this.dateTime = dateTime;
		this.student = student;
	}

	public Payment() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Float getAmount() {
		return amount;
	}

	public void setAmount(Float amount) {
		this.amount = amount;
	}

	public Date getDateTime() {
		return dateTime;
	}

	public void setDateTime(Date dateTime) {
		this.dateTime = dateTime;
	}

	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}
	
}
