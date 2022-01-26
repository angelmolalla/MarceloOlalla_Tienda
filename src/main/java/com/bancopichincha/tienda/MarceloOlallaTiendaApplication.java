package com.bancopichincha.tienda;

import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
public class MarceloOlallaTiendaApplication extends SpringBootServletInitializer implements CommandLineRunner {

	private static final Logger log_logger = Logger.getLogger(MarceloOlallaTiendaApplication.class.getName());
	
	public static void main(String[] args) {
		SpringApplication.run(MarceloOlallaTiendaApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		log_logger.log(Level.INFO,"Aplication Start");
		
	}

}
