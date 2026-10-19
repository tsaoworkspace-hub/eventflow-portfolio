package com.project.sneakermarket;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * EventFlow portfolio backend entry point.
 *
 * The package name stays compatible with the original training project while
 * the repository contains only the author's event, calendar and notification
 * modules plus small demo adapters.
 */
@SpringBootApplication
@EnableScheduling
public class EventFlowBackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(EventFlowBackendApplication.class, args);
    }
}
