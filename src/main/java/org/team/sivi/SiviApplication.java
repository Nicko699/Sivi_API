package org.team.sivi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class SiviApplication {

    public static void main(String[] args) {
        SpringApplication.run(SiviApplication.class, args);
    }

}
