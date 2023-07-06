package hr.fer.tel.server.rest.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToOne;

import hr.fer.tel.server.rest.dto.MesurmentViewDTO;


@Entity
//@DiscriminatorValue("mesurment")
//@Table(name = "MesurmentView")
////public class MesurmentView{
//@PrimaryKeyJoinColumn(name = "mesurmentId")
//@JsonTypeName("mesurment")
public class MesurmentView extends View{



//    @Id
//    @GeneratedValue
//	private long id;

	private String measurementUnit;

    @OneToOne(cascade = CascadeType.ALL)
	private MeasurmentSelectForm selectForm;

    @OneToOne(cascade = CascadeType.ALL)
	private Request query;

    @OneToOne(cascade = CascadeType.ALL)
	private DataExtractor responseExtracting;

	public MesurmentView(String title, String description, String viewType, String measurementUnit, MeasurmentSelectForm selectForm,
			Request query, DataExtractor responseExtracting) {
		super(0, title, description, viewType);
//		setTitle(title);
//		setViewType(viewType);
		this.measurementUnit = measurementUnit;
		this.selectForm = selectForm;
		this.query = query;
		this.responseExtracting = responseExtracting;
	}

	public MesurmentView() {
		super();
	}

	public MesurmentView(MesurmentViewDTO dto) {
		super(0, dto.getTitle(), dto.getDescription(), dto.getViewType());

		this.measurementUnit = dto.getMeasurementUnit();
		this.selectForm = new MeasurmentSelectForm(dto.getSelectForm());
		this.query = new Request(dto.getQuery());
		this.responseExtracting = new DataExtractor(dto.getResponseExtracting());
	}

//	public long getId() {
//		return id;
//	}
//
//	public void setId(long id) {
//		this.id = id;
//	}

//	public String getTitle() {
//		return title;
//	}
//
//	public void setTitle(String title) {
//		this.title = title;
//	}
//
//	public String getViewType() {
//		return viewType;
//	}
//
//	public void setViewType(String viewType) {
//		this.viewType = viewType;
//	}

	public String getMeasurementUnit() {
		return measurementUnit;
	}

	public void setMeasurementUnit(String measurementUnit) {
		this.measurementUnit = measurementUnit;
	}

	public MeasurmentSelectForm getSelectForm() {
		return selectForm;
	}

	public void setSelectForm(MeasurmentSelectForm selectForm) {
		this.selectForm = selectForm;
	}

	public Request getQuery() {
		return query;
	}

	public void setQuery(Request query) {
		this.query = query;
	}

	public DataExtractor getResponseExtracting() {
		return responseExtracting;
	}

	public void setResponseExtracting(DataExtractor responseExtracting) {
		this.responseExtracting = responseExtracting;
	}

}
