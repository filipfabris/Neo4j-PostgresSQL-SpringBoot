package hr.fer.tel.server.rest.dto;

import com.fasterxml.jackson.annotation.JsonTypeName;

import hr.fer.tel.server.rest.model.ActuationView;

@JsonTypeName("actuation")
public class ActuationViewDTO extends ViewDTO{

	private ActuationFormDTO form;

	public ActuationViewDTO(ActuationFormDTO form) {
		super();
		this.form = form;
	}

	public ActuationViewDTO() {
		super();
	}

	public ActuationFormDTO getForm() {
		return form;
	}

	public void setForm(ActuationFormDTO form) {
		this.form = form;
	}

	public static ActuationViewDTO of(ActuationView view) {

		ActuationViewDTO tmp = new ActuationViewDTO(ActuationFormDTO.of(view.getForm()));

		tmp.setTitle(view.getTitle());
		tmp.setDescription(view.getDescription());
		tmp.setViewType(view.getViewType());
		return tmp;
	}




}