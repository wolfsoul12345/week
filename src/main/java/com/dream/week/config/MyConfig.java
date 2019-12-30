package com.dream.week.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.CacheControl;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

@Configuration
public class MyConfig extends WebMvcConfigurationSupport {



    @Override
    protected void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/img/**").addResourceLocations("file:C:/img/").setCacheControl(CacheControl.noCache());
        registry.addResourceHandler("/css/**").addResourceLocations("classpath:/static/css/").setCacheControl(CacheControl.noCache());
        registry.addResourceHandler("/js/**").addResourceLocations("classpath:/static/js/").setCacheControl(CacheControl.noCache());

    }
}
