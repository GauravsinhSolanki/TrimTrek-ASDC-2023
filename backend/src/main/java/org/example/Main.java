package org.example;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
@SpringBootApplication
public class Main {
    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }
    @Bean
    public ApplicationRunner initializeData(UserServices userServices) {
        return new ApplicationRunner() {
            @Override
            public void run(ApplicationArguments args) throws Exception {
                User user1 = new User();
                user1.setUsername("neel");
                user1.setPassword("neel@dal");
                userServices.createUser(user1);

                User user2 = new User();
                user2.setUsername("gaurav");
                user2.setPassword("gaurav@dal");
                userServices.createUser(user2);

            }
        };
    }
}