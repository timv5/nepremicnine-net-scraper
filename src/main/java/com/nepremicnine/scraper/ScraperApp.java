package com.nepremicnine.scraper;

import com.nepremicnine.scraper.config.AppProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EntityScan(basePackages = {"com.nepremicnine.scraper"})
@EnableScheduling
@EnableConfigurationProperties(AppProperties.class)
public class ScraperApp {

    public static void main(String[] args) {
        SpringApplication.run(ScraperApp.class, args);
    }

}
