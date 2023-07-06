package hr.fer.tel.server.rest.model;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonBackReference;

import hr.fer.tel.server.rest.dto.ViewDTO;


@Entity
//@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
//@DiscriminatorColumn(name = "type")
@Inheritance(strategy = InheritanceType.JOINED)
public class View {

    @Id
    @GeneratedValue
	private long id;

    private String title;

    private String description;

    private String viewType;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonBackReference
    private Scene scene;

    public View(ViewDTO dto){
    	this.title = dto.getTitle();
    	this.viewType = dto.getViewType();
    }

	public View() {

	}

	public View(long id, String title, String description, String viewType) {
		this.id = id;
		this.title = title;
		this.description = description;
		this.viewType = viewType;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

  public String getViewType() {
		return viewType;
	}

	public void setViewType(String viewType) {
		this.viewType = viewType;
	}

	public Scene getScene() {
		return scene;
	}

	public void setScene(Scene scene) {
		this.scene = scene;
	}
}
