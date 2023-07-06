package hr.fer.tel.server.rest.dto;

import java.util.List;

import hr.fer.tel.server.rest.model.ActuationForm;

public class ActuationFormDTO {

    private RequestDTO defaultValuesRequest;

    private RequestDTO submitFormRequest;

    private List<InputsDTO> inputs;

	public ActuationFormDTO(RequestDTO defaultValuesRequest, RequestDTO submitFormRequest,
			List<InputsDTO> inputs) {
		super();
		this.defaultValuesRequest = defaultValuesRequest;
		this.submitFormRequest = submitFormRequest;
		this.inputs = inputs;
	}

	public ActuationFormDTO() {
		super();
	}

	public RequestDTO getDefaultValuesRequest() {
		return defaultValuesRequest;
	}

	public void setDefaultValuesRequest(RequestDTO defaultValuesRequest) {
		this.defaultValuesRequest = defaultValuesRequest;
	}

	public RequestDTO getSubmitFormRequest() {
		return submitFormRequest;
	}

	public void setSubmitFormRequest(RequestDTO submitFormRequest) {
		this.submitFormRequest = submitFormRequest;
	}

	public List<InputsDTO> getInputs() {
		return inputs;
	}

	public void setInputs(List<InputsDTO> inputs) {
		this.inputs = inputs;
	}

	public static ActuationFormDTO of(ActuationForm form) {

		RequestDTO req1;
		if (form.getDefaultValuesRequest() == null) {
			req1 = null;
		}
		else {
			req1 = RequestDTO.of(form.getDefaultValuesRequest());
		}

		RequestDTO req2;
		if (form.getSubmitFormRequest() == null) {
			req2 = null;
		}
		else {
			req2 = RequestDTO.of(form.getSubmitFormRequest());
		}

		return new ActuationFormDTO(req1, req2, InputsDTO.of(form.getInputs()));
	}

}
