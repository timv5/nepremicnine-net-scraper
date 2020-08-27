package com.nepremicnine.scraper.scheduler;

import com.nepremicnine.scraper.service.EmailServiceImpl;
import com.nepremicnine.scraper.service.ScraperService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;


@Component
public class ScraperScheduler {

    private static final Logger LOGGER = LoggerFactory.getLogger(ScraperScheduler.class);

    private final EmailServiceImpl emailService;
    private final ScraperService scraperService;

    @Autowired
    public ScraperScheduler(final EmailServiceImpl emailService,
                            final ScraperService scraperService) {
        this.emailService = emailService;
        this.scraperService = scraperService;
    }

    @Scheduled(fixedRate = 20000)
    public void scraperScheduler() {
        String result = this.scraperService.pageScraper();
        LOGGER.info(result);
    }

}
