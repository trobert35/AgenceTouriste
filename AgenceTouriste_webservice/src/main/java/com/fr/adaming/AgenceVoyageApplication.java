package com.fr.adaming;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author Claire
 *
 */
@SpringBootApplication
public class AgenceVoyageApplication extends SpringBootServletInitializer{

	/**
	 * @param args arguments
	 */
	public static void main(String[] args) {
		SpringApplication.run(AgenceVoyageApplication.class, args);
			
		System.out.println("********************************Bonjour********************************");
	}
}
