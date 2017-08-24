package com.sanjok.springbootdataangular1crud;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@Import({JPAConfig.class})
public class SpringBootDataAngular1CrudApplication implements CommandLineRunner {


    public static void main(String[] args) {
        //SpringApplication.run(HrmsApplication.class, args);
        SpringApplication springApplication = new SpringApplication();
        ApplicationContext ctx = springApplication.run(SpringBootDataAngular1CrudApplication.class, args);

    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println("===============[SpringDataAngularCRUD] Started Successfully!=======================");
    }
}

