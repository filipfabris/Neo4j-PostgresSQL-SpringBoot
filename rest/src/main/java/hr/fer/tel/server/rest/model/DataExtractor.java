package hr.fer.tel.server.rest.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import hr.fer.tel.server.rest.dto.DataExtractorDTO;

@Entity
@Table(name = "DataExtractor")
public class DataExtractor {
	
    @Id
    @GeneratedValue
	private long id;
    
    private String dataFormat;
    
    private String timeColumn;
    
    private String valueColumn;

	public DataExtractor(String dataFormat, String timeColumn, String valueColumn) {
		super();
		this.dataFormat = dataFormat;
		this.timeColumn = timeColumn;
		this.valueColumn = valueColumn;
	}

	public DataExtractor() {

	}
	
	public DataExtractor(DataExtractorDTO dto) {
		this.dataFormat = dto.getDataFormat();
		this.timeColumn = dto.getTimeColumn();
		this.valueColumn = dto.getValueColumn();

	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
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
    
}
