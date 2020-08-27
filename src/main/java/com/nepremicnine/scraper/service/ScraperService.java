package com.nepremicnine.scraper.service;

import com.nepremicnine.scraper.config.AppProperties;
import com.nepremicnine.scraper.config.StaticConfig;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class ScraperService {

    private static final Logger LOGGER = LoggerFactory.getLogger(ScraperService.class);

    private final AppProperties appProperties;

    @Autowired
    public ScraperService(final AppProperties appProperties) {
        this.appProperties = appProperties;
    }

    public String pageScraper() {
        String result = "";
        try {

            int pageNumber = 1;
            int counter = 0;
            while(true) {
                Document document = Jsoup.connect(urlBuilder() + pageNumber + "/").get();
                Elements elements = document.getElementsByClass("seznam");
                if (elements == null || elements.isEmpty() || elements.text().isEmpty() || elements.get(0) == null) {
                    break;
                }

                Elements elements1 = elements.get(0).children();

                for (Element e : elements1) {
                    if (e.text() == null || e.text().isEmpty()) {
                        continue;
                    }
                    counter++;
                    String tmp = e.text().replaceAll(", ", "\n");
                    if (tmp.contains("€")) {
                        tmp = tmp.substring(0, tmp.indexOf("€"));
                    }

                    tmp = tmp.replace("Ne spreglejte - ", "");
                    result += "\n\n";
                    result += tmp;
                }
                LOGGER.info(String.valueOf(counter));
                pageNumber++;
            }

            LOGGER.info("Tok: " + counter);

        } catch (IOException e) {
            LOGGER.error(e.getMessage(), e);
        }

        return result;
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
