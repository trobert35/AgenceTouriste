package com.fr.adaming;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.fr.adaming.dao.IUserDao;
import com.fr.adaming.entity.CB;
import com.fr.adaming.entity.Prestation;
import com.fr.adaming.entity.User;
import com.fr.adaming.service.AdminService;
import com.fr.adaming.service.UserService;

@SpringBootApplication
public class AgenceVoyageApplication {
	/**
	 * @author Claire
	 * @param args
	 * @throws ParseException 
	 */

	public static void main(String[] args) throws ParseException {
		SpringApplication.run(AgenceVoyageApplication.class, args);

//		CB cb = new CB();
//		cb.setCrypto(123L);
//
//		cb.setDateExp(new SimpleDateFormat("dd/MM/yyyy").parse("02/01/2010"));
//
//		cb.setNumeroCarte(123456L);
//		User u = new User("toto", "toto", "toto@toto.fr", "123");
//		u.setId(0L);
//		UserService userService = new UserService();
//		
//		u = userService.create(u);
//
//		Prestation p = new Prestation("Camping", new SimpleDateFormat("dd/MM/yyyy").parse("02/02/2019"),
//				new SimpleDateFormat("dd/MM/yyyy").parse("20/02/2019"), "Paris", "Marseille", 60, 100, null);
//
//		AdminService admService = new AdminService();
//		p = admService.createPrestation(p);
	}

}
