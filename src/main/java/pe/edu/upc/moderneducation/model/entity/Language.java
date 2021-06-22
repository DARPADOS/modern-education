package pe.edu.upc.moderneducation.model.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="Language")
public class Language {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "language_id")
	private Integer id;

    @Column(name = "name", length = 100, nullable = false)
	private String name;

	@OneToMany(mappedBy = "language", fetch = FetchType.LAZY)
	private List<Course> course;

    public Language(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public Language(){
        super();
        course=new ArrayList<Course>();
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

    
}
