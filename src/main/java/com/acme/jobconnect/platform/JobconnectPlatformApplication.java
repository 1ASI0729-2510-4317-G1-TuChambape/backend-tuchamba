package com.acme.jobconnect.platform;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class JobconnectPlatformApplication {

    public static void main(String[] args) {
        SpringApplication.run(JobconnectPlatformApplication.class, args);
    }

}