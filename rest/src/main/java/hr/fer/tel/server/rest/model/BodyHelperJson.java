package hr.fer.tel.server.rest.model;

import java.io.IOException;

import javax.persistence.AttributeConverter;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class BodyHelperJson implements AttributeConverter<Object, String> {

	private final static ObjectMapper objectMapper = new ObjectMapper();

	@Override
	public String convertToDatabaseColumn(Object meta) {
		try {
			return objectMapper.writeValueAsString(meta);
		} catch (JsonProcessingException ex) {
			System.err.println("EER");
			return null;
			// or throw an error
		}
	}

	@Override
	public Object convertToEntityAttribute(String dbData) {
		try {
			return objectMapper.readValue(dbData, Object.class);
		} catch (IOException ex) {
			System.err.println("EER");
			return null;
		}
	}

}
