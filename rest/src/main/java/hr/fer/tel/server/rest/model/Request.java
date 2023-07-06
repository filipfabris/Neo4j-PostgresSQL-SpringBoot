package hr.fer.tel.server.rest.model;

import java.util.Map;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonProperty;

import hr.fer.tel.server.rest.dto.RequestDTO;

@Entity
@Table(name = "Request")
public class Request {

    @Id
    @GeneratedValue
	private long id;

    private String method;

    private String uri;

	@Convert(converter = BodyHelperJson.class)
    private Map<String, String> headers;

	@Column(length = 51200)
    private String payload;

	public Request(String method, String uri, Map<String, String> headers, String payload) {
		super();
		this.method = method;
		this.uri = uri;
		this.headers = headers;
		this.payload = payload;
	}



	public Request() {
	}

	public Request(RequestDTO dto) {
		
		this.method = dto.getMethod();
		this.uri = dto.getUri();
		this.headers = dto.getHeaders();
		this.payload = dto.getPayload();
	}



	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getMethod() {
		return method;
	}

	public void setMethod(String method) {
		this.method = method;
	}

	public Map<String, String> getHeaders() {
		return headers;
	}

	public void setHeaders(Map<String, String> headers) {
		this.headers = headers;
	}

	public String getPayload() {
		return payload;
	}

	public void setPayload(String payload) {
		this.payload = payload;
	}

	@JsonProperty("URI")
	public String getUri() {
		return uri;
	}

	public void setUri(String uri) {
		this.uri = uri;
	}





}
