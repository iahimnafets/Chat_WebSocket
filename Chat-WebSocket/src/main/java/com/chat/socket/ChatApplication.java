package com.chat.socket;

import com.chat.socket.entitys.Chat;
import com.chat.socket.repository.ChatRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

import java.util.Date;

@ComponentScan( basePackages = { "com" } )
@SpringBootApplication
public class ChatApplication {

	/**
     * Mihai Stefan - 26 June - 2022˙
	 * @param args˙
	 */
	public static void main(String[] args) {
		SpringApplication.run(ChatApplication.class, args);
	}

}
