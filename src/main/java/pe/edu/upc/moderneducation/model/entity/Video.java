package pe.edu.upc.moderneducation.model.entity;

import java.util.ArrayList;
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

@Entity
@Table(name = "video")
@SequenceGenerator(name="Video_video_id_seq",initialValue=1,allocationSize = 1)
public class Video {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="Video_video_id_seq")
	@Column(name = "video_id")
	private Integer id;
	
	@ManyToOne
	@JoinColumn(name = "chapter_id", nullable = true)
	private Chapter chapter;
	
	@Column(name = "name", length = 100, nullable = false)
	private String name;
	
	@Column(name = "description", length = 150, nullable = false)
	private String description;
	
	@Column(name = "link", length = 500, nullable = false)
	private String link;
	
	@OneToMany(mappedBy = "video", fetch = FetchType.LAZY)
	private List<Comment> comments;

	public Video() {
		super();
		// TODO Auto-generated constructor stub
		comments=new ArrayList<Comment>();
	}

	public Video(Integer id, Chapter chapter, String name, String description, String link, List<Comment> comments) {
		super();
		this.id = id;
		this.chapter = chapter;
		this.name = name;
		this.description = description;
		this.link = link;
		this.comments = comments;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Chapter getChapter() {
		return chapter;
	}

	public void setChapter(Chapter chapter) {
		this.chapter = chapter;
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

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public List<Comment> getComments() {
		return comments;
	}

	public void setComments(List<Comment> comments) {
		this.comments = comments;
	}

}
