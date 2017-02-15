package com.cache.application;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * Created by michal.zfania on 13-Feb-17.
 */
@Configuration
public class StaticWebMvcConfiguration {

    @Bean
    public WebMvcConfigurerAdapter forwardToIndex() {
        return new WebMvcConfigurerAdapter() {
            @Override
            public void addViewControllers(ViewControllerRegistry registry) {
                // forward requests to / to the main static index html
                registry.addViewController("/").setViewName(
                        "forward: /resources/index.html");
            }
        };
    }
}
