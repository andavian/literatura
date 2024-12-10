package com.andavian.literatura;

import com.andavian.literatura.principal.Principal;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.beans.factory.annotation.Autowired;


@SpringBootApplication
public class LiteraturaApplication implements CommandLineRunner {

	private final Principal principal;

	@Autowired
	public LiteraturaApplication(Principal principal) {
		this.principal = principal;
	}

	public static void main(String[] args) {
		SpringApplication.run(LiteraturaApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		principal.muestraMenu();
	}
}
