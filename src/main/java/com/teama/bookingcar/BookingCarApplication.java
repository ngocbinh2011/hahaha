package com.teama.bookingcar;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.env.Environment;

@Slf4j
@SpringBootApplication
public class BookingCarApplication implements CommandLineRunner {

    @Autowired
    private Environment environment;

    public static void main(String[] args) {
        SpringApplication springApplication = new SpringApplication(BookingCarApplication.class);
        springApplication.run(args);
    }

    @Override
    public void run(String... args) throws Exception {
        log.info(String.format("Server running at %s", environment.getProperty("server.port")));
    }
}
