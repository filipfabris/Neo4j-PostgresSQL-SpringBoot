package hr.fer.tel.server.rest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class RestServerApplication {
	protected final Log logger = LogFactory.getLog(getClass());

	public static void main(String[] args) {
		SpringApplication.run(RestServerApplication.class, args);
	}
}
