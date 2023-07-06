package hr.fer.tel.server.rest.dto;

import com.fasterxml.jackson.annotation.JsonTypeName;

import hr.fer.tel.server.rest.model.DecimalInput;
import hr.fer.tel.server.rest.model.InputType;


@JsonTypeName("DECIMAL")
public class DecimalInputDTO extends InputsDTO {

	private String description;
	private Double defaultValue;
	private Double min;
	private Double max;

	public DecimalInputDTO(String name, String title, int inputOrder, String description, Double defaultValue, Double min, Double max) {
		super(InputType.DECIMAL, name, title, inputOrder);
		this.description = description;
		this.defaultValue = defaultValue;
		this.min = min;
		this.max = max;
	}

	public DecimalInputDTO() {

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

	public static DecimalInputDTO of(DecimalInput input) {
		return new DecimalInputDTO(input.getName(), input.getTitle(), input.getInputOrder(), input.getDescription(), input.getDefaultValue(), input.getMin(), input.getMax());
	}

}
