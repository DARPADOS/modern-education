package pe.edu.upc.moderneducation.model.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PostLoad;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "Course",
indexes = {@Index(columnList="name",name="course_index_name"),
		@Index(columnList="language_id",name="course_index_language")})
//@SequenceGenerator(name="Course_course_id_seq",initialValue=1,allocationSize = 1)
public class Course {
	
	@Id
	//@GeneratedValue(strategy = GenerationType.IDENTITY, generator="Course_course_id_seq")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "course_id")
	private Integer id;
	
	@Column(name="published")
	private boolean published;
	
	@ManyToOne
	@JoinColumn(name = "teacher_id", nullable = false)
	private Teacher teacher;
	
	@Column(name = "name", length = 100, nullable = false)
	private String name;
	
	//@Column(name = "language", length = 20, nullable = false)
	//private String language;
	
	@Column(name = "description", length = 500, nullable = false)
	private String description;
	
	@Column(name = "mineture_image", length = 500)
	private String mineture_image;

	@OneToMany(mappedBy = "course", fetch = FetchType.LAZY)
	private List<Chapter> chapter;
	
	@OneToMany(mappedBy = "course", fetch = FetchType.LAZY)
	private List<Resource> resource;

	@OneToMany(mappedBy="course", fetch=FetchType.LAZY, cascade = CascadeType.REMOVE)
	private List<DetailCourseStudent> detailCourseStudent;

	@ManyToOne
	@JoinColumn(name = "language_id",nullable = true)
	@OnDelete(action = OnDeleteAction.CASCADE)
	private Language language;

	@Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_date", nullable = true, updatable = true)
    @CreatedDate
    private Date createdDate;

	@Transient
	private float averageQualification;

	@Transient
	private int reviewsCount;

	@Transient
	private int filledStars;

	@Transient
	private int emptyStars;

	@Transient
	private List<Integer> countRating;

	public List<Integer> getCountRating() {
		return countRating;
	}

	public void setCountRating(List<Integer> countRating) {
		this.countRating = countRating;
	}

	@PostLoad
	private void chargeAverageQualification(){
		float sum = 0;
		reviewsCount = 0;

		for (DetailCourseStudent detail : detailCourseStudent) {
			if(detail.getQualification() != null && detail.getQualification() <=5){
				sum = sum + detail.getQualification();
				reviewsCount++;
			}
		}

		if(reviewsCount !=0){
			averageQualification = sum/reviewsCount;
			averageQualification = Math.round(averageQualification*10);
			averageQualification = averageQualification/10;
		}
		else
			averageQualification = 0;

		// For count stars of average rating
		filledStars = (int) Math.floor(averageQualification);
		emptyStars = (int) Math.floor(5-averageQualification);
	}

	public Course(Integer id, boolean published, Teacher teacher, String name, Language language, String description,
			String mineture_image, List<Chapter> chapter, List<Resource> resource, List<DetailCourseStudent> detailCourseStudent) {
		super();
		this.id = id;
		this.published = published;
		this.teacher = teacher;
		this.name = name;
		//this.language = language;
		this.description = description;
		this.mineture_image = mineture_image;
		this.chapter = chapter;
		this.resource = resource;
		this.detailCourseStudent=detailCourseStudent;
		this.language=language;
	}
		
	public int getReviewsCount() {
		return reviewsCount;
	}

	public void setReviewsCount(int reviewsCount) {
		this.reviewsCount = reviewsCount;
	}

	public int getFilledStars() {
		return filledStars;
	}

	public void setFilledStars(int filledStars) {
		this.filledStars = filledStars;
	}

	public int getEmptyStars() {
		return emptyStars;
	}

	public void setEmptyStars(int emptyStars) {
		this.emptyStars = emptyStars;
	}

	public float getAverageQualification() {
		return averageQualification;
	}

	public void setAverageQualification(float averageQualification) {
		this.averageQualification = averageQualification;
	}

	public Course() {
		super();
		chapter=new ArrayList<Chapter>();
		resource=new ArrayList<Resource>();
		detailCourseStudent=new ArrayList<DetailCourseStudent>();
		// TODO Auto-generated constructor stub
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public boolean isPublished() {
		return published;
	}

	public void setPublished(boolean published) {
		this.published = published;
	}

	public Teacher getTeacher() {
		return teacher;
	}

	public void setTeacher(Teacher teacher) {
		this.teacher = teacher;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	/*public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}*/

	public String getDescription() {
		return description;
	}

	public Language getLanguage() {
		return language;
	}

	public void setLanguage(Language language) {
		this.language = language;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getMineture_image() {
		return mineture_image;
	}

	public void setMineture_image(String mineture_image) {
		this.mineture_image = mineture_image;
	}

	public List<Chapter> getChapter() {
		return chapter;
	}

	public void setChapter(List<Chapter> chapter) {
		this.chapter = chapter;
	}

	public List<Resource> getResource() {
		return resource;
	}

	public void setResource(List<Resource> resource) {
		this.resource = resource;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public List<DetailCourseStudent> getDetailCourseStudent() {
		return detailCourseStudent;
	}

	public void setDetailCourseStudent(List<DetailCourseStudent> detailCourseStudent) {
		this.detailCourseStudent = detailCourseStudent;
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
		Course other = (Course) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	
}
