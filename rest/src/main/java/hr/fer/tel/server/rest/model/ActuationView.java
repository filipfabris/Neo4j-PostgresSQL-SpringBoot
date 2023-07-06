package hr.fer.tel.server.rest.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;

import hr.fer.tel.server.rest.dto.ActuationViewDTO;

@Entity
//@DiscriminatorValue("actuation")
//@Table(name = "ActuationView")
////public class ActuationView{
@PrimaryKeyJoinColumn(name = "actuationId")
//@JsonTypeName("actuation")
public class ActuationView extends View{


//    @Id
//    @GeneratedValue
//	private long id;


    @OneToOne(cascade = CascadeType.ALL)
	private ActuationForm form;

	public ActuationView(String title, String description, String viewType, ActuationForm form) {
		super(0, title, description, viewType);
		this.form = form;
	}

	public ActuationView() {

	}

	public ActuationView(ActuationViewDTO dto) {
		super(0, dto.getTitle(), dto.getDescription(), dto.getViewType());

		this.form = new ActuationForm(dto.getForm());

	}

	public ActuationForm getForm() {
		return form;
	}

	public void setForm(ActuationForm form) {
		this.form = form;
	}

}
