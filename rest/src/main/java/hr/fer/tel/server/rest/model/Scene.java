package hr.fer.tel.server.rest.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import hr.fer.tel.server.rest.dto.ActuationViewDTO;
import hr.fer.tel.server.rest.dto.MesurmentViewDTO;
import hr.fer.tel.server.rest.dto.SceneDTO;

@Entity
@Table(name = "Scene")
public class Scene {

	@Id
	@GeneratedValue
	private long id;

	@Column
	private String title;

	@Column
	private String subtitle;

	@OneToOne(cascade = CascadeType.ALL)
	private Layout layout;

	@Column
	private String pictureLink;

//	@OneToMany(mappedBy = "scene", cascade = CascadeType.ALL, orphanRemoval = true)
//	@JsonManagedReference
	@ElementCollection
	private List<String> tags = new ArrayList<>();

	@OneToMany(mappedBy = "scene", cascade = CascadeType.ALL, orphanRemoval = true)
	@JsonManagedReference
	private List<View> views = new ArrayList<>();

	@OneToMany(mappedBy = "scene", cascade = CascadeType.ALL, orphanRemoval = true)
	@JsonManagedReference
	private List<Role> roles = new ArrayList<>(); // roles required for specific scene

//	@OneToMany(mappedBy = "scene", cascade = CascadeType.ALL, orphanRemoval = true)
//	@JsonManagedReference
//	private List<Key> keys = new ArrayList<>(); // keys required for specific scene

	@Column(name = "keyNames")
	@ElementCollection(targetClass = String.class)
	private List<String> keyNames = new ArrayList<>();

	public Scene() {

	}

	public Scene(String title, String subtitle, Layout layout, String pictureLink, List<String> tags, List<View> views,
			List<Role> roles, List<String> keyNames) {
		this.title = title;
		this.subtitle = subtitle;
		this.layout = layout;
		this.pictureLink = pictureLink;
		this.tags = tags;
		this.views = views;
		this.roles = roles;
		this.keyNames = keyNames;

//		for (Tag tag : tags) {
//			tag.setScene(this);
//		}
		for (View view : views) {
			view.setScene(this);
		}
		for (Role role : roles) {
			role.setScene(this);
		}
//		for (String key : keyNames) {
//			keyNames.setScene(this);
//		}

	}

	public Scene(SceneDTO dto) {
		this.id = dto.getId();
		
		this.keyNames = dto.getKeys(); // .stream().map(key -> new Key(new KeyDTO(key))).toList();

		this.layout = new Layout(dto.getLayout());

		this.pictureLink = dto.getPictureLink();

		this.roles = dto.getRoles().stream().map(role -> new Role(role)).toList();

		this.subtitle = dto.getSubtitle();

		this.tags = dto.getTags();

		this.title = dto.getTitle();

//		this.views = dto.getViews().stream().map(view -> new View2(view)).toList();

		for (var temp : dto.getViews()) {

			if (temp instanceof ActuationViewDTO) {

				ActuationViewDTO a = (ActuationViewDTO) temp;
				ActuationView view1 = new ActuationView(a);
				this.views.add(view1);

			}

			if (temp instanceof MesurmentViewDTO) {

				MesurmentViewDTO a = (MesurmentViewDTO) temp;
				MesurmentView view1 = new MesurmentView(a);
				this.views.add(view1);

			}

		}

//		for (Tag tag : tags) {
//			tag.setScene(this);
//		}
		for (View view : views) {
			view.setScene(this);
		}
		for (Role role : roles) {
			role.setScene(this);
		}
//		for (Key key : keys) {
//			key.setScene(this);
//		}

	}

	public String getPictureLink() {
		return pictureLink;
	}

	public void setPictureLink(String pictureLink) {
		this.pictureLink = pictureLink;
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

	public String getSubtitle() {
		return subtitle;
	}

	public void setSubtitle(String subtitle) {
		this.subtitle = subtitle;
	}

	public Layout getLayout() {
		return layout;
	}

	public void setLayout(Layout layout) {
		this.layout = layout;
	}

	public List<String> getTags() {
		return tags;
	}

	public void setTags(List<String> tags) {
		this.tags = tags;
	}

	public List<View> getViews() {
		return views;
	}

	public void setViews(List<View> views) {
		this.views = views;
	}

	public List<Role> getRoles() {
		return roles;
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}

//	public List<Key> getKeys() {
//		return keys;
//	}
//
//	public void setKeys(List<Key> keys) {
//		this.keys = keys;
//	}

	public List<String> getKeyNames() {
		return keyNames;
	}

	public void setKeyNames(List<String> keyNames) {
		this.keyNames = keyNames;
	}

//    public void addTag(Tag tag) {
//    	this.tags.add(tag);
//    	tag.setScene(this);
//    }

	@Override
	public String toString() {
		return "Scene [id=" + id + ", title=" + title + ", subtitle=" + subtitle + ", layout=" + layout
				+ ", pictureLink=" + pictureLink + ", tags=" + tags + ", views=" + views + ", roles=" + roles
				+ ", keys=" + keyNames + "]";
	}

}
