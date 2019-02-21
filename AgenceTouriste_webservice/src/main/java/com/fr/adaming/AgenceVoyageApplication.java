package com.fr.adaming;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author Claire
 *
 */
@SpringBootApplication
@EnableSwagger2
public class AgenceVoyageApplication {

	/**
	 * @param args arguments
	 */
	public static void main(String[] args) {
		SpringApplication.run(AgenceVoyageApplication.class, args);

	}
}
