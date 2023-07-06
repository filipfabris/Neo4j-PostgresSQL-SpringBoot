package hr.fer.tel.server.rest.model;

import javax.persistence.Entity;
import javax.persistence.Table;

import hr.fer.tel.server.rest.dto.DateInputDTO;

@Entity
@Table(name = "DateInput")
public class DateInput extends Inputs {

	private String description;
	private String defaultValue;

	public DateInput(String name, String title, int inputOrder, String description, String defaultValue) {
		super(0, InputType.DATE, name, title, inputOrder);
		this.description = description;
		this.defaultValue = defaultValue;
	}

	public DateInput(DateInputDTO input) {
		super(0, InputType.BOOLEAN, input.getName(), input.getTitle(), input.getInputOrder());
		this.description = input.getDescription();
		this.defaultValue = input.getDefaultValue();
	}

	public DateInput() {

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
