package hr.fer.tel.server.rest.dto;

import java.util.LinkedList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeInfo.As;

import hr.fer.tel.server.rest.model.BooleanInput;
import hr.fer.tel.server.rest.model.DateInput;
import hr.fer.tel.server.rest.model.DecimalInput;
import hr.fer.tel.server.rest.model.InputType;
import hr.fer.tel.server.rest.model.Inputs;
import hr.fer.tel.server.rest.model.IntegerInput;
import hr.fer.tel.server.rest.model.StringInput;
import hr.fer.tel.server.rest.model.SubmitButton;
import hr.fer.tel.server.rest.model.TimeInput;


@JsonTypeInfo(
	      use = JsonTypeInfo.Id.NAME,
	      include = As.EXISTING_PROPERTY,
	      visible = true,
	      property = "inputType")
	    @JsonSubTypes({
	        @JsonSubTypes.Type(value = BooleanInputDTO.class, name = "BOOLEAN"),
	        @JsonSubTypes.Type(value = DateInputDTO.class, name = "DATE"),
	        @JsonSubTypes.Type(value = DecimalInputDTO.class, name = "DECIMAL"),
	        @JsonSubTypes.Type(value = IntegerInputDTO.class, name = "INTEGER"),
	        @JsonSubTypes.Type(value = StringInputDTO.class, name = "STRING"),
	        @JsonSubTypes.Type(value = SubmitButtonDTO.class, name = "SUBMIT"),
	        @JsonSubTypes.Type(value = TimeInputDTO.class, name = "TIME")
	    })
public class InputsDTO {
	private InputType inputType;
	private String name;
	private String title;
	private int inputOrder;

	public InputsDTO(InputType inputType, String name, String title, int inputOrder) {
		super();
		this.inputType = inputType;
		this.name = name;
		this.title = title;
        this.inputOrder = inputOrder;
	}

	public InputsDTO() {

	}

	public InputType getInputType() {
		return inputType;
	}

	public void setInputType(InputType inputType) {
		this.inputType = inputType;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

    public int getInputOrder() {
      return inputOrder;
    }

    public void setInputOrder(int inputOrder) {
      this.inputOrder = inputOrder;
    }

  public static List<InputsDTO> of(List<Inputs> inputs) {

	  List<InputsDTO> inputDtos = new LinkedList<>();

	  for(Inputs input: inputs) {

		if (input instanceof BooleanInput) {

			BooleanInput a = (BooleanInput) input;
			BooleanInputDTO input1 = BooleanInputDTO.of(a);
			inputDtos.add(input1);

		}
		if (input instanceof DateInput) {

			DateInput a = (DateInput) input;
			DateInputDTO input1 = DateInputDTO.of(a);
			inputDtos.add(input1);

		}
		if (input instanceof DecimalInput) {

			DecimalInput a = (DecimalInput) input;
			DecimalInputDTO input1 = DecimalInputDTO.of(a);
			inputDtos.add(input1);

		}
		if (input instanceof IntegerInput) {

			IntegerInput a = (IntegerInput) input;
			IntegerInputDTO input1 = IntegerInputDTO.of(a);
			inputDtos.add(input1);

		}
		if (input instanceof StringInput) {

			StringInput a = (StringInput) input;
			StringInputDTO input1 = StringInputDTO.of(a);
			inputDtos.add(input1);

		}
		if (input instanceof SubmitButton) {

			SubmitButton a = (SubmitButton) input;
			SubmitButtonDTO input1 = SubmitButtonDTO.of(a);
			inputDtos.add(input1);

		}
		if (input instanceof TimeInput) {

			TimeInput a = (TimeInput) input;
			TimeInputDTO input1 = TimeInputDTO.of(a);
			inputDtos.add(input1);

		}
	  }

		return inputDtos;

	}
}
