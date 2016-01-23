package com.ojins.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.web.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.GenericServlet;
import javax.servlet.Servlet;
import javax.servlet.ServletRequest;
import javax.servlet.ServletException;
import javax.servlet.ServletResponse;
import java.io.IOException;

/**
 * Hello world!
 *
 */
@Configuration
@EnableAutoConfiguration
public class Application extends SpringBootServletInitializer {
    @SuppressWarnings("serial")
    @Bean
    public Servlet dispatcherServlet() {
        return new GenericServlet() {
            @Override
            public void service(ServletRequest req, ServletResponse res)
                    throws ServletException, IOException {
                res.setContentType("text/plain");
                res.getWriter().append("Hello World");
            }
        };
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(Application.class);
    }

    public static void main( String[] args ) throws Exception
    {
        SpringApplication.run(Application.class, args);
    }
}
