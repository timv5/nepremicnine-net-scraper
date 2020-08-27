package com.nepremicnine.scraper.config;

import lombok.Data;
import lombok.ToString;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@ToString
@Component
@ConfigurationProperties(prefix = "app")
public class AppProperties {

    private final UrlConfig urlConfig = new UrlConfig();
    private final Mail mail = new Mail();
    private final Max max = new Max();

    @Data
    @ToString
    public static class Max {

        private String result;

    }

    @Data
    @ToString
    public static class Mail {

        private String subject;
        private String from_to;

    }

    @Data
    @ToString
    public static class UrlConfig {

        private boolean znizaneCene;
        private String posredovanje;
        private String nepremicnina;
        private String regija;
        private String cenaOd;
        private String cenaDo;
        private String tipi;
        private String leto;
        private String velikostM2Od;
        private String velikostM2Do;

    }
}
