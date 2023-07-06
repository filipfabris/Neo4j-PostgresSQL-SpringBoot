package hr.fer.tel.server.rest.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import hr.fer.tel.server.rest.dto.StringInputDTO;

@Entity
@Table(name = "StringInput")
public class StringInput extends Inputs {
  private static Logger log = LoggerFactory.getLogger(StringInput.class);

  private String description;
  private String defaultValue;
  private String pattern;
  @Column(name = "input_values")
  private String[] values;

  public StringInput(String name, String title, int inputOrder, String description, String defaultValue, String pattern) {
    super(0, InputType.STRING, name, title, inputOrder);
    this.description = description;
    this.defaultValue = defaultValue;
    this.pattern = pattern;
  }

  public StringInput(String name, String title, int inputOrder, String description, String defaultValue, String... values) {
    super(0, InputType.STRING, name, title, inputOrder);
    this.description = description;
    this.defaultValue = defaultValue;
    this.values = values;
  }

  
  public StringInput(StringInputDTO input) {
    super(0, InputType.BOOLEAN, input.getName(), input.getTitle(), input.getInputOrder());
    this.description = input.getDescription();
    this.defaultValue = input.getDefaultValue();
    this.pattern = input.getPattern();
    this.values = input.getValues();
  }

  public StringInput() {

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
}
