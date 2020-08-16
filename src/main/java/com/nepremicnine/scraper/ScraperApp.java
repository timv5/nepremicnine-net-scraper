package com.nepremicnine.scraper;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EntityScan(basePackages = {"com.nepremicnine.scraper"})
@EnableScheduling
public class ScraperApp {

    public static void main(String[] args) {
        SpringApplication.run(ScraperApp.class, args);
    }

}
