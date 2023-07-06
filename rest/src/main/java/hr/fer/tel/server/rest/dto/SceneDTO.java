package hr.fer.tel.server.rest.dto;

import java.util.ArrayList;
import java.util.List;

import hr.fer.tel.server.rest.model.*;

public class SceneDTO {
	
	private long id;

	private String title;

	private String subtitle;

	private String layout;

	private String pictureLink;

	private List<String> tags = new ArrayList<>();

	private List<ViewDTO> views = new ArrayList<>();

	private List<String> roles = new ArrayList<>();

	private List<String> keys = new ArrayList<>();

	public SceneDTO(long id, String title, String subtitle, String layout, String pictureLink, List<String> tags,
					List<ViewDTO> views, List<String> roles, List<String> keys) {
		super();
		this.id = id;
		this.title = title;
		this.subtitle = subtitle;
		this.layout = layout;
		this.pictureLink = pictureLink;
		this.tags = tags;
		this.views = views;
		this.roles = roles;
		this.keys = keys;
	}

	public SceneDTO() {
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

	public String getLayout() {
		return layout;
	}

	public void setLayout(String layout) {
		this.layout = layout;
	}

	public String getPictureLink() {
		return pictureLink;
	}

	public void setPictureLink(String pictureLink) {
		this.pictureLink = pictureLink;
	}

	public List<String> getTags() {
		return tags;
	}

	public void setTags(List<String> tags) {
		this.tags = tags;
	}

	public List<ViewDTO> getViews() {
		return views;
	}

	public void setViews(List<ViewDTO> views) {
		this.views = views;
	}

	public List<String> getRoles() {
		return roles;
	}

	public void setRoles(List<String> roles) {
		this.roles = roles;
	}

	public List<String> getKeys() {
		return keys;
	}

	public void setKeys(List<String> keys) {
		this.keys = keys;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public static SceneDTO of(Scene scene) {

		List<String> tags = scene.getTags();

		List<ViewDTO> views = new ArrayList<>();

		for (View temp : scene.getViews()) {

			if (temp instanceof ActuationView) {

				ActuationView a = (ActuationView) temp;
				ActuationViewDTO view1 = ActuationViewDTO.of(a);
				views.add(view1);

			}

			if (temp instanceof MesurmentView) {

				MesurmentView a = (MesurmentView) temp;
				MesurmentViewDTO view1 = MesurmentViewDTO.of(a);
				views.add(view1);

			}

		}

		List<String> roles = new ArrayList<>();
		for (Role role : scene.getRoles()) {
			roles.add(role.getName());
		}

			return new SceneDTO(scene.getId(), scene.getTitle(), scene.getSubtitle(), scene.getLayout().getName(), scene.getPictureLink(),
					tags, views, roles, scene.getKeyNames());
		}


	}
