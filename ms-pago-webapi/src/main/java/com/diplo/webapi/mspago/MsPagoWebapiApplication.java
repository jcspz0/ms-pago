package com.diplo.webapi.mspago;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(
	{ "com.diplo", "org.springframework.data.repository.CrudRepository" }
)
public class MsPagoWebapiApplication extends SpringBootServletInitializer {

	//add
	public static void main(String[] args) {
		SpringApplication.run(MsPagoWebapiApplication.class, args);
	}
}
