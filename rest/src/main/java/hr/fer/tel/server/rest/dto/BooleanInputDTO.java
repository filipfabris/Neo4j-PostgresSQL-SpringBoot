package hr.fer.tel.server.rest.dto;

import com.fasterxml.jackson.annotation.JsonTypeName;

import hr.fer.tel.server.rest.model.BooleanInput;
import hr.fer.tel.server.rest.model.InputType;

@JsonTypeName("BOOLEAN")
public class BooleanInputDTO extends InputsDTO {

	private String description;
	private boolean defaultValue;

	public BooleanInputDTO(String name, String title, int inputOrder, String description, boolean defaultValue) {
		super(InputType.BOOLEAN, name, title, inputOrder);
		this.description = description;
		this.defaultValue = defaultValue;
	}

	public BooleanInputDTO() {
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public boolean isDefaultValue() {
		return defaultValue;
	}

	public void setDefaultValue(boolean defaultValue) {
		this.defaultValue = defaultValue;
	}

	public static BooleanInputDTO of(BooleanInput input) {
		return new BooleanInputDTO(input.getName(), input.getTitle(), input.getInputOrder(), input.getDescription(), input.isDefaultValue());
	}

}
