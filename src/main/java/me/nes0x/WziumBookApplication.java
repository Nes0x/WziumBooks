package me.nes0x;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

import javax.validation.Validator;

@SpringBootApplication
public class WziumBookApplication {

    public static void main(String[] args) {
        SpringApplication.run(WziumBookApplication.class, args);
    }

    @Bean
    Validator validator() {
        return new LocalValidatorFactoryBean();
    }

}
