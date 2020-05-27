package org.example;

import org.example.tm.context.Bootstrap;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class App 
{
    public static void main(String[] args) throws Exception {
        var ctxt = SpringApplication.run(App.class, args);
        ctxt.getBean(Bootstrap.class).init();
    }
}
