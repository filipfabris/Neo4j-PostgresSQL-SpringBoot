package hr.fer.tel.server.rest.dto;

import com.fasterxml.jackson.annotation.JsonTypeName;

import hr.fer.tel.server.rest.model.DateInput;
import hr.fer.tel.server.rest.model.InputType;

@JsonTypeName("DATE")
public class DateInputDTO extends InputsDTO {

	private String description;
	private String defaultValue;

	public DateInputDTO(String name, String title, int inputOrder, String description, String defaultValue) {
		super(InputType.DATE, name, title, inputOrder);
		this.description = description;
		this.defaultValue = defaultValue;
	}

	public DateInputDTO() {

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

	public static DateInputDTO of(DateInput input) {
		return new DateInputDTO(input.getName(), input.getTitle(), input.getInputOrder(), input.getDescription(), input.getDefaultValue());
	}

}
