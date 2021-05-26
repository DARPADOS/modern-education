package pe.edu.upc.moderneducation.model.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name="Users",
indexes = {@Index(columnList="last_name, first_name",name="user_index_last_first_name")},
uniqueConstraints= {@UniqueConstraint(columnNames={"email"})})
public class User {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="user_id")
	private Integer id;
	
	@Column(name="user_name",length=20,nullable=false)
	private String userName;
	
	@Column(name="password", length=16,nullable=false)
	private String password;
	
	@Column(name="first_name", length=100,nullable=false)	
	private String firstName;
	
	@Column(name="last_name", length=100,nullable=false)	
	private String lastName;
	
	@Column(name="email", length=50, nullable=false)
	private String email;
	
	@Column(name="profile_image", length=500, nullable=false)
	private String profileImage;
	
	@OneToOne(mappedBy="user")
	private Teacher teacher;
	
	
	@OneToOne(mappedBy="user")
	private Student student;
	
	@OneToMany(mappedBy="main", fetch=FetchType.LAZY)
	private List<Comment> comments;

	public User() {
		super();
		// TODO Auto-generated constructor stub
		comments=new ArrayList<Comment>();
	}

	public User(Integer id, String userName, String password, String firstName, String lastName, String email,
			String profileImage, Teacher teacher, Student student, List<Comment> comments) {
		super();
		this.id = id;
		this.userName = userName;
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.profileImage = profileImage;
		this.teacher = teacher;
		this.student = student;
		this.comments = comments;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getProfileImage() {
		return profileImage;
	}

	public void setProfileImage(String profileImage) {
		this.profileImage = profileImage;
	}

	public Teacher getTeacher() {
		return teacher;
	}

	public void setTeacher(Teacher teacher) {
		this.teacher = teacher;
	}

	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}

	public List<Comment> getComments() {
		return comments;
	}

	public void setComments(List<Comment> comments) {
		this.comments = comments;
	}
	
	
	
	
}
