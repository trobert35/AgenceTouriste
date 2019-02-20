package com.fr.adaming;

import java.util.Date;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import com.fr.adaming.entity.Prestation;
import com.fr.adaming.restController.AdminRestController;

import springfox.documentation.swagger2.annotations.EnableSwagger2;


@SpringBootApplication
@EnableSwagger2
public class AgenceVoyageApplication {
	/**
	 * @author Claire
	 * @param args
	 */

	public static void main(String[] args) {
		ApplicationContext context = SpringApplication.run(AgenceVoyageApplication.class, args);
	
		AdminRestController bean = context.getBean(AdminRestController.class);
		
		Prestation presta = new Prestation("presta1", new Date(), new Date(), "a", "b", 30, 0.0f, "c");
		
//		presta.set
//		
//		bean.createPrestation(presta);
	
	
	}

}
