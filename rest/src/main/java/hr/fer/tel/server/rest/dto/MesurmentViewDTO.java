package hr.fer.tel.server.rest.dto;

import com.fasterxml.jackson.annotation.JsonTypeName;

import hr.fer.tel.server.rest.model.MesurmentView;

@JsonTypeName("mesurment")
public class MesurmentViewDTO extends ViewDTO{

	private String measurementUnit;

	private MeasurmentSelectFormDTO selectForm;

	private RequestDTO query;

	private DataExtractorDTO responseExtracting;

	public MesurmentViewDTO(String measurementUnit, MeasurmentSelectFormDTO selectForm, RequestDTO query,
			DataExtractorDTO responseExtracting) {
		super();
		this.measurementUnit = measurementUnit;
		this.selectForm = selectForm;
		this.query = query;
		this.responseExtracting = responseExtracting;
	}

	public MesurmentViewDTO() {
		super();
	}

	public String getMeasurementUnit() {
		return measurementUnit;
	}

	public void setMeasurementUnit(String measurementUnit) {
		this.measurementUnit = measurementUnit;
	}

	public MeasurmentSelectFormDTO getSelectForm() {
		return selectForm;
	}

	public void setSelectForm(MeasurmentSelectFormDTO selectForm) {
		this.selectForm = selectForm;
	}

	public RequestDTO getQuery() {
		return query;
	}

	public void setQuery(RequestDTO query) {
		this.query = query;
	}

	public DataExtractorDTO getResponseExtracting() {
		return responseExtracting;
	}

	public void setResponseExtracting(DataExtractorDTO responseExtracting) {
		this.responseExtracting = responseExtracting;
	}

	public static MesurmentViewDTO of(MesurmentView view) {
		RequestDTO req;

		if (view.getQuery() == null) {
			req = null;
		}
		else {
			req = RequestDTO.of(view.getQuery());
		}

		MeasurmentSelectFormDTO form;
		if(view.getSelectForm() == null) {
			form = null;
		}
		else {
			form = MeasurmentSelectFormDTO.of(view.getSelectForm());
		}

		DataExtractorDTO extractor;
		if(view.getResponseExtracting() == null) {
			extractor = null;
		}
		else {
			extractor = DataExtractorDTO.of(view.getResponseExtracting());
		}

		MesurmentViewDTO tmp = new MesurmentViewDTO(view.getMeasurementUnit(), form, req, extractor);

		tmp.setTitle(view.getTitle());
		tmp.setDescription(view.getDescription());
		tmp.setViewType(view.getViewType());

		return tmp;
	}

}