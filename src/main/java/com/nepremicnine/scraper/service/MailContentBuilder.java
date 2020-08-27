package com.nepremicnine.scraper.service;

import com.nepremicnine.scraper.model.HousingAd;
import com.nepremicnine.scraper.model.HousingAdWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.util.List;


@Service
public class MailContentBuilder {

    private final TemplateEngine templateEngine;

    @Autowired
    public MailContentBuilder(final TemplateEngine templateEngine) {
        this.templateEngine = templateEngine;
    }

    public String build(List<HousingAd> ads) {
        HousingAdWrapper housingAdWrapper = new HousingAdWrapper(ads);
        Context context = new Context();
        context.setVariable("housingAdWrapper", housingAdWrapper);;

        return templateEngine.process("mailTemplate", context);
    }

}
