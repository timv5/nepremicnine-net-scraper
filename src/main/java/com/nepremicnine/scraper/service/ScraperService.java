package com.nepremicnine.scraper.service;

import com.nepremicnine.scraper.config.AppProperties;
import com.nepremicnine.scraper.config.StaticConfig;
import com.nepremicnine.scraper.model.HousingAd;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class ScraperService {

    private static final Logger LOGGER = LoggerFactory.getLogger(ScraperService.class);

    private final AppProperties appProperties;

    @Autowired
    public ScraperService(final AppProperties appProperties) {
        this.appProperties = appProperties;
    }

    public List<HousingAd> pageScraper() {
        List<HousingAd> ads = new ArrayList<>();
        int resultCounter = 0;
        int maxResults = Integer.parseInt(this.appProperties.getMax().getResult());
        try {

            int pageNumber = 1;
            while(true) {
                Document document = Jsoup.connect(urlBuilder() + pageNumber + "/").get();
                Elements elements = document.getElementsByClass("seznam");
                if (elements == null || elements.isEmpty() || elements.text().isEmpty() || elements.get(0) == null) {
                    break;
                }

                Elements elements1 = elements.get(0).children();

                for (Element e : elements1) {
                    if (resultCounter >= maxResults) {
                        break;
                    }

                    if (e.text() == null || e.text().isEmpty()) {
                        continue;
                    }

                    String tmp = e.text().replaceAll(", ", "\n");
                    if (tmp.contains("€")) {
                        tmp = tmp.substring(0, tmp.indexOf("€"));
                    }

                    tmp = tmp.replace("Ne spreglejte - ", "");

                    // set result in an object
                    String[] str = tmp.split("\n");
                    HousingAd housingAd = new HousingAd();
                    housingAd.setAd(Arrays.asList(str));
                    ads.add(housingAd);

                    resultCounter++;
                }

                pageNumber++;
            }
        } catch (IOException e) {
            LOGGER.error(e.getMessage(), e);
        }

        return ads;
    }

    private String urlBuilder() {
        StringBuilder sb = new StringBuilder(StaticConfig.BASE_URL);
        sb.append("/oglasi-" + appProperties.getUrlConfig().getPosredovanje());
        sb.append("/" + appProperties.getUrlConfig().getRegija());
        sb.append("/" + appProperties.getUrlConfig().getNepremicnina());

        sb.append("/");
        return sb.toString();
    }

}
