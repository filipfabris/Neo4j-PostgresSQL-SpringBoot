package hr.fer.tel.server.rest.model;


import java.util.LinkedList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import hr.fer.tel.server.rest.dto.BooleanInputDTO;
import hr.fer.tel.server.rest.dto.DateInputDTO;
import hr.fer.tel.server.rest.dto.DecimalInputDTO;
import hr.fer.tel.server.rest.dto.InputsDTO;
import hr.fer.tel.server.rest.dto.IntegerInputDTO;
import hr.fer.tel.server.rest.dto.MeasurmentSelectFormDTO;
import hr.fer.tel.server.rest.dto.StringInputDTO;
import hr.fer.tel.server.rest.dto.SubmitButtonDTO;
import hr.fer.tel.server.rest.dto.TimeInputDTO;


@Entity
@Table(name = "MeasurmentSelectForm")
public class MeasurmentSelectForm {

    @Id
    @GeneratedValue
	private long id;

    @OneToOne(cascade = CascadeType.ALL)
	private Request submitSelectionRequest;

//	@Convert(converter = BodyHelperJson.class)
//    private Map<String, String> inputs;

	@OneToMany(cascade = CascadeType.ALL)
    private List<Inputs> inputs;

	public MeasurmentSelectForm(Request submitSelectionRequest, List<Inputs> inputs) {
		super();
		this.submitSelectionRequest = submitSelectionRequest;
		this.inputs = inputs;
	}

//	public MeasurmentSelectForm(Request submitSelectionRequest, Inputs inputs) {
//		super();
//		this.submitSelectionRequest = submitSelectionRequest;
//		this.inputs = inputs;
//	}

	public MeasurmentSelectForm() {
	}

	public MeasurmentSelectForm(MeasurmentSelectFormDTO dto) {
		if(dto.getSubmitSelectionRequest() == null) {
			this.submitSelectionRequest = null;
		}else {			
			this.submitSelectionRequest = new Request(dto.getSubmitSelectionRequest());
		}

		this.inputs = new LinkedList<>();
		for(InputsDTO inputDto: dto.getInputs()) {
		  Inputs input = extractInput(inputDto);
		  
		  if(input != null) // TODO ovo bi trebalo raditi s exceptionma
		    this.inputs.add(input);
		}
	}

  private Inputs extractInput(InputsDTO dto) {
    if (dto instanceof BooleanInputDTO) {
			BooleanInputDTO a = (BooleanInputDTO)dto;
			return new BooleanInput(a);
		}

		if (dto instanceof DateInputDTO) {
			DateInputDTO a = (DateInputDTO)dto;
			return new DateInput(a);
		}

		if (dto instanceof DecimalInputDTO) {
			DecimalInputDTO a = (DecimalInputDTO)dto;
			return new DecimalInput(a);
		}

		if (dto instanceof IntegerInputDTO) {
			IntegerInputDTO a = (IntegerInputDTO)dto;
			return new IntegerInput(a);
		}

		if (dto instanceof StringInputDTO) {
			StringInputDTO a = (StringInputDTO)dto;
			return new StringInput(a);
		}

		if (dto instanceof SubmitButtonDTO) {
			SubmitButtonDTO a = (SubmitButtonDTO)dto;
			return new SubmitButton(a);
		}

		if (dto instanceof TimeInputDTO) {
			TimeInputDTO a = (TimeInputDTO)dto;
			return new TimeInput(a);
		}

		return null; // TODO baciti iznimku
  }

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Request getSubmitSelectionRequest() {
		return submitSelectionRequest;
	}

	public void setSubmitSelectionRequest(Request submitSelectionRequest) {
		this.submitSelectionRequest = submitSelectionRequest;
	}

	public List<Inputs> getInputs() {
		return inputs;
	}

	public void setInputs(List<Inputs> inputs) {
		this.inputs = inputs;
	}

//	public Map<String, String> getInputs() {
//		return inputs;
//	}
//
//	public void setInputs(Map<String, String> inputs) {
//		this.inputs = inputs;
//	}



}
