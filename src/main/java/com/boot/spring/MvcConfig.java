package com.boot.spring;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
@EnableWebMvc
public class MvcConfig extends WebMvcConfigurerAdapter {
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry
          .addResourceHandler("/html/**")
          .addResourceLocations("/WEB-INF/static/html/");
        
        registry.addResourceHandler("/css/**")
        .addResourceLocations("/WEB-INF/static/css/");
        
        registry.addResourceHandler("/js/**")
        .addResourceLocations("/WEB-INF/static/js/");
   
        registry.addResourceHandler("/webjars/**")
        .addResourceLocations("classpath:/META-INF/resources/webjars/");
    }
}