package main;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
@EnableMongoRepositories("repositories")
@ComponentScan({"repositories", "controllers", "domain", "services"})
public class Application {

    public static void main(String[] args) {
    		SpringApplication.run(Application.class, args);
    }
}