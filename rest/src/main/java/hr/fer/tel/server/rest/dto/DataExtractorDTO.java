package hr.fer.tel.server.rest.dto;

import hr.fer.tel.server.rest.model.DataExtractor;

public class DataExtractorDTO {
	    
    private String dataFormat;
    
    private String timeColumn;
    
    private String valueColumn;

	public DataExtractorDTO(String dataFormat, String timeColumn, String valueColumn) {
		super();
		this.dataFormat = dataFormat;
		this.timeColumn = timeColumn;
		this.valueColumn = valueColumn;
	}

	public DataExtractorDTO() {

	}


	public String getTimeColumn() {
		return timeColumn;
	}

	public void setTimeColumn(String timeColumn) {
		this.timeColumn = timeColumn;
	}

	public String getValueColumn() {
		return valueColumn;
	}

	public void setValueColumn(String valueColumn) {
		this.valueColumn = valueColumn;
	}

	public String getDataFormat() {
		return dataFormat;
	}

	public void setDataFormat(String dataFormat) {
		this.dataFormat = dataFormat;
	}
	
	public static DataExtractorDTO of(DataExtractor extractor) {
		return new DataExtractorDTO(extractor.getDataFormat(), extractor.getTimeColumn(), extractor.getValueColumn());
	}
    
}
