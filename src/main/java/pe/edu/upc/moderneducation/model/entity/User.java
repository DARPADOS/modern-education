package pe.edu.upc.moderneducation.model.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
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

import pe.edu.upc.moderneducation.model.types.UserAuthorities;

@Entity
@Table(name="Users",
indexes = {@Index(columnList="last_name, first_name",name="user_index_last_first_name")},
uniqueConstraints= {@UniqueConstraint(columnNames={"email"})})
public class User {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="user_id")
	private Integer id;
	
	@Column(name="username",length=20,nullable=false)
	private String username;
	
	@Column(name="password", length=60,nullable=false)
	private String password;
	
	@Column(name="first_name", length=100,nullable=false)	
	private String firstName;
	
	@Column(name="last_name", length=100,nullable=false)	
	private String lastName;
	
	@Column(name="email", length=50, nullable=false)
	private String email;
	
	@Column(name="profile_image", length=500, nullable=true)
	private String profileImage;
	
	@OneToOne(mappedBy="user")
	private Teacher teacher;
	
	private boolean enable;

	@OneToOne(mappedBy="user")
	private Student student;
	
	@OneToMany(mappedBy="main", fetch=FetchType.LAZY)
	private List<Comment> comments;

	@OneToMany(mappedBy = "user", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	private List<Authority> authorities;

	public User() {
		super();
		this.enable = true;
		comments=new ArrayList<Comment>();
		authorities = new ArrayList<Authority>();
	}

	public User(Integer id, String username, String password, String firstName, String lastName, String email,
			String profileImage, Teacher teacher, boolean enable, Student student, List<Comment> comments,
			List<Authority> authorities) {
		this.id = id;
		this.username = username;
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.profileImage = profileImage;
		this.teacher = teacher;
		this.enable = enable;
		this.student = student;
		this.comments = comments;
		this.authorities = authorities;
	}

	// Agregar el ROLE o ACCESS al usuario
	public void addAuthority(UserAuthorities auth) {
		Authority authority = new Authority();
		authority.setAuthority(auth.name()) ;
		authority.setUser(this);
		
		this.authorities.add(authority);
	}

	public boolean hasRoleStudent(){
		for (Authority auth : authorities) {
			if(auth.getAuthority().matches(UserAuthorities.ROLE_STUDENT.name())){
				return true;
			}
		}
		return false;
	}

	public boolean hasRoleTeacher(){
		for (Authority auth : this.authorities) {
			if(auth.getAuthority().matches(UserAuthorities.ROLE_TEACHER.name())){
				return true;
			}
		}
		return false;
	}

	public boolean isEnable() {
		return enable;
	}

	public void setEnable(boolean enable) {
		this.enable = enable;
	}

	public List<Authority> getAuthorities() {
		return authorities;
	}

	public void setAuthorities(List<Authority> authorities) {
		this.authorities = authorities;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
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
