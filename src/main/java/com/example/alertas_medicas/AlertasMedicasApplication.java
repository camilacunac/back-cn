package com.example.alertas_medicas;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class AlertasMedicasApplication {

	public static void main(String[] args) {
		SpringApplication.run(AlertasMedicasApplication.class, args);
	}

}
