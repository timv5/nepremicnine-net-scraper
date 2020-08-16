package com.nepremicnine.scraper.scheduler;

import com.nepremicnine.scraper.config.StaticConfig;
import com.nepremicnine.scraper.service.EmailServiceImpl;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class ScraperScheduler {

    private static final Logger LOGGER = LoggerFactory.getLogger(ScraperScheduler.class);

    private final EmailServiceImpl emailService;

    @Autowired
    public ScraperScheduler(final EmailServiceImpl emailService) {
        this.emailService = emailService;
    }

    @Scheduled(fixedRate = 2000)
    public void scraperScheduler() {
        try {
            Document document = Jsoup.connect(StaticConfig.BASE_URL).get();
            LOGGER.info(document.title());
        } catch (IOException e) {
            LOGGER.error(e.getMessage(), e);
        }
    }

}
