package com.nepremicnine.scraper.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "app")
public class AppProperties {

    private final UrlConfig urlConfig = new UrlConfig();

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

        public boolean isZnizaneCene() {
            return znizaneCene;
        }

        public void setZnizaneCene(boolean znizaneCene) {
            this.znizaneCene = znizaneCene;
        }

        public String getPosredovanje() {
            return posredovanje;
        }

        public void setPosredovanje(String posredovanje) {
            this.posredovanje = posredovanje;
        }

        public String getNepremicnina() {
            return nepremicnina;
        }

        public void setNepremicnina(String nepremicnina) {
            this.nepremicnina = nepremicnina;
        }

        public String getRegija() {
            return regija;
        }

        public void setRegija(String regija) {
            this.regija = regija;
        }

        public String getCenaOd() {
            return cenaOd;
        }

        public void setCenaOd(String cenaOd) {
            this.cenaOd = cenaOd;
        }

        public String getCenaDo() {
            return cenaDo;
        }

        public void setCenaDo(String cenaDo) {
            this.cenaDo = cenaDo;
        }

        public String getTipi() {
            return tipi;
        }

        public void setTipi(String tipi) {
            this.tipi = tipi;
        }

        public String getLeto() {
            return leto;
        }

        public void setLeto(String leto) {
            this.leto = leto;
        }

        public String getVelikostM2Od() {
            return velikostM2Od;
        }

        public void setVelikostM2Od(String velikostM2Od) {
            this.velikostM2Od = velikostM2Od;
        }

        public String getVelikostM2Do() {
            return velikostM2Do;
        }

        public void setVelikostM2Do(String velikostM2Do) {
            this.velikostM2Do = velikostM2Do;
        }
    }

    public UrlConfig getUrlConfig() {
        return urlConfig;
    }
}
