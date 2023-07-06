package hr.fer.tel.server.rest.dto;

import com.fasterxml.jackson.annotation.JsonTypeName;

import hr.fer.tel.server.rest.model.InputType;
import hr.fer.tel.server.rest.model.IntegerInput;


@JsonTypeName("INTEGER")
public class IntegerInputDTO extends InputsDTO {

	private String description;
	private Integer defaultValue;
	private Integer min;
	private Integer max;

	public IntegerInputDTO(String name, String title, int inputOrder, String description, Integer defaultValue, Integer min, Integer max) {
		super(InputType.INTEGER, name, title, inputOrder);
		this.description = description;
		this.defaultValue = defaultValue;
		this.min = min;
		this.max = max;
	}

	public IntegerInputDTO() {

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

	public static IntegerInputDTO of(IntegerInput input) {
		return new IntegerInputDTO(input.getName(), input.getTitle(), input.getInputOrder(), input.getDescription(), input.getDefaultValue(), input.getMin(), input.getMax());
	}
}
