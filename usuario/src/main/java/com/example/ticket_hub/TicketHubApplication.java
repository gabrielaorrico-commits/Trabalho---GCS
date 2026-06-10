// TicketHubApplication é a classe principal da aplicação, onde o método main é definido para iniciar a aplicação Spring Boot. Ela é anotada com @SpringBootApplication, que é uma combinação de @Configuration, @EnableAutoConfiguration e @ComponentScan, permitindo que a aplicação seja configurada automaticamente e que os componentes sejam escaneados para serem gerenciados pelo Spring.
package com.example.ticket_hub;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TicketHubApplication {

	public static void main(String[] args) {
		SpringApplication.run(TicketHubApplication.class, args);
	}

}
