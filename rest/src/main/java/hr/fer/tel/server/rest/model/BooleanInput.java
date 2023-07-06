package hr.fer.tel.server.rest.model;

import javax.persistence.Entity;
import javax.persistence.Table;

import hr.fer.tel.server.rest.dto.BooleanInputDTO;

@Entity
@Table(name = "BooleanInput")
public class BooleanInput extends Inputs {

	private String description;
	private boolean defaultValue;

	public BooleanInput(String name, String title, int inputOrder, String description, boolean defaultValue) {
		super(0, InputType.BOOLEAN, name, title, inputOrder);
		this.description = description;
		this.defaultValue = defaultValue;
	}

	public BooleanInput(BooleanInputDTO input) {
		super(0, InputType.BOOLEAN, input.getName(), input.getTitle(), input.getInputOrder());
		this.description = input.getDescription();
		this.defaultValue = input.isDefaultValue();
	}

	public BooleanInput() {
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

}
