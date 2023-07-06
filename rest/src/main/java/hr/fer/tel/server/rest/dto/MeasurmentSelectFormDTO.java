package hr.fer.tel.server.rest.dto;

import java.util.List;

import hr.fer.tel.server.rest.model.MeasurmentSelectForm;


public class MeasurmentSelectFormDTO {

	private RequestDTO submitSelectionRequest;

    private List<InputsDTO> inputs;

	public MeasurmentSelectFormDTO(RequestDTO submitSelectionRequest, List<InputsDTO> inputs) {
		super();
		this.submitSelectionRequest = submitSelectionRequest;
		this.inputs = inputs;
	}

	public MeasurmentSelectFormDTO() {
	}

	public RequestDTO getSubmitSelectionRequest() {
		return submitSelectionRequest;
	}

	public void setSubmitSelectionRequest(RequestDTO submitSelectionRequest) {
		this.submitSelectionRequest = submitSelectionRequest;
	}

	public List<InputsDTO> getInputs() {
		return inputs;
	}

	public void setInputs(List<InputsDTO> inputs) {
		this.inputs = inputs;
	}

	public static MeasurmentSelectFormDTO of(MeasurmentSelectForm form) {
		RequestDTO req;

		if (form.getSubmitSelectionRequest() == null) {
			req = null;
		}
		else {
			req = RequestDTO.of(form.getSubmitSelectionRequest());
		}

		return new MeasurmentSelectFormDTO(req, InputsDTO.of(form.getInputs()));
	}


}
