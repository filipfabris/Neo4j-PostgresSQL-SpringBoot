package hr.fer.tel.server.rest.dto;

import com.fasterxml.jackson.annotation.JsonTypeName;

import hr.fer.tel.server.rest.model.InputType;
import hr.fer.tel.server.rest.model.TimeInput;

@JsonTypeName("TIME")
public class TimeInputDTO extends InputsDTO {

	private String description;
	private String defaultValue;

	public TimeInputDTO(String name, String title, int inputOrder, String description, String defaultValue) {
		super(InputType.TIME, name, title, inputOrder);
		this.description = description;
		this.defaultValue = defaultValue;
	}

	public TimeInputDTO() {

	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getDefaultValue() {
		return defaultValue;
	}

	public void setDefaultValue(String defaultValue) {
		this.defaultValue = defaultValue;
	}

	public static TimeInputDTO of(TimeInput input) {
		return new TimeInputDTO(input.getName(), input.getTitle(), input.getInputOrder(), input.getDescription(), input.getDefaultValue());
	}
}
