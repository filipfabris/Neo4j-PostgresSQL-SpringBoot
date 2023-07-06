package hr.fer.tel.server.rest.model;

import javax.persistence.Entity;
import javax.persistence.Table;

import hr.fer.tel.server.rest.dto.SubmitButtonDTO;

@Entity
@Table(name = "SubmitButton")
public class SubmitButton extends Inputs {

	public SubmitButton(String name, String title, int inputOrder) {
		super(0, InputType.SUBMIT, name, title, inputOrder);
		// TODO Auto-generated constructor stub
	}

	public SubmitButton(SubmitButtonDTO input) {
		super(0, InputType.BOOLEAN, input.getName(), input.getTitle(), input.getInputOrder());
	}

	public SubmitButton() {

	}

}
