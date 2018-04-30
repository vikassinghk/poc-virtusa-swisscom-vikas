package org.vikas;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.vikas.model.Message;
import org.vikas.service.MessageService;

@EnableJpaAuditing
@EnableJpaRepositories
@SpringBootApplication
public class E2EModalApplication implements CommandLineRunner {

    @Autowired
    private MessageService messageService;
    private static final Logger logger = LoggerFactory.getLogger(E2EModalApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(E2EModalApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

        messageService.saveMsg(new Message("Vikas", "Java is awesome", "Thought of Java"));
        messageService.saveMsg(new Message("Mohit", "Spring is great", "Thought of Spring"));
    }
}
