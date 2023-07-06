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

import hr.fer.tel.server.rest.dto.ActuationFormDTO;
import hr.fer.tel.server.rest.dto.BooleanInputDTO;
import hr.fer.tel.server.rest.dto.DateInputDTO;
import hr.fer.tel.server.rest.dto.DecimalInputDTO;
import hr.fer.tel.server.rest.dto.InputsDTO;
import hr.fer.tel.server.rest.dto.IntegerInputDTO;
import hr.fer.tel.server.rest.dto.StringInputDTO;
import hr.fer.tel.server.rest.dto.SubmitButtonDTO;
import hr.fer.tel.server.rest.dto.TimeInputDTO;

@Entity
@Table(name = "ActuationForm")
public class ActuationForm {

    @Id
    @GeneratedValue
	private long id;

    @OneToOne(cascade = CascadeType.ALL)
    private Request defaultValuesRequest;

    @OneToOne(cascade = CascadeType.ALL)
    private Request submitFormRequest;

//	@Convert(converter = BodyHelperJson.class)
//    private Map<String, String> inputs;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Inputs> inputs;

	public ActuationForm(Request defaultValuesRequest, Request submitFormRequest, List<Inputs> inputs) {
		this.defaultValuesRequest = defaultValuesRequest;
		this.submitFormRequest = submitFormRequest;
		this.inputs = inputs;
	}

	public ActuationForm() {
	}

	public ActuationForm(ActuationFormDTO dto) {
		this.defaultValuesRequest = new Request(dto.getDefaultValuesRequest());
		this.submitFormRequest = new Request(dto.getSubmitFormRequest());

		this.inputs = new LinkedList<>();
		for(InputsDTO inputDto: dto.getInputs()) {
		  Inputs input = extractInput(inputDto);
		  if(inputDto != null)
		    this.inputs.add(input);
		}


	}

    private Inputs extractInput(InputsDTO input) {
      if (input instanceof BooleanInputDTO) {
        BooleanInputDTO a = (BooleanInputDTO) input;
        return new BooleanInput(a);
      }

      if (input instanceof DateInputDTO) {
        DateInputDTO a = (DateInputDTO) input;
        return new DateInput(a);
      }

      if (input instanceof DecimalInputDTO) {
        DecimalInputDTO a = (DecimalInputDTO) input;
        return new DecimalInput(a);
      }

      if (input instanceof IntegerInputDTO) {
        IntegerInputDTO a = (IntegerInputDTO) input;
        return new IntegerInput(a);
      }

      if (input instanceof StringInputDTO) {
        StringInputDTO a = (StringInputDTO) input;
        return new StringInput(a);
      }

      if (input instanceof SubmitButtonDTO) {
        SubmitButtonDTO a = (SubmitButtonDTO) input;
        return new SubmitButton(a);
      }

      if (input instanceof TimeInputDTO) {
        TimeInputDTO a = (TimeInputDTO) input;
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

	public Request getDefaultValuesRequest() {
		return defaultValuesRequest;
	}

	public void setDefaultValuesRequest(Request defaultValuesRequest) {
		this.defaultValuesRequest = defaultValuesRequest;
	}

	public Request getSubmitFormRequest() {
		return submitFormRequest;
	}

	public void setSubmitFormRequest(Request submitFormRequest) {
		this.submitFormRequest = submitFormRequest;
	}

	public List<Inputs> getInputs() {
		return inputs;
	}

	public void setInputs(List<Inputs> inputs) {
		this.inputs = inputs;
	}



}
