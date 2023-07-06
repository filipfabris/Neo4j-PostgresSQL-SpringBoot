package hr.fer.tel.server.rest.model;

import javax.persistence.Entity;
import javax.persistence.Table;

import hr.fer.tel.server.rest.dto.IntegerInputDTO;

@Entity
@Table(name = "IntegerInput")
public class IntegerInput extends Inputs {

	private String description;
	private Integer defaultValue;
	private Integer min;
	private Integer max;

	public IntegerInput(String name, String title, int inputOrder, String description, Integer defaultValue, Integer min, Integer max) {
		super(0, InputType.INTEGER, name, title, inputOrder);
		this.description = description;
		this.defaultValue = defaultValue;
		this.min = min;
		this.max = max;
	}

	public IntegerInput(IntegerInputDTO input) {
		super(0, InputType.BOOLEAN, input.getName(), input.getTitle(), input.getInputOrder());
		this.description = input.getDescription();
		this.defaultValue = input.getDefaultValue();
		this.min = input.getMin();
		this.max = input.getMax();
	}

	public IntegerInput() {

	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Integer getDefaultValue() {
		return defaultValue;
	}

	public void setDefaultValue(Integer defaultValue) {
		this.defaultValue = defaultValue;
	}

	public Integer getMin() {
		return min;
	}

	public void setMin(Integer min) {
		this.min = min;
	}

	public Integer getMax() {
		return max;
	}

	public void setMax(Integer max) {
		this.max = max;
	}

}
