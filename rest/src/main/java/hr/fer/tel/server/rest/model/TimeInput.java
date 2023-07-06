package hr.fer.tel.server.rest.model;

import javax.persistence.Entity;
import javax.persistence.Table;

import hr.fer.tel.server.rest.dto.TimeInputDTO;

@Entity
@Table(name = "TimeInput")
public class TimeInput extends Inputs {

	private String description;
	private String defaultValue;

	public TimeInput(String name, String title, int inputOrder, String description, String defaultValue) {
		super(0, InputType.TIME, name, title, inputOrder);
		this.description = description;
		this.defaultValue = defaultValue;
	}

	public TimeInput(TimeInputDTO input) {
		super(0, InputType.BOOLEAN, input.getName(), input.getTitle(), input.getInputOrder());
		this.description = input.getDescription();
		this.defaultValue = input.getDefaultValue();
	}

	public TimeInput() {

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

}
