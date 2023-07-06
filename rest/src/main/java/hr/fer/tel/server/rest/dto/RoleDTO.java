package hr.fer.tel.server.rest.dto;

import hr.fer.tel.server.rest.model.Role;
import java.util.List;

public class RoleDTO {
	
    private long id;

    private String name;

	public RoleDTO(long id, String name) {
		super();
		this.id = id;
		this.name = name;
	}
	
	public RoleDTO(String name) {
		super();
		this.name = name;
	}

	public RoleDTO() {
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

	public static List<String> of(List<Role> roles) {
		return roles.stream().map(role -> RoleDTO.of(role)).toList();
	}

	public static String of(Role role) {
		return role.getName();
	}

}
