package com.nepremicnine.scraper.scheduler;

import com.nepremicnine.scraper.config.AppProperties;
import com.nepremicnine.scraper.model.HousingAd;
import com.nepremicnine.scraper.service.EmailServiceImpl;
import com.nepremicnine.scraper.service.MailContentBuilder;
import com.nepremicnine.scraper.service.ScraperService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;


@Component
public class ScraperScheduler {

    private static final Logger LOGGER = LoggerFactory.getLogger(ScraperScheduler.class);

    private final EmailServiceImpl emailService;
    private final ScraperService scraperService;
    private final AppProperties appProperties;
    private final MailContentBuilder mailContentBuilder;

    @Autowired
    public ScraperScheduler(final EmailServiceImpl emailService,
                            final ScraperService scraperService,
                            final AppProperties appProperties,
                            final MailContentBuilder mailContentBuilder) {
        this.emailService = emailService;
        this.scraperService = scraperService;
        this.appProperties = appProperties;
        this.mailContentBuilder = mailContentBuilder;
    }

    @Scheduled(fixedRate = 21600000)
    public void scraperScheduler() {

        // get data from nepremicnine.net
        List<HousingAd> housingAd = this.scraperService.pageScraper();
        if (housingAd == null || housingAd.isEmpty()) {
            LOGGER.info("Empty result from Nepremicnine.net");
            return;
        }

        String body = mailContentBuilder.build(housingAd);

        // send an email
        this.emailService.send(this.appProperties.getMail().getFrom_to(), this.appProperties.getMail().getFrom_to(),
                this.appProperties.getMail().getSubject(), body);
    }

}
