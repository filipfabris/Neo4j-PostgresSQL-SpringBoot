package hr.fer.tel.server.rest.model;

import javax.persistence.Entity;
import javax.persistence.Table;

import hr.fer.tel.server.rest.dto.DecimalInputDTO;

@Entity
@Table(name = "DecimalInput")
public class DecimalInput extends Inputs {

	private String description;
	private Double defaultValue;
	private Double min;
	private Double max;

	public DecimalInput(String name, String title, int inputOrder, String description, Double defaultValue, Double min, Double max) {
		super(0, InputType.DECIMAL, name, title, inputOrder);
		this.description = description;
		this.defaultValue = defaultValue;
		this.min = min;
		this.max = max;
	}

	public DecimalInput(DecimalInputDTO input) {
		super(0, InputType.BOOLEAN, input.getName(), input.getTitle(), input.getInputOrder());
		this.description = input.getDescription();
		this.defaultValue = input.getDefaultValue();
		this.min = input.getMin();
		this.max = input.getMax();
	}

	public DecimalInput() {

	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Double getDefaultValue() {
		return defaultValue;
	}

	public void setDefaultValue(Double defaultValue) {
		this.defaultValue = defaultValue;
	}

	public Double getMin() {
		return min;
	}

	public void setMin(Double min) {
		this.min = min;
	}

	public Double getMax() {
		return max;
	}

	public void setMax(Double max) {
		this.max = max;
	}

}
