package com.cas.app2;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author 赵秀非
 */
@SuppressWarnings({"JavaDoc", "SpringJavaAutowiredFieldsWarningInspection"})
@SpringBootApplication
public class App2Server implements CommandLineRunner {
    public static void main(String[] args) {
        SpringApplication.run(App2Server.class, args);
    }

    @Override
    public void run(String... strings) throws Exception {
        System.out.println("====App2Server=====");
    }
}

