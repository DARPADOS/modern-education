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

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
@Table(name = "Comment")
//@SequenceGenerator(name="Comment_comment_id_seq",initialValue=1,allocationSize = 1)
public class Comment {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "comment_id")
	private Integer id;
	
	@ManyToOne
	@JoinColumn(name = "user_id", nullable = false)
	private User user;
	
	@ManyToOne
	@JoinColumn(name = "video_id", nullable = false)
	@OnDelete(action = OnDeleteAction.CASCADE)
	private Video video;
	
	@Column(name = "date", nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date date;
	
	@Column(name = "content", length = 500, nullable = false)
	private String content;
	
	@ManyToOne
	@JoinColumn(name = "main_id")
	private Comment main;
	
	@OneToMany(mappedBy = "main", fetch = FetchType.LAZY)
	private List<Comment> responses;

	public Comment(Integer id, User user, Video video, Date date, String content, Comment main,
			List<Comment> responses) {
		super();
		this.id = id;
		this.user = user;
		this.video = video;
		this.date = date;
		this.content = content;
		this.main = main;
		this.responses = responses;
	}

	public Comment() {
		super();
		responses= new ArrayList<Comment>();
		// TODO Auto-generated constructor stub
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Video getVideo() {
		return video;
	}

	public void setVideo(Video video) {
		this.video = video;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Comment getMain() {
		return main;
	}

	public void setMain(Comment main) {
		this.main = main;
	}

	public List<Comment> getResponses() {
		return responses;
	}

	public void setResponses(List<Comment> responses) {
		this.responses = responses;
	}
	
}
