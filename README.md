# nepremicnine-net-scraper
Email notification scraper app for Nepremicnine.net

## How does it work
Are you looking for a suitable apartment or other real estate and would like to be informed at the right moment when it appears in the ad? 
The application works by sending an email (by default every 6 hours) to your address with the most important information about the relevant properties after setting the appropriate criteria.

## Usage
In application.properties file set your email credentials: 
- spring.mail.host=
- spring.mail.port=
- spring.mail.username=
- spring.mail.password=
- spring.mail.properties.mail.smtp.ssl.enable=
- app.mail.from_to=

(If you are using gmail, you will have to set this application as trusted)

By default email will be sent every 6 hours, but this can be configured in ScraperScheduler.java.
For example: @Scheduled(fixedRate = 21600000) - number must be in miliseconds

For setting a parameters you need to set these parameters, also in application.properties. For example:
- app.urlConfig.zizanaCena=true
- app.urlConfig.posredovanje=prodaja
- app.urlConfig.nepremicnina=stanovanje
- app.urlConfig.regija=ljubljana-mesto
- app.urlConfig.cenaOd=200.000
- app.urlConfig.cenaDo=500.000
- app.urlConfig.tipi=garsonjera,1-sobno

You can look for these params in detail on https://www.nepremicnine.net/.

## Prequences
- Maven, Java

## Running the application
- In application root directory run: - mvn clean install
- in /target/ directory: - java -jar nepremicnine-net-scraper-1.0-SNAPSHOT.jar
