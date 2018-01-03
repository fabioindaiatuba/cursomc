package com.fabioindaiatuba.cursomc;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CursomcApplication implements CommandLineRunner {
	
	//@Autowired
	//private CloudinaryService cloudinaryService;
	
	public static void main(String[] args) {
		SpringApplication.run(CursomcApplication.class, args);
	}

	@Override
	public void run(String... arg0) throws Exception {
		//cloudinaryService.uploadFileteste("C:\\Fabio\\palmeiras.jpg");
	}
}
