package com.cpt;



import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.io.*;
import java.math.BigDecimal;


@SpringBootApplication
@MapperScan("com.cpt.mapper")
public class Demo2Application {

    public static void main(String[] args) throws IOException {

        SpringApplication.run(Demo2Application.class, args);
    }

}
