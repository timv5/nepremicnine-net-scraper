package com.nepremicnine.scraper.service;

public interface EmailService {

    /**
     * Send an email
     * @param from sender
     * @param to receiver
     * @param subject subject that contains all the requested information from NEPREMICNINE.NET
     * @param body
     */
    void send(final String from, final String to, final String subject, final String body);

}
