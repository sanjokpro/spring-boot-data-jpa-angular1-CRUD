package com.sanjok.springbootdataangular1crud;

import com.sanjok.springbootdataangular1crud.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@Import({JPAConfig.class})

public class SpringBootDataAngular1CrudApplication implements CommandLineRunner {
    private static final Logger log = LoggerFactory.getLogger(SpringBootDataAngular1CrudApplication.class);

    public static void main(String[] args) {
        //SpringApplication.run(HrmsApplication.class, args);
        SpringApplication springApplication = new SpringApplication();
        ApplicationContext ctx = springApplication.run(SpringBootDataAngular1CrudApplication.class, args);
        UserRepository userRepository = ctx.getBean(UserRepository.class);
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println("===============[SpringDataAngularCRUD] Started Successfully!=======================");
    }
/*
    @Bean
    public CommandLineRunner demo(UserRepository userRepository) {
        return (args) -> {
            Object userByUsername = userRepository.findByUserName("sanjok");
            Object userById = userRepository.findOne(1L);
            Object userByUsernameAndPassword = userRepository.findByUserNameAndPassword("sanjok", "pass");
            if (null != userById) {
                System.out.println("userById:" + userById);
            } else {
                System.out.println("userById:null");
            }
            if (null != userByUsername) {
                System.out.println("userByUsername:" + userByUsername);
            } else {
                System.out.println("userByUsername:null");
            }
            if (null != userByUsernameAndPassword) {
                System.out.println("userByUsernameAndPassword:" + userByUsernameAndPassword);
            } else {
                System.out.println("userByUsernameAndPassword:null");
            }

            log.info("User found with findByUserNameAndPassword('sanjok',pass):");
            log.info("--------------------------------------------");
            User uu=userRepository.findByUserNameAndPassword("sanjok", "pass");
                log.info(uu.toString());




            log.info("User found with findByUserName('sanjok'):");
            log.info("--------------------------------------------");
            for (User u : userRepository.findByUserName("sanjok")) {
                log.info(u.toString());
            }

        };
    }*/
}

