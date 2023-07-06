package hr.fer.tel.server.rest.dto;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.fasterxml.jackson.annotation.JsonTypeName;
import hr.fer.tel.server.rest.model.InputType;
import hr.fer.tel.server.rest.model.StringInput;

@JsonTypeName("STRING")
public class StringInputDTO extends InputsDTO {
  private static Logger log = LoggerFactory.getLogger(StringInputDTO.class);

  private String description;
  private String defaultValue;
  private String pattern;
  private String[] values;

  public StringInputDTO(String name, String title, int inputOrder, String description, String defaultValue, String pattern,
      String[] values) {
    super(InputType.STRING, name, title, inputOrder);
    this.description = description;
    this.defaultValue = defaultValue;
    this.pattern = pattern;
    this.values = values;
  }

  public StringInputDTO() {

  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public String getDefaultValue() {
    return defaultValue;
  }

  public void setDefaultValue(String defaultValue) {
    this.defaultValue = defaultValue;
  }

  public String getPattern() {
    return pattern;
  }

  public void setPattern(String pattern) {
    this.pattern = pattern;
  }

  public String[] getValues() {
    return values;
  }

  public void setValues(String[] values) {
    this.values = values;
  }

  public static StringInputDTO of(StringInput input) {
    String[] values = input.getValues();

    return new StringInputDTO(input.getName(), input.getTitle(), input.getInputOrder(), input.getDescription(), input.getDefaultValue(),
        input.getPattern(), values);
  }

}
