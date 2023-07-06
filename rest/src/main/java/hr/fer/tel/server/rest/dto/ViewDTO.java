package hr.fer.tel.server.rest.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

import hr.fer.tel.server.rest.model.View;

@JsonTypeInfo(
	      use = JsonTypeInfo.Id.NAME,
	      include = JsonTypeInfo.As.EXISTING_PROPERTY,
	      visible = true,
	      property = "viewType")
	    @JsonSubTypes({
	        @JsonSubTypes.Type(value = ActuationViewDTO.class, name = "actuation"),
	        @JsonSubTypes.Type(value = MesurmentViewDTO.class, name = "mesurment"),
	        @JsonSubTypes.Type(value = MesurmentViewDTO.class, name = "single"),
	        @JsonSubTypes.Type(value = MesurmentViewDTO.class, name = "series")

	    })
public class ViewDTO {

    private String title;

    private String description;

    private String viewType;

	public ViewDTO(String title, String description, String viewType) {
		super();
		this.title = title;
		this.description = description;
		this.viewType = viewType;
	}


	public ViewDTO() {
		super();
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

	public static List<ViewDTO> of(List<View> views) {
		return views.stream().map(view -> ViewDTO.of(view)).toList();
	}

	public static ViewDTO of(View view) {
		return new ViewDTO(view.getTitle(), view.getDescription(), view.getViewType());
	}

}
