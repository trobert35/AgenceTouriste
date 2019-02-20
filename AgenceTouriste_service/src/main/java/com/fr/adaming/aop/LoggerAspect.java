package com.fr.adaming.aop;

import org.apache.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;

import com.fr.adaming.service.AdminService;

/**
 * @author Thomas R
 *
 */
@Aspect
public class LoggerAspect {

	/**
	 * @param jp
	 * @param e  Throwable Exception qui sera attrapée par la methode La methode
	 *           enregistre la stack Trace dans le fichier de Log toute erreur
	 *           consideree comme FATAL
	 */
	@AfterThrowing(pointcut = "execution(* com.fr.adaming.service.*(..))", throwing = "e")
	public void LogError(JoinPoint jp, Throwable e) {

		Logger log = Logger.getLogger(AdminService.class);

		log.fatal(e.getStackTrace());
	}
}
