package hr.fer.tel.server.rest.dto;

import hr.fer.tel.server.rest.model.Layout;

public class LayoutDTO {
	
    private long id;
    
    private String name;

	public LayoutDTO(long id, String name) {
		super();
		this.id = id;
		this.name = name;
	}
	
	public LayoutDTO(String name) {
		super();
		this.name = name;
	}

	public LayoutDTO() {
		super();
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public static LayoutDTO of(Layout layout) {
		return new LayoutDTO(layout.getName());
	}
    

}
