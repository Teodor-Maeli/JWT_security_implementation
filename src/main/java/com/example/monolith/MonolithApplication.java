package com.example.monolith;

import com.example.monolith.services.impl.ClientServiceImpl;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
@EnableWebSecurity
@Configuration
public class MonolithApplication {

	public static void main(String[] args) {
		SpringApplication.run(MonolithApplication.class, args);
	}


	@Bean
	CommandLineRunner run(ClientServiceImpl clientService){
		return args -> {

//			clientService.saveClient(new Request("Johny Lenon", 30, "johny", "pass"));
//			clientService.saveClient(new Request("Teodor Maeli", 30, "Teodor", "pass"));
//			clientService.saveClient(new Request("Nurel Nazmi", 30, "Nurel", "pass"));
//			clientService.saveClient(new Request("Ivan Lenon", 30, "Ivan", "pass"));
//			clientService.saveClient(new Request("Russel Hobbs", 30, "Russel", "pass"));
//
//
//			clientService.saveRole(new Roles(null, "user"));
//			clientService.saveRole(new Roles(null, "bloger"));
//
//			clientService.AddRole("johny", "user");
//			clientService.AddRole("Teodor", "admin");
//			clientService.AddRole("Nurel", "bloger");
//			clientService.AddRole("Ivan", "admin");
//			clientService.AddRole("Russel", "user");
		};
	}

	@Bean
	public WebMvcConfigurer corsConfigurer() {
		return new WebMvcConfigurer() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				registry.addMapping("/*").allowedOrigins("http://localhost:4200")
						.allowedHeaders("*")
						.allowedMethods("*")
						.exposedHeaders("AccessToken","RefreshToken");


			}
		};

	}
}
