package hr.fer.tel.server.rest.utils;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class NoSuchElement extends RuntimeException{

	private static final long serialVersionUID = 1L;

	public NoSuchElement(String message) {
        super(message);
    }

}
